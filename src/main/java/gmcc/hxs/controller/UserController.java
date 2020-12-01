package gmcc.hxs.controller;

import gmcc.hxs.constant.CookieConstant;
import gmcc.hxs.constant.CurrentUserConstants;
import gmcc.hxs.constant.RedisConstant;
import gmcc.hxs.dataobject.Userinfo;
import gmcc.hxs.enums.ResultEnum;
import gmcc.hxs.form.ChangePWDForm;
import gmcc.hxs.form.UserInfoForm;
import gmcc.hxs.service.UserInfoService;
import gmcc.hxs.utils.CookieUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @PostMapping("/loginAction")
    public ModelAndView loginAction(@Valid UserInfoForm form,
                              BindingResult bindingResult,
                              Map<String,Object> map,
                              HttpServletResponse response){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("user/login",map);
        }

        Userinfo userinfo = userInfoService.findUserInfoByUsername(form.getUsername());
        if(userinfo == null){
            map.put("msg","用户不存在");
            return new ModelAndView("user/login",map);
        }

        if(!userinfo.getPassword().equals(form.getPassword())){
            map.put("msg","用户密码不对");
            return new ModelAndView("user/login",map);
        }


        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token), userinfo.getUserId(),
                expire, TimeUnit.SECONDS);
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);
        return new ModelAndView("redirect:/main/index");
    }

    @GetMapping("/changepwd")
    public String changepwd(){
        return "user/changepwd";
    }

    @PostMapping("/changepwdAction")
    public ModelAndView changepwdAction(@Valid ChangePWDForm form,
                                    BindingResult bindingResult,
                                    Map<String,Object> map,HttpServletRequest request){
        System.out.println(form);

        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("user/changepwd",map);
        }

        String userid = ((Userinfo)request.getAttribute(CurrentUserConstants.CURRENT_USER)).getUserId();
        Userinfo userinfo = userInfoService.findUserInfoByUserId(userid);
        if(userinfo == null){
            map.put("msg","用户不存在");
            return new ModelAndView("user/login",map);
        }

        if(!userinfo.getPassword().equals(form.getOldpassword())){
            map.put("msg","用户旧密码不对");
            return new ModelAndView("user/changepwd",map);
        }

        if(!form.getNewpassword().equals(form.getNewpasswordconfirm())){
            map.put("msg","用户新密码不一致");
            return new ModelAndView("user/changepwd",map);
        }

        userinfo.setPassword(form.getNewpassword());
        Userinfo userinfo1 = userInfoService.updateUserInfo(userinfo);
        if(userinfo1 != null){
            map.put("msg","修改密码成功！！");
        }
        return new ModelAndView("user/changepwd");
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/detail")
    public ModelAndView detail(HttpServletRequest request,
                         Map<String,Object> map){
        String userid = ((Userinfo)request.getAttribute(CurrentUserConstants.CURRENT_USER)).getUserId();
        Userinfo userinfo = userInfoService.findUserInfoByUserId(userid);
        map.put("userinfo",userinfo);
        return new ModelAndView("user/detail",map);
        //return "user/detail";
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object> map){

        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie != null){
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS);
        map.put("url","/dns/user/login");
        return new ModelAndView("common/success",map);
    }
}

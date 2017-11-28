package com.imooc.controller;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.dataobject.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.form.ProductForm;
import com.imooc.form.SellerInfoForm;
import com.imooc.service.SellerService;
import com.imooc.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;



    @PostMapping("/loginAction")
    public ModelAndView loginAction(@Valid SellerInfoForm form,
                              BindingResult bindingResult,
                              Map<String,Object> map,
                              HttpServletResponse response){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("seller/login",map);
        }

        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(form.getUsername());
        if(sellerInfo == null){
            map.put("msg","用户不存在");
            return new ModelAndView("seller/login",map);
        }

        if(!sellerInfo.getPassword().equals(form.getPassword())){
            map.put("msg","用户密码不对");
            return new ModelAndView("seller/login",map);
        }


        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),sellerInfo.getSellerId(),expire, TimeUnit.SECONDS);

        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return new ModelAndView("redirect:/seller/order/list");



        //return null;
        /*SellerInfo sellerInfo = sellerService.findSellerInfoBySellerId(sellerId);
        if(sellerInfo == null){
            map.put("msg", ResultEnum.LOGIN_FAIL);
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),sellerId,expire, TimeUnit.SECONDS);

        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return new ModelAndView("redirect:/seller/order/list");*/
    }

    @GetMapping("/login")
    public String login(){
        return "seller/login";
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

        map.put("msg",ResultEnum.LOGOUT_SUCCESS);
        map.put("url","/sell/seller/login");
        return new ModelAndView("common/success",map);
    }
}

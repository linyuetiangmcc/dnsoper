package gmcc.hxs.controller;

import gmcc.hxs.constant.CurrentUserConstants;
import gmcc.hxs.dataobject.Userinfo;
import gmcc.hxs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request, Map<String,Object> map){
        String userid = ((Userinfo)request.getAttribute(CurrentUserConstants.CURRENT_USER)).getUserId();
        Userinfo userinfo = userInfoService.findUserInfoByUserId(userid);
        map.put("username",userinfo.getUsername());
        return new ModelAndView("main/index",map);
        //return "main/index";
    }

}

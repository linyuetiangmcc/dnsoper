package gmcc.hxs.aspect;

import gmcc.hxs.constant.CookieConstant;
import gmcc.hxs.constant.CurrentUserConstants;
import gmcc.hxs.constant.RedisConstant;
import gmcc.hxs.dataobject.Userinfo;
import gmcc.hxs.exception.UserAuthorizeException;
import gmcc.hxs.service.UserInfoService;
import gmcc.hxs.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class UserAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserInfoService userInfoService;

    @Pointcut("execution(public * gmcc.hxs.controller.*.*(..))" +
    "&& !execution(public * gmcc.hxs.controller.UserController.login*(..))")
    public void verify(){
    }

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("[登录校验]Cookie中查不到token");
            throw new UserAuthorizeException();
        }

        String userid = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.isEmpty(userid)){
            log.warn("[登录校验]Redis中查不到token");
            throw new UserAuthorizeException();
        }

        Userinfo userinfo = userInfoService.findUserInfoByUserId(userid);
        request.setAttribute(CurrentUserConstants.CURRENT_USER,userinfo);
    }

}

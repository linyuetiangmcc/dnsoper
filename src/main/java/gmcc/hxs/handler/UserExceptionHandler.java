package gmcc.hxs.handler;

import gmcc.hxs.VO.ResultVO;
import gmcc.hxs.exception.ResponseBankException;
import gmcc.hxs.exception.UserException;
import gmcc.hxs.exception.UserAuthorizeException;
import gmcc.hxs.utils.ResultVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(value = UserAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","Please login as a user first!!");
        return new ModelAndView("user/login",map);
    }

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public ResultVO handerSellException(UserException e ){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){
    }
}

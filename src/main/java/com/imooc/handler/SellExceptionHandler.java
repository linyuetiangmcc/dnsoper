package com.imooc.handler;

import com.imooc.VO.ResultVO;
import com.imooc.exception.ResponseBankException;
import com.imooc.exception.SellException;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.ResultVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SellExceptionHandler {
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","please login as a seller first!!");

        return new ModelAndView("seller/login",map);
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handerSellException(SellException e ){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){
    }
}

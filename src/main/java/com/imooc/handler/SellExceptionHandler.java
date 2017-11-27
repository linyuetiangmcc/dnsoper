package com.imooc.handler;

import com.imooc.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
}

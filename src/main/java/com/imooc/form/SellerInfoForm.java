package com.imooc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class SellerInfoForm {

    @NotEmpty(message = "用户名必填")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;
}

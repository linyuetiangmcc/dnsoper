package gmcc.hxs.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ChangePWDForm {
    @NotEmpty(message = "旧密码不能为空")
    private String oldpassword;
    @NotEmpty(message = "新密码不能为空")
    @Size(min = 6,message = "新密码必须6位以上")
    private String newpassword;
    @NotEmpty(message = "新密码不能为空")
    @Size(min = 6,message = "新密码必须6位以上")
    private String newpasswordconfirm;
}

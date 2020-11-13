package gmcc.hxs.exception;

import gmcc.hxs.enums.ResultEnum;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private Integer code;

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public UserException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}

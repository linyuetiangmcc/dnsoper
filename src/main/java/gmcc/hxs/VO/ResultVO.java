package gmcc.hxs.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = 3773087406155860963L;

    private Integer code;

    private String message;

    private T data;
}

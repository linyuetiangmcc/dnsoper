package gmcc.hxs.enums;

import lombok.Getter;

@Getter
public enum ProductStausEnum implements CodeEnum{
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;

    ProductStausEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

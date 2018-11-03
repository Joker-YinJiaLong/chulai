package com.chulai.domain;

import com.chulai.enums.ResultEnum;
import com.sun.istack.internal.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BaseResult implements Serializable {
    @NotNull
    private Integer code;
    private String msg;
    private Object data;

    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.setResultEnum(ResultEnum.SUCCESS);
        return result;
    }

    public static BaseResult success(Object data) {
        BaseResult result = new BaseResult();
        result.setResultEnum(ResultEnum.SUCCESS);
        result.setData(data);
        return result;
    }

    public static BaseResult failure(ResultEnum resultEnum) {
        BaseResult result = new BaseResult();
        result.setResultEnum(resultEnum);
        return result;
    }

    public static BaseResult failure(ResultEnum resultEnum, Object data) {
        BaseResult result = new BaseResult();
        result.setResultEnum(resultEnum);
        result.setData(data);
        return result;
    }

    private void setResultEnum(ResultEnum resultEnum) {
        this.code = resultEnum.code();
        this.msg = resultEnum.message();
    }
}

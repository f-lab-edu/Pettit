package com.flab.Pettit.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;

    public static<T> CommonResponse<T> res(final int code, final T t ){
        return res(code, null, t);
    }

    public static<T> CommonResponse<T> res(final int code, final String message,final T t){
        return CommonResponse.<T>builder()
                .data(t)
                .message(message)
                .code(code)
                .build();
    }
}


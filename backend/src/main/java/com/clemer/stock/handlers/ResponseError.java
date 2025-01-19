package com.clemer.stock.handlers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {

    private int code;
    private String description;
}

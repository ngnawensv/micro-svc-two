package com.belrose.microsvctwo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {
    String message;
    Integer code;
}

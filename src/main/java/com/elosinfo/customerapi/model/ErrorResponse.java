package com.elosinfo.customerapi.model;

import lombok.Builder;
import lombok.Getter;

//Classe personalizada para retorno de erros em chamadas da API
@Builder
@Getter
public class ErrorResponse {

    private String message;
    private int httpStatus;
    private long timestamp;
    private String xtid;
}

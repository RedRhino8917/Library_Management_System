package org.gfg.minor1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenericResponse <T>{
    private T data;

    private String error;

    private String message; //success/failure

    private String code;


}

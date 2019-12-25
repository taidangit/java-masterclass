package guru.springframework.spring5webfluxrest.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceErrorResponse {

    private Integer status;
    private String message;
    private Long timeStamp;

}

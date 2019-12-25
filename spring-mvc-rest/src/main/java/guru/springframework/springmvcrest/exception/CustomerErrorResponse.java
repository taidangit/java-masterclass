package guru.springframework.springmvcrest.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerErrorResponse {

    private Integer status;
    private String message;
    private Long timeStamp;

}

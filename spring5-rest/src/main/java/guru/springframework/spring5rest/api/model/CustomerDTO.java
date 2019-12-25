package guru.springframework.spring5rest.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long id;

    @ApiModelProperty(required = true)
    private String firstName;

    @ApiModelProperty(required = true)
    private String lastName;

    private String url;
}

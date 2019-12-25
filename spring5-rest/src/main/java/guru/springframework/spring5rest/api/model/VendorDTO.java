package guru.springframework.spring5rest.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorDTO {

    private Long id;

    @ApiModelProperty(required = true)
    private String name;
    private String url;

}

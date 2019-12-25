package guru.springframework.spring5rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VendorListDTO {

    private List<VendorDTO> vendors;
}

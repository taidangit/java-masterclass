package guru.springframework.springrestexample.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {

    private String street;
    private String city;
    private String state;
    private String postcode;
}

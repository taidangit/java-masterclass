package guru.springframework.springrestexample.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ExpirationDate {

    private String date;
    private Integer timezoneType;
    private String timezone;
}

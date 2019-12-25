package guru.springframework.springrestexample.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Name {

    private String title;
    private String first;
    private String last;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}

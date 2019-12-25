package guru.springframework.springrestexample.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {

    private String type;
    private String number;
    private ExpirationDate expirationDate;
    private String iban;
    private String swift;
}

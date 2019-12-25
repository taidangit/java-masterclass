package guru.springframework.springrestexample.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    private String username;
    private String password;
    private String md5;
    private String sha1;
    private String sha256;
}

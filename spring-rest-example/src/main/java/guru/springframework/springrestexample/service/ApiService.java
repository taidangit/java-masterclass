package guru.springframework.springrestexample.service;

import guru.springframework.springrestexample.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);
    Flux<User> getUsers(Mono<Integer> limit);
}

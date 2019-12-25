package guru.springframework.spring5reactivemongorecipeapp.service;


import guru.springframework.spring5reactivemongorecipeapp.domain.UnitOfMeasure;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {

    Flux<UnitOfMeasure> listAllUoms();
}

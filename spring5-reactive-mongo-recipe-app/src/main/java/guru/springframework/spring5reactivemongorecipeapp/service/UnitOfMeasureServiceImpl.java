package guru.springframework.spring5reactivemongorecipeapp.service;

import guru.springframework.spring5reactivemongorecipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5reactivemongorecipeapp.repository.UnitOfMeasureReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureReactiveRepository unitOfMeasureRepository;

    @Autowired
    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Flux<UnitOfMeasure> listAllUoms() {
        return unitOfMeasureRepository.findAll();
    }
}

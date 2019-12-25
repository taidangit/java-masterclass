package guru.springframework.spring5reactivemongorecipeapp.service;

import guru.springframework.spring5reactivemongorecipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5reactivemongorecipeapp.repository.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {


    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureReactiveRepository);
    }

    @Test
    public void listAllUoms() throws Exception {
        //given

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");

        when(unitOfMeasureReactiveRepository.findAll()).thenReturn(Flux.just(uom1, uom2));

        //when
        List<UnitOfMeasure> unitOfMeasureList = unitOfMeasureService.listAllUoms().collectList().block();

        //then
        assertEquals(2, unitOfMeasureList.size());
        verify(unitOfMeasureReactiveRepository, times(1)).findAll();
    }
}
package academy.learnprogramming;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private Random random = new Random();

    @Autowired
    private int maxNumber;

    @Autowired
    private int minNumber;


    // == public methods ==
    @Override
    public int next() {
        // example:  min=5 max=20 -> max-min=15 -> range 0-15 + min -> 5-20
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }

}

package guru.springframework.service;

import org.springframework.beans.factory.annotation.Autowired;

public class PrimarySpanishGreetingService implements GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public String sayGreeting() {
        return greetingRepository.getSpanishGreeting();
    }
}

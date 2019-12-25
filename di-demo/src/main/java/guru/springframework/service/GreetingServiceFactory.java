package guru.springframework.service;

public class GreetingServiceFactory {

    public GreetingService createGreetingService(String lang) {
        switch (lang) {
            case "en":
                return new PrimaryGreetingService();
            case "de":
                return new PrimaryGermanGreetingService();
            case "es":
                return new PrimarySpanishGreetingService();
            default:
                return new PrimaryGreetingService();
        }
    }
}

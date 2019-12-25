package guru.springframework;

import guru.springframework.controller.ConstructorInjectedController;
import guru.springframework.controller.MyController;
import guru.springframework.controller.PropertyInjectedController;
import guru.springframework.controller.SetterInjectedController;
import guru.springframework.examplebean.FakeDataSource;
import guru.springframework.examplebean.FakeJmsBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);

        MyController myController = (MyController) ctx.getBean("myController");


        System.out.println(myController.hello());

        PropertyInjectedController propertyInjectedController=
                (PropertyInjectedController) ctx.getBean("propertyInjectedController") ;

        System.out.println(propertyInjectedController.sayHello());

        SetterInjectedController setterInjectedController=
                (SetterInjectedController) ctx.getBean("setterInjectedController");

        System.out.println(setterInjectedController.sayHello());

        ConstructorInjectedController constructorInjectedController=
                (ConstructorInjectedController) ctx.getBean("constructorInjectedController");


        System.out.println(constructorInjectedController.sayHello());

        FakeDataSource fakeDataSource=(FakeDataSource) ctx.getBean(FakeDataSource.class);
        System.out.println(fakeDataSource.getUser()+"-"+fakeDataSource.getPassword()+"-"+fakeDataSource.getUrl());

        FakeJmsBroker fakeJmsBroker = (FakeJmsBroker) ctx.getBean(FakeJmsBroker.class);
        System.out.println(fakeJmsBroker.getUsername()+"-"+fakeJmsBroker.getPassword()+"-"+fakeJmsBroker.getUrl());

    }

}

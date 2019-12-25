package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);
        Employee charming = new Employee("Prince Charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(charming);


        Function<Employee, String> getLastName = employee -> {
            return employee.getName().substring(employee.getName().indexOf(" ") + 1);
        };

        String lastName = getLastName.apply(employees.get(3));
        System.out.println(lastName);

        Function<Employee, String> getFirstName = employee -> {
            return employee.getName().substring(0, employee.getName().indexOf(" "));
        };

        String firstName = getFirstName.apply(employees.get(2));
        System.out.println(firstName);

        System.out.println("===================================================");

        Random random = new Random();
        for (Employee employee : employees) {
            if (random.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));
            } else {
                System.out.println(getAName(getLastName, employee));
            }
        }
        System.out.println("===================================================");

        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> firstName1 = name -> name.substring(0, name.indexOf(" "));
        Function chainedFunction = upperCase.andThen(firstName1);
        System.out.println(chainedFunction.apply(employees.get(0)));

        BiFunction<String, Employee, String> concatAge = (name, employee) -> {
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(1));
        System.out.println(concatAge.apply(upperName, employees.get(1)));

        IntUnaryOperator incBy5 = i -> i + 5;
        System.out.println(incBy5.applyAsInt(10));

        Consumer<String> c1 = s -> System.out.println(s.toUpperCase());
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello, World!");

        System.out.println("========================================================");

        System.out.println("Employees over 30.");
        employees.forEach(employee -> {
            if (employee.getAge() <= 30) {
                System.out.println(employee.getName());
            }
        });


        System.out.println("========================================================");

        printEmployeesByAge(employees, "Employee over 30", employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, "Employee 30 and under", employee -> employee.getAge() <= 30);
        printEmployeesByAge(employees, "Employees younger 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;

        System.out.println(greaterThan15.test(10));//false
        int a = 20;
        System.out.println(greaterThan15.test(a + 5));//true

        System.out.println(greaterThan15.or(lessThan100).test(15));//15<15 and 15<100-> true

        Random random1 = new Random();
        Supplier<Integer> randomSupplier = () -> random1.nextInt(1000);
        for (int i = 0; i < 10; i++) {
            System.out.println(randomSupplier.get());
        }

        employees.forEach(employee -> {
            String lastName1 = employee.getName().substring(employee.getName().indexOf(' ') + 1);
            System.out.println("Last Name is " + lastName1);
        });
    }

    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
    }

    private static void printEmployeesByAge(List<Employee> employees,
                                            String ageText,
                                            Predicate<Employee> ageCondition) {
        System.out.println(ageText);
        System.out.println("===========================================");
        for (Employee employee : employees) {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        }
    }
}

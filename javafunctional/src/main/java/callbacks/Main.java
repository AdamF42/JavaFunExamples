package callbacks;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        hello("Mario", null, value -> {
            System.out.println("lastName not provided for " + value);
        });

        hello2("Mario",
                null,
                () -> System.out.println("lastName not provided"));
    }

    static void hello(String firstName, String lastName, Consumer<String> callback) {
        System.out.println(firstName);
        if (lastName != null) {
            System.out.println(lastName);
        } else {
            callback.accept(firstName);
        }
    }

    static void hello2(String firstName, String lastName, Runnable callback) {
        System.out.println(firstName);
        if (lastName != null) {
            System.out.println(lastName);
        } else {
            callback.run();
        }
    }

// javascript example of callback
/*    function hello(firsrName, lastName, callback) {
        console.log(firtName);
        if (lastName) {
            console.log(lastName);
        } else {
            callback();
        }
    }*/
}

package optionals;

import java.util.Optional;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Object value = Optional.ofNullable(null)
                .orElseGet(() -> "default value");

        System.out.println(value);

        Supplier<IllegalStateException> exception = ()
                -> new IllegalStateException("exception");
        Object valueE = Optional.ofNullable("Hello")
                .orElseThrow(exception);
        System.out.println(valueE);


        Optional.ofNullable("mario.rossi@gmail.it")
                .ifPresent(email -> System.out.println("Sending email to: " + email));

        Optional.ofNullable(null)
                .<Runnable>map(email -> () -> System.out.println("Sending email to: " + email))
                .orElse(() -> System.out.println("Email Not Found"))
                .run();
    }
}

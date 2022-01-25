package tricks;

import java.util.function.Consumer;
import java.util.function.Function;

public final class HelperFunctions {
    public static <T> Function<T, T> peek(Consumer<T> consumer) {
        return t -> { consumer.accept(t); return t; };
    }

//    Usage example
//    getOptionalObject()
//            .map(peek(System.out::println))
//            .map(peek(name -> invokeImportantHttpPostRequest(x)))
//            .map(String::length);
}
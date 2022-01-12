package optionals;

import java.util.function.Consumer;
import java.util.function.Function;

public final class HelperFunctions {
    public static <T> Function<T, T> peek(Consumer<T> consumer) {
        return t -> { consumer.accept(t); return t; };
    }

//    Usage example
//    getOptionalObject()
//            .map(doing(System.out::println))
//            .map(doing(name -> invokeImportantHttpPostRequest(x)))
//            .map(String::length);
}
package tricks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class HelperFunctions {
    /**
     * Returns an identity function, additionally
     * performing the provided action on input element.
     *
     * @param action a <a href="package-summary.html#NonInterference">
     *                 non-interfering</a> action to perform on the element as
     *                 when it is consumed
     * @return an id function that perform some kind of computation using the input value
     *
     * Usage example:
     * <pre>{@code
     * getOptionalObject()
     *      .map(peek(System.out::println))
     *      .map(peek(name -> invokeImportantHttpPostRequest(x)))
     *      .map(String::length);
     * }</pre>
     */
    public static <T> Function<T, T> peek(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        return t -> { action.accept(t); return t; };
    }

    /**
     * Split a List into multiple Lists of chunkSize
     * Throws ArithmeticException if chunkSize is equal to 0
     *
     * @param <T> the type of the list
     * @param list the input list
     * @param chunkSize specify the desired output size of the sub list
     *
     * @return the new List<List<T>>
     */
    public static <T> List<List<T>> partition(List<T> list, int chunkSize) {
        AtomicInteger counter = new AtomicInteger();
        return Optional.ofNullable(list)
                .map(l -> new ArrayList<>(l.stream().collect(Collectors.groupingBy(i -> counter.getAndIncrement() / chunkSize)).values()))
                .orElseGet(ArrayList::new);
    }
}
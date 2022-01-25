package tricks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;


// https://dzone.com/articles/java-8-automatic-memoization#:~:text=Memoization%20applies%20to%20functions.%20Prior%20to%20Java%208%2C,These%20kind%20of%20method%20may%20benefit%20from%20memoization.
public class Memoizer<T, U> {

    private final Map<T, U> cache = new ConcurrentHashMap<>();

    private Memoizer() {}

    private Function<T, U> doMemoize(final Function<T, U> function) {
        return input -> cache.computeIfAbsent(input, function::apply);
    }

    public static <T, U> Function<T, U> memoize(final Function<T, U> function) {
        return new Memoizer<T, U>().doMemoize(function);
    }
}

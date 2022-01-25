package reasultorerror;

import java.util.function.Function;
import java.util.function.Supplier;

public class ResultOrError<R, E> {

    private final R result;
    private final E error;

    private ResultOrError ( R result, E error ) {
        this.result = result;
        this.error = error;
    }

    public static <R, E> ResultOrError<R, E> createResult ( R result ) {
        return new ResultOrError<R, E> ( result, null );
    }

    public static <R, E> ResultOrError<R, E> createError ( E error ) {
        return new ResultOrError<R, E> ( null, error );
    }

    public R getResult() { return result; }

    public E getError() { return error; }

    public boolean isResult() { return error == null; }

    public boolean isError() { return error != null; }

    /**
     * If a result is present, apply the provided ResultOrError-bearing mapping function to it, return that result,
     * otherwise return an error ResultOrError.
     * @param function
     * @param <R2>
     * @return
     */
    public <R2> ResultOrError<R2,E> flatMap(Function<R, ResultOrError<R2,E>> function ) {

        if ( isResult() ) {
            return function.apply ( result );
        } else {
            return createError ( error );
        }
    }

    /**
     * Return the result if present, otherwise invoke other and return the result of that invocation.
     * @param other
     * @return
     */
    public R orElseGet(Supplier<? extends R> other) {
        if (this.isResult()) {
            return this.getResult();
        }
        return other.get();
    }

    public String toString() {

        if ( isResult() ) {
            return "Result: " + result.toString();
        } else {
            return "Error: " + error.toString();
        }
    }
}
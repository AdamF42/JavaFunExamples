package monads.reasultorerror;

import java.util.function.Function;

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

    public <R2> ResultOrError<R2,E> bind ( Function<R, ResultOrError<R2,E>> function ) {

        if ( isResult() ) {
            return function.apply ( result );
        } else {
            return createError ( error );
        }
    }

    public String toString() {

        if ( isResult() ) {
            return "Result: " + result.toString();
        } else {
            return "Error: " + error.toString();
        }
    }
}
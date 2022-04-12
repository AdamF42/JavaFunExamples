package reasultorerror;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class Either<R, E> {

    private final R result;
    private final E error;

    private Either(R result, E error ) {
        this.result = result;
        this.error = error;
    }

    public static <R, E> Either<R, E> createResult (R result ) {
        return new Either<R, E>( result, null );
    }

    public static <R, E> Either<R, E> createError (E error ) {
        return new Either<R, E>( null, error );
    }

    public R getResult() { return result; }

    public E getError() { return error; }

    public boolean isResult() { return error == null; }

    public boolean isError() { return error != null; }

    /**
     * If a result is present, apply the provided ResultOrError-bearing mapping function to it, return that result,
     * otherwise return an error ResultOrError.
     * @param mapper
     * @param <R2>
     * @return
     *
     *
     * If a result is present, apply the provided {@code ResultOrError}-bearing
     * mapping function to it, return that result, otherwise return an empty
     * {@code ResultOrError}.  This method is similar to {@link #map(Function)},
     * but the provided mapper is one whose result is already an {@code ResultOrError},
     * and if invoked, {@code flatMap} does not wrap it with an additional
     * {@code ResultOrError}.
     *
     * @param <R2> The type parameter to the {@code ResultOrError} returned by
     * @param mapper a mapping function to apply to the result, if present
     *           the mapping function
     * @return the result of applying an {@code ResultOrError}-bearing mapping
     * function to the result of this {@code ResultOrError}, if a result is present,
     * otherwise an empty {@code ResultOrError}
     * @throws NullPointerException if the mapping function is null or returns
     * a null result
     */
    public <R2> Either<R2,E> flatMap(Function<R, Either<R2,E>> mapper ) {
        Objects.requireNonNull(mapper);
        if ( isResult() ) {
            return mapper.apply(result);
        } else {
            return Objects.requireNonNull(createError(error));
        }
    }

    /**
     * If a value is present, apply the provided mapping function to it,
     * and if the result is non-null, return an {@code Optional} describing the
     * result.  Otherwise return an empty {@code Optional}.
     *
     * @param <R2> The type of the result of the mapping function
     * @param mapper a mapping function to apply to the result, if present
     * @return an {@code ResultOrError} describing the result of applying a mapping
     * function to the result of this {@code ResultOrError}, if a result is present,
     * otherwise the error {@code ResultOrError}
     *
     * @throws NullPointerException if the mapping function is null
     */
    public <R2> Either<R2, E> map(Function<R, R2> mapper) {
        Objects.requireNonNull(mapper);
        if (isResult()) {
            return createResult(mapper.apply(result));
        } else {
            return createError(error);
        }
    }

    public <E2> Either<R, E2> mapError(Function<E, E2> mapper) {
        Objects.requireNonNull(mapper);
        if (isError()) {
            return createError(mapper.apply(error));
        } else {
            return createResult(result);
        }
    }

    /**
     * Return the result if present, otherwise return {@code other}.
     *
     * @param other the value to be returned if there is no result present, may
     * be null
     * @return the result, if present, otherwise {@code other}
     */
    public R orElse(R other) {
        return isResult() ? result : other;
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

    public Either<E, R> swap() {
        if (this.isResult()) {
            return createError(result);
        }
        return createResult(error);
    }

    public String toString() {

        if ( isResult() ) {
            return "Result: " + result.toString();
        } else {
            return "Error: " + error.toString();
        }
    }
}
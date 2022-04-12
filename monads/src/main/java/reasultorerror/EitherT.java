package reasultorerror;

import java.util.function.Function;


/**
 * Wannabe Transformer Monad...Miss the wrapper monad parameter...
 *
 */
class EitherT<R, E> {

    private Try<Either<R, E>> value;

    public EitherT(Try<Either<R, E>> value) {
        this.value = value;
    }

    public <R2> EitherT<R2, E> map(Function<R, R2> f) {
        return new EitherT<R2, E>(value.map(resOrErr -> resOrErr.map(f)));
    }

    public <R2> EitherT<R2, E> flatMap(Function<R, EitherT<R2, E>> f) {

        return new EitherT<R2, E>(value.flatMap(resOrErr -> {
            if (resOrErr.isResult()) {
                return f.apply(resOrErr.getResult()).value;
            } else {
                return Try.of(() -> Either.createError(resOrErr.getError()));
            }
        }));
    }
}
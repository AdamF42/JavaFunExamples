package reasultorerror;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Try<T> {

    // sealed
    private Try() {
    }

    public abstract T get() throws Throwable;

    public abstract T orElse(T value);

    public abstract <U> Try<U> map(Function<? super T, ? extends U> f);

    public abstract <U> Try<U> flatMap(Function<? super T, Try<U>> f);

    public abstract Try<T> onFailure(Consumer<Throwable> action);

    public abstract Try<T> recoverUnchecked(Function<Throwable, T> action);

    public abstract Try<T> onSuccess(Consumer<T> action);

    public abstract T orElseGet(Supplier<? extends T> other);

    public static <T> Try<T> of(Supplier<T> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        try {
            return new Success<>(supplier.get());
        } catch (Throwable t) {
            return new Failure<>(t);
        }
    }

    public static final class Failure<T> extends Try<T> {
        private final Throwable e;

        Failure(Throwable e) {
            this.e = e;
        }

        @Override
        public T get() throws Throwable {
            throw e;
        }

        @Override
        public T orElse(T value) {
            return value;
        }

        @Override
        public <U> Failure<U> flatMap(Function<? super T, Try<U>> f) {
            Objects.requireNonNull(f);
            return new Failure<>(e);
        }

        @Override
        public Try<T> onFailure(Consumer<Throwable> action) {
            action.accept(e);
            return this;
        }

        @Override
        public Try<T> onSuccess(Consumer<T> action) {
            return this;
        }

        @Override
        public <U> Failure<U> map(Function<? super T, ? extends U> f) {
            Objects.requireNonNull(f);
            return new Failure<>(e);
        }

        @Override
        public T orElseGet(Supplier<? extends T> other) {
            return other.get();
        }

        @Override
        public Try<T> recoverUnchecked(Function<Throwable, T> action) {
            return new Success<T>(action.apply(e));
        }
    }

    public static final class Success<T> extends Try<T> {
        private final T value;

        Success(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public T orElse(T value) {
            return this.value;
        }

        @Override
        public <U> Try<U> flatMap(Function<? super T, Try<U>> f) {
            Objects.requireNonNull(f);
            try {
                return f.apply(value);
            } catch (Throwable e) {
                return new Failure<>(e);
            }
        }

        @Override
        public Try<T> onFailure(Consumer<Throwable> action) {
            return this;
        }

        @Override
        public Try<T> onSuccess(Consumer<T> action) {
            action.accept(value);
            return this;
        }

        @Override
        public <U> Try<U> map(Function<? super T, ? extends U> f) {
            Objects.requireNonNull(f);
            try {
                return new Success<>(f.apply(value));
            } catch (Throwable e) {
                return new Failure<>(e);
            }
        }

        @Override
        public T orElseGet(Supplier<? extends T> other) {
            return value;
        }

        @Override
        public Try<T> recoverUnchecked(Function<Throwable, T> action) {
            return this;
        }
    }
}

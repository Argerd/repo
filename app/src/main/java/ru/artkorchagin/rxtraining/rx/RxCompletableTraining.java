package ru.artkorchagin.rxtraining.rx;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.artkorchagin.rxtraining.exceptions.ExpectedException;

/**
 * @author Arthur Korchagin (artur.korchagin@simbirsoft.com)
 * @since 20.11.18
 */
public class RxCompletableTraining {

    /* Тренировочные методы */

    /**
     * Выполнение метода {@link #havyMethod()} внутри {@link Completable} и вызов {@code onComplete}
     *
     * @return {@link Completable}, который вызывает {@link #havyMethod()}
     */
    Completable callFunction() {
        return Completable.create(emitter -> {
            havyMethod();
            emitter.onComplete();
        });
    }

    /**
     * Завершить последовательность, если {@code checkSingle} эммитит {@code true} или эммитит
     * ошибку, если {@code checkSingle} эммитит {@code false}
     *
     * @param checkSingle @{link Single} который эммитит {@code true} или {@code false}
     * @return {@code Completable}
     */
    Completable completeWhenTrue(Single<Boolean> checkSingle) {
        return checkSingle.flatMapCompletable(item -> Completable.create(emitter -> {
            if (item) emitter.onComplete();
            emitter.onError(new ExpectedException());
        }));
    }

    /* Вспомогательные методы */

    /**
     * Тяжёлый метод
     * (Вспомогательный метод! Не изменять!)
     */
    void havyMethod() {
        // Выполнение операций
    }
}

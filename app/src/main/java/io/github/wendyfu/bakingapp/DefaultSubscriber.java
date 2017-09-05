package io.github.wendyfu.bakingapp;

import rx.Subscriber;

/**
 * @author wendy
 * @since Dec 20, 2015.
 */
public class DefaultSubscriber<T> extends Subscriber<T> {

    @Override public void onCompleted() {
        // do nothing by default.
    }

    @Override public void onError(Throwable e) {
        // do nothing by default.
    }

    @Override public void onNext(T t) {
        // do nothing by default.
    }
}

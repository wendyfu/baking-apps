package io.github.wendyfu.bakingapp;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase<P, O> {

    protected final Scheduler executionScheduler, postExecutionScheduler;

    private Subscription subscription;

    protected UseCase(Scheduler executionScheduler, Scheduler postExecutionScheduler) {
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link
     * UseCase}.
     */
    protected abstract Observable<O> buildUseCaseObservable(P parameter);

    public Observable<O> executionObservable(P parameter) {
        return this.buildUseCaseObservable(parameter)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler);
    }

    /**
     * Executes the current use case using specified execution and post execution thread.
     * Unsubscribing it first to ensure no memory is leaked.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link
     * #buildUseCaseObservable(Object)}.
     */
    @SuppressWarnings("unchecked") public void execute(Subscriber<O> useCaseSubscriber,
        P parameter) {
        unsubscribe();
        this.subscription = executionObservable(parameter).subscribe(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (subscription == null) return;
        subscription.unsubscribe();
    }

    public boolean isSubscribed() {
        return subscription != null && !subscription.isUnsubscribed();
    }
}

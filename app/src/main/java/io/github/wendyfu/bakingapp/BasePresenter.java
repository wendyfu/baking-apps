package io.github.wendyfu.bakingapp;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

public interface BasePresenter<V> {

    void setView(V View);

    void dropView();
}

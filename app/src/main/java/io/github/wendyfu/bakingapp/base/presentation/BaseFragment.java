package io.github.wendyfu.bakingapp.base.presentation;

import android.support.v4.app.Fragment;

import io.github.wendyfu.bakingapp.di.HasComponent;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

public class BaseFragment extends Fragment {

    @SuppressWarnings("unchecked") protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}

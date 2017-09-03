package io.github.wendyfu.bakingapp.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author wendy
 * @since Sep 02, 2017.
 */

public class ActivityUtil {

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment,
        int containerId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commit();
    }
}

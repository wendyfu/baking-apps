package io.github.wendyfu.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import io.github.wendyfu.bakingapp.R;

/**
 * @author wendy
 * @since Sep 16, 2017.
 */

public class SimpleRecipeWidgetProvider extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
        int appWidgetId, String title, String content) {
        RemoteViews views =
            new RemoteViews(context.getPackageName(), R.layout.widget_simple_recipe);
        views.setTextViewText(R.id.text_ingredient_title, title);
        views.setTextViewText(R.id.text_ingredient_list, content);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

package io.github.wendyfu.bakingapp.di.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.github.wendyfu.bakingapp.BuildConfig;
import io.github.wendyfu.bakingapp.base.presentation.BaseActivity;
import io.github.wendyfu.bakingapp.data.model.RecipeList;
import io.github.wendyfu.bakingapp.data.model.deserializer.RecipeListDeserializer;
import io.github.wendyfu.bakingapp.data.source.network.RecipeListService;
import io.github.wendyfu.bakingapp.di.ActivityScoped;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

@Module public class ActivityModule {

    public static final String ACTIVITY_CONTEXT = "activity_context";

    private final AppCompatActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides @ActivityScoped AppCompatActivity activity() {
        return this.activity;
    }

    @Provides @ActivityScoped @Named(ACTIVITY_CONTEXT) Context provideAppContext() {
        return this.activity;
    }

    @Provides @ActivityScoped RecipeListService provideRecipeListService() {
        Gson gson =
            new GsonBuilder().registerTypeAdapter(RecipeList.class, new RecipeListDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.RECIPE_LIST_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

        return retrofit.create(RecipeListService.class);
    }
}

package io.github.wendyfu.bakingapp.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import io.github.wendyfu.bakingapp.BuildConfig;
import io.github.wendyfu.bakingapp.data.model.RecipeList;
import io.github.wendyfu.bakingapp.data.model.deserializer.RecipeListDeserializer;
import io.github.wendyfu.bakingapp.data.source.RecipeListService;
import io.github.wendyfu.bakingapp.di.ActivityScoped;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wendy
 * @since Sep 04, 2017.
 */

@Module public class RecipeModule {

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

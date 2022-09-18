package ru.geekbrains.popularlibraries.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.popularlibraries.BuildConfig

object NetworkProvider {

    val usersApi by lazy {
        createRetrofit().create(UsersApi::class.java)
    }

    fun createGson() =
        GsonBuilder()
//        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()


    fun createRetrofit() =
        Retrofit.Builder()
            .client(createClient())
            .baseUrl(BuildConfig.SERVER_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .build()

    fun createClient() = OkHttpClient.Builder().build()
}
package org.test.testutest.data.net

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import org.test.testutest.util.FieldExclusionStrategy
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppApi{

    lateinit var retrofit:Retrofit

    fun init(url:HttpUrl){
        retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .setExclusionStrategies(FieldExclusionStrategy())
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    inline fun<reified T> buildApi():T = retrofit.create(T::class.java)
}
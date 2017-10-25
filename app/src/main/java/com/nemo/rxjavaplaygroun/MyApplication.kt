package com.nemo.rxjavaplaygroun

import android.app.Application
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by jakub on 25.10.17.
 */
class MyApplication : Application() {

  lateinit var networkService: NetworkServiceInterface

  override fun onCreate() {
    super.onCreate()
    networkService = createRetrofitInterface()
  }

  fun createRetrofitInterface(): NetworkServiceInterface {
    val jsonObjectMapper = ObjectMapper().apply {
      registerKotlinModule()
      setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
      configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    val okHttpClient = OkHttpClient.Builder().apply {
      addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }.build()

    val retrofit = Retrofit.Builder().apply {
      baseUrl("https://api.myjson.com/bins/")
      client(okHttpClient)
      addConverterFactory(JacksonConverterFactory.create(jsonObjectMapper))
      addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }.build()

    return retrofit.create(NetworkServiceInterface::class.java)
  }

}
package com.nemo.rxjavaplaygroun

import android.support.annotation.Keep
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by jakub on 25.10.17.
 */
interface NetworkServiceInterface {

  @GET("16wgd3")
  fun getNameAndNumberJson(): Flowable<NameAndNumberModel>
}

@Keep
data class NameAndNumberModel(val name: String,
                              val numbers: List<Int>)

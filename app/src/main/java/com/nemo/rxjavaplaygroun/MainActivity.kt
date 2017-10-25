package com.nemo.rxjavaplaygroun

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  lateinit var networkServce: NetworkServiceInterface

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    networkServce = (application as MyApplication).networkService

    findViewById(R.id.uiBtn).setOnClickListener {
      // todo do RxJava/retrofit magic!
    }
  }
}

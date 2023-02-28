package com.example.jsonplaceholder.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JsonInterfaceObject {
    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                   .baseUrl("https://jsonplaceholder.typicode.com/")
                   .build()
                   .create(JsonInterface::class.java)
}
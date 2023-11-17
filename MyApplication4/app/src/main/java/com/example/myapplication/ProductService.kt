package com.example.myapplication

import com.example.myapplication.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("/dogins/{id}")
    fun findById(@Path("id") id: String): Call<Product>
}
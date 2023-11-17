package com.example.myapplication

import com.example.myapplication.models.Product
import com.example.myapplication.models.Update
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ProductService {

    @GET("/dogins/{id}")
    fun findById(@Path("id") id: String): Call<Product>

    @GET("/dogins")
    fun findAll(): Call<List<Product>>

    @GET("/dogins/{id}/quantity")
    fun getProductQuantity(@Path("id") id: String): Call<Int>

    //temporary patch
    @PATCH("/dogins/{id}")
    fun updateProductFields(@Body fields: List<Update>): Call<Product>
}
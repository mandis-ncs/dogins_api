package com.example.myapplication

import com.example.myapplication.models.Product
import com.example.myapplication.models.Update
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ProductService {

    @GET("/dogins/products/{id}")
    fun findById(@Path("id") id: String): Call<Product>

    @GET("/dogins/products")
    fun findAll(): Call<List<Product>>

    @GET("/dogins/products/{id}/quantity")
    fun getProductQuantity(@Path("id") id: String): Call<Int>

    //temporary patch
    @PATCH("/dogins/products")
    fun updateProductFields(@Body fields: List<Update>): Call<ResponseBody>
}
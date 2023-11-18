package com.example.myapplication.repository

import com.example.myapplication.ProductService
import com.example.myapplication.models.Update
import retrofit2.http.Body

class ProductRepository(private val api : ProductService) {

    suspend fun updateProduct(fields: List<Update>) {
        api.updateProductFields(fields)
    }
}
package com.example.myapplication.models

import org.bson.types.Binary
import java.util.Objects


data class Product(
    val id: String? = null,
    val productCategory: String,
    val productColor: String,
    val productDescription: String,
    val productImages: List<ProductImage>, //should be binary
    val brandName: String,
    val productPrice: Double,
    val productStock: Int,
    val size: String,
    val productName: String,
)

data class ProductImage(
    val type: Long,
    val data: String,
)
package com.example.myapplication.models


data class Product(
    val id: String? = null,

    val productCategory: String,
    val universalProductCode: String,
    val productColor: String,
    val productDescription: String,
    val productImages: List<ByteArray>,
    val brandName: String,
    val productWeight: String,
    val productPrice: String,
    val productStock: String,
    val size: String,
    val minStock: String,
    val amountSales: Int,
    val productName: String,
    val productColors: List<String>
)
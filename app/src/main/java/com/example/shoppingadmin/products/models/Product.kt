package com.example.shoppingadmin.products.models

data class Product(
    var displayImage : String ?= null,
    var descriptionImage : List<String>?= null,
    var productName : String?= null ,
    var productDescription : String?= null,
    var discountPercent : Long?= null ,
    var size : List<String> ?= null,
    var color : List<ProductColor> ?= null,
    var price : Long ?= null
    )
data class  ProductColor(val colorName : String, val colorCode :Long)
package com.example.findyourway.Data

import android.graphics.Bitmap

data class Post(
    var name: String = "",
    var date: String = "",
    var Description: String = "",
    var Image: Bitmap? = null
)

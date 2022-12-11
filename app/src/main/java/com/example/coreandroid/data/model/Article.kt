package com.example.coreandroid.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article (
    @Expose
    @SerializedName("created_at")
    val createdAt: String?,
    @Expose
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("content")
    val content: String?
        )
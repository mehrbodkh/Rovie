package com.example.rovie.data.models

import com.google.gson.annotations.SerializedName

data class Movies (
    @SerializedName("data") val movies : List<Movie>,
    @SerializedName("metadata") val metadata : Metadata
)
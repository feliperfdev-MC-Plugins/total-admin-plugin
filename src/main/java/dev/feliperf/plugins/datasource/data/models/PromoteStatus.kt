package dev.feliperf.plugins.datasource.data.models

import com.google.gson.annotations.SerializedName

data class PromoteStatus(
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("permission")
    var permission: String,

    @SerializedName("errorMessage")
    var errorMessage: String?,
)
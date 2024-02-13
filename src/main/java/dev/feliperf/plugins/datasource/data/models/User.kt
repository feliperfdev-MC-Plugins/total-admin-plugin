package dev.feliperf.plugins.datasource.data.models

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id")
        var id: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("password")
        var password: String,

        @SerializedName("permission")
        var permission: String,

        @SerializedName("joinedAt")
        var joinedAt: String,
)


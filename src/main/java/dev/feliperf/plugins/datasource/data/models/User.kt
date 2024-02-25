package dev.feliperf.plugins.datasource.data.models

import com.google.gson.annotations.SerializedName

// TODO: Create `logged` field

data class User(
        @SerializedName("id")
        var id: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("permission")
        var permission: String,

        @SerializedName("joinedAt")
        var joinedAt: String,
)


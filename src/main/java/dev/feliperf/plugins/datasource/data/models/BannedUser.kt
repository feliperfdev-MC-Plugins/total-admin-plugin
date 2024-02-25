package dev.feliperf.plugins.datasource.data.models

import com.google.gson.annotations.SerializedName

data class BannedUser(
        @SerializedName("banId")
        var banId: String,

        @SerializedName("id")
        var id: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("reason")
        var reason: String,

        @SerializedName("durationInDays")
        var durationInDays: Int,

        @SerializedName("bannedAt")
        var bannedAt: String,
)
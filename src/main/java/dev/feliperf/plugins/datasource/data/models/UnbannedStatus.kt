package dev.feliperf.plugins.datasource.data.models

import com.google.gson.annotations.SerializedName

data class UnbannedStatus(
    @SerializedName("playerName")
    var playerName: String,

    @SerializedName("unbanned")
    var unbanned: String,

    @SerializedName("unbannedAt")
    var unbannedAt: String?,

    @SerializedName("errorMessage")
    var errorMessage: String?,
)
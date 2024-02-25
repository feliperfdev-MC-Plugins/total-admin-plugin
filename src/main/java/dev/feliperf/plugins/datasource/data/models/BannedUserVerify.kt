package dev.feliperf.plugins.datasource.data.models

import com.google.gson.annotations.SerializedName

data class BannedUserVerify(
        @SerializedName("name")
        var name: String,

        @SerializedName("banTimeRemeaning")
        var banTimeRemeaning: Int,
)

package dev.feliperf.plugins.datasource.data.models

import com.google.gson.annotations.SerializedName

data class Auth(
        @SerializedName("logged")
        var logged: Boolean,
)
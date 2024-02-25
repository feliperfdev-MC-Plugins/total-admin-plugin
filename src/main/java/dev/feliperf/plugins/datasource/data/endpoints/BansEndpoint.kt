package dev.feliperf.plugins.datasource.data.endpoints

import dev.feliperf.plugins.datasource.data.models.BannedUser
import dev.feliperf.plugins.datasource.data.models.BannedUserVerify
import dev.feliperf.plugins.datasource.data.models.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BansEndpoint {

    @GET("banned")
    fun getBannedUsers(
    ) : retrofit2.Call<List<BannedUser>>

    @GET("banned/verify")
    fun verifyBannedUsers(
    ) : retrofit2.Call<List<BannedUserVerify>>

    @POST("ban")
    fun banUser(
            @Query("id") id: String,
            @Query("name") name: String,
            @Query("reason") reason: String,
            @Query("durationInDays") durationInDays: Int,
    ) : retrofit2.Call<BannedUser>
}
package dev.feliperf.plugins.datasource.data.endpoints

import dev.feliperf.plugins.datasource.data.models.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersEndpoint {

    @GET("users")
    fun getUsers(
    ) : retrofit2.Call<List<User>>

    @GET("users/{name}")
    fun getSpecificUser(
            @Path("name") name: String
    ) : retrofit2.Call<User>

    @POST("users")
    fun createUser(
            @Query("name") name: String,
            @Query("password") password: String,
            @Query("permission") permission: String,
    ) : retrofit2.Call<User>
}
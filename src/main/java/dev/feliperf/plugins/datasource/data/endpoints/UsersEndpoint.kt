package dev.feliperf.plugins.datasource.data.endpoints

import dev.feliperf.plugins.datasource.data.models.Auth
import dev.feliperf.plugins.datasource.data.models.PromoteStatus
import dev.feliperf.plugins.datasource.data.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
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

    @PATCH("login")
    fun login(
            @Body credentials: HashMap<String, String>,
    ) : retrofit2.Call<Auth>

    @PATCH("disconnect")
    fun disconnect(
            @Body credentials: HashMap<String, String>,
    ) : retrofit2.Call<Auth>

    @PATCH("promote")
    fun promote(
        @Body credentials: HashMap<String, String>,
    ) : retrofit2.Call<PromoteStatus>
}
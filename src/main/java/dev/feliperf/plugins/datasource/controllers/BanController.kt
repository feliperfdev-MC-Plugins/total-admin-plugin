package dev.feliperf.plugins.datasource.controllers

import dev.feliperf.plugins.datasource.data.RetrofitNetwork
import dev.feliperf.plugins.datasource.data.endpoints.BansEndpoint
import dev.feliperf.plugins.datasource.data.models.BannedUser
import dev.feliperf.plugins.datasource.data.models.BannedUserVerify
import dev.feliperf.plugins.datasource.data.models.UnbannedStatus

class BanController {
    companion object {
        private const val BASE_URL = "http://localhost:8080"
        private val client = RetrofitNetwork.getRetrofitInstance(BASE_URL)
        private val endpoint = client.create(BansEndpoint::class.java)

        fun banUser(id: String, name: String, reason: String, durationInDays: Int) : BannedUser? {
            val callback = endpoint.banUser(id, name, reason, durationInDays)
            val body = callback.execute().body()
            return body
        }

        fun unbanUser(id: String, name: String) : UnbannedStatus? {
            val callback = endpoint.unbanUser(id, name)
            val body = callback.execute().body()
            return body
        }

        fun verifyBannedUsers() : List<BannedUserVerify>? {
            val callback = endpoint.verifyBannedUsers()
            val body = callback.execute().body()
            return body
        }
    }
}
package dev.feliperf.plugins.datasource.controllers

import dev.feliperf.plugins.datasource.data.RetrofitNetwork
import dev.feliperf.plugins.datasource.data.endpoints.UsersEndpoint
import dev.feliperf.plugins.datasource.data.models.User

class UsersController {
    companion object {
        private const val BASE_URL = "http://localhost:8080"
        private val client = RetrofitNetwork.getRetrofitInstance(BASE_URL)
        private val endpoint = client.create(UsersEndpoint::class.java)

        fun fetch() : List<User> {
            val callback = endpoint.getUsers()
            val body = callback.execute().body()
            return body ?: listOf()
        }

        fun register(name: String, password: String, permission: String) : User? {
            val callback = endpoint.createUser(name, password, permission)
            val body = callback.execute().body()
            return body
        }

        fun getSpecificUser(name: String) : User? {
            val callback = endpoint.getSpecificUser(name)
            val body = callback.execute().body()
            return body
        }
    }


}
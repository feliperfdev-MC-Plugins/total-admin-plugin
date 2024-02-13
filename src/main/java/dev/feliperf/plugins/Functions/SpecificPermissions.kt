package dev.feliperf.plugins.Functions

import dev.feliperf.plugins.datasource.controllers.UsersController

class SpecificPermissions {

    companion object {
        fun canBeAdmin(name: String) : Boolean {

            val user = UsersController.getSpecificUser(name)

            return user != null && user.permission == "ADMIN"
        }
    }

}
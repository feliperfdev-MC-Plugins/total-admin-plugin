package dev.feliperf.plugins.Functions

import dev.feliperf.plugins.datasource.controllers.UsersController
import dev.feliperf.plugins.utils.models.UserPermission

class SpecificPermissions {

    companion object {
        fun canBeAdmin(name: String) : Boolean {

            val user = UsersController.getSpecificUser(name)

            return user != null && user.permission == UserPermission.admin
        }
    }

}
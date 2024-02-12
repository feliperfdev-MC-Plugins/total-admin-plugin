package dev.feliperf.plugins.Contants

class SpecificPermissions {

    companion object {
        private val ADMINPERMISSION = mutableListOf("feliper_02")
        fun canBeAdmin(name: String) : Boolean {
            return ADMINPERMISSION.contains(name)
        }
    }

}
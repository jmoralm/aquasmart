package com.example.aquasmart.models

data class Cliente(
    var name: String,
    var phone: String,
    var email: String,
    var adress: String,
    var reports: MutableList<Reports>
) {
    override fun toString(): String {
        return "Cliente(name='$name', phone='$phone', email='$email', adress='$adress')"
    }
}

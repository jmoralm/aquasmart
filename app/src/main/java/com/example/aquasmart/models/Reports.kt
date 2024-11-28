package com.example.aquasmart.models

import java.time.LocalDate

data class Reports(
    var name: String,
    var clientName: String,
    var date: LocalDate,
    var description: String,
    var image: String

) {
    override fun toString(): String {
        return "Reports(name='$name', client='${clientName}', dale='$date', description='$description', image='$image')"
    }
}

package org.example.tableorder.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "RESERVATION")
class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var storeId: Long? = null

    var reservationDate: LocalDateTime? = null

    var arrived_status: Boolean = false


}
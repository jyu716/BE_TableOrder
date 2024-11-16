package org.example.tableorder.repository

import org.example.tableorder.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository: JpaRepository<Reservation, Long>{

    fun findByStoreId(storeId: Long) : List<Reservation>
}
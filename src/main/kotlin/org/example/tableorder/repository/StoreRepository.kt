package org.example.tableorder.repository

import org.example.tableorder.model.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : JpaRepository<Store, Long>{
    fun findByName(name: String?): List<Store>
}
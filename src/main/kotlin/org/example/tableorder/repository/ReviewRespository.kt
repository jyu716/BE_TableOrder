package org.example.tableorder.repository

import org.example.tableorder.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRespository : JpaRepository<Review, Long> {
    fun findByStoreId(storeId: Long): List<Review>
}
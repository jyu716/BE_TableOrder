package org.example.tableorder.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "REVIEW")
class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var reservationId: Long? = null

    var storeId: Long? = null

    // 5점만점
    var rating: Double? = null

    // 리뷰 글
    var comment: String? = null

    // 작성일
    var writeTime: LocalDateTime? = null
}
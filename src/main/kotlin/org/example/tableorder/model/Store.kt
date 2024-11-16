package org.example.tableorder.model

import jakarta.persistence.*

@Entity(name = "STORE")
class Store (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String? = null,  // 매장 명
    var location: String? = null, // 위치
    var storeInfo: String? = null, // 매장 설명

    @OneToMany
    var review: List<Review>? = null

) {
    constructor() : this(name = "", location = "", storeInfo = "", review = listOf())
}
package org.example.tableorder.controller

import org.example.tableorder.model.Reservation
import org.example.tableorder.model.Review
import org.example.tableorder.model.Store
import org.example.tableorder.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService){

    // 매장 검색
    @GetMapping("/stores")
    fun searchStore(@RequestParam(name = "name", required = false) name:String?): ResponseEntity<List<Store>> {
        return ResponseEntity.ok(userService.searchStores(name))
    }

    // 매장 예약 - 회원가입 필수
    @PostMapping("/stores/reservation")
    fun reservationStore(@RequestParam(name = "storeId", required = false) storeId:Long, @RequestParam(name = "reserveDate", required = false)reserveDate: String): ResponseEntity<Reservation> {
        return ResponseEntity.ok(userService.reserveStore(storeId, reserveDate))
    }

    // 예약 확인
    @GetMapping("/stores/reservation")
    fun checkReservation(@RequestParam(name = "reservationId", required = false) reservationId:Long): ResponseEntity<Optional<Reservation>> {
        return ResponseEntity.ok(userService.checkReservation(reservationId))
    }

    // 매장 도착확인
    @PutMapping("/stores/reservation")
    fun checkReservationStore(@RequestParam(name = "storeId", required = false) storeId:Long): ResponseEntity<Reservation> {
        return ResponseEntity.ok(userService.checkReserveStore(storeId))
    }

    // 매장 리뷰작성 - 매장 예약 및 사용 후
    @PostMapping("/stores/review")
    fun storeReview(@RequestParam(name = "reservationId", required = false) reservationId:Long, @RequestBody review: Review): ResponseEntity<Any>{
        return ResponseEntity.ok(userService.writeReview(reservationId, review))
    }

}
package org.example.tableorder.service

import org.example.tableorder.Const
import org.example.tableorder.model.Reservation
import org.example.tableorder.model.Review
import org.example.tableorder.model.Store
import org.example.tableorder.repository.ReservationRepository
import org.example.tableorder.repository.ReviewRespository
import org.example.tableorder.repository.StoreRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserService(
    private val storeRepository: StoreRepository,
    private val reviewRepository: ReviewRespository,
    private val reservationRepository: ReservationRepository
) {

    // 매장 검색
    fun searchStores(name: String?): List<Store> {

        if(name.isNullOrEmpty()) { // 매장이름이 없다면 전체 출력

            val stores = storeRepository.findAll()
            stores.forEach{ store ->

                val review = reviewRepository.findByStoreId(store.id!!)

                store.review = review

            }
            return stores

        }else{ // 매장 이름이 있다면 해당 매장과 댓글 출력

            val stores = storeRepository.findByName(name)
            stores.forEach{ store ->
                val review = reviewRepository.findByStoreId(store.id!!)
                store.review = review
            }

            return stores
        }
    }


    // 매장 예약
    fun reserveStore(storeId: Long, reservationDate: String): Reservation {
        val store = storeRepository.findById(storeId)

        if(store.isEmpty){
            throw RuntimeException("Store is not found")
        }

        val reservation = Reservation().apply {
            this.reservationDate = Const.convertStringToDate(reservationDate)
            this.storeId = storeId
        }

        return reservationRepository.save(reservation)
    }

    fun checkReservation(reservationId: Long): Optional<Reservation> {
        return reservationRepository.findById(reservationId)
    }

    // 방문 확인
    fun checkReserveStore(storeId: Long): Reservation {

        val reservations = reservationRepository.findByStoreId(storeId)

        if(reservations.isEmpty()){
            throw RuntimeException("Store is not found")
        }

        val reservation = reservations[0]
        val reserveTime = reservation.reservationDate

        if(!Const.isWithin30Min(reserveTime!!)){
            throw RuntimeException("예약 시간을 확인해 주세요")
        }

        reservation.arrived_status = true

        return reservationRepository.save(reservation)

    }

    // 리뷰 작성
    fun writeReview(reservationId:Long, review: Review): Any {

        val store = storeRepository.findById(review.storeId!!)
        val reservations = reservationRepository.findById(reservationId)


        if(store.isEmpty){
            throw RuntimeException("Store is not found")
        }else if(reservations.isEmpty){
            throw RuntimeException("Reservation is not found")
        }

        if(!reservations.get().arrived_status){
            //throw RuntimeException("댓글은 이용후 작성 가능합니다.")
            return "댓글은 이용 후 작성 가능합니다."
        }else {
            val setReview = Review().apply {
                this.storeId = review.storeId
                this.reservationId = reservationId
                this.rating = review.rating
                this.comment = review.comment
                this.writeTime = LocalDateTime.now()
            }
            return reviewRepository.save(setReview)
        }

    }

}
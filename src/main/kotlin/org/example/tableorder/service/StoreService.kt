package org.example.tableorder.service

import org.example.tableorder.model.Store
import org.example.tableorder.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(private val storeRepository: StoreRepository) {

    // 매장 등록 - 회원가입 필요
    fun registorStore(store: Store): Store{
        return storeRepository.save(store)
    }

}
package org.example.tableorder.controller

import org.example.tableorder.model.Partner
import org.example.tableorder.model.Store
import org.example.tableorder.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/partners")
class PartnerController(private val storeService: StoreService) {


    // 매장 등록
    @PostMapping("/store")
    fun registorStore(@RequestBody store: Store): ResponseEntity<Store> {
        return ResponseEntity.ok(storeService.registorStore(store))
    }
}
package org.example.tableorder.controller

import org.example.tableorder.model.Store
import org.example.tableorder.service.StoreService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/partners")
class PartnerController(private val storeService: StoreService) {


    // 매장 등록
    @PostMapping("/store")
    @PreAuthorize("hasRole('PARTNER')")
    fun registerStore(@RequestBody store: Store): ResponseEntity<Store> {
        return ResponseEntity.ok(storeService.registorStore(store))
    }
}
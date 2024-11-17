package org.example.tableorder.repository

import org.example.tableorder.model.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MemberRepository : JpaRepository<Member, Long> {

    fun findByEmail(username: String): Optional<Member>

    fun existsByEmail(username: String): Boolean
}
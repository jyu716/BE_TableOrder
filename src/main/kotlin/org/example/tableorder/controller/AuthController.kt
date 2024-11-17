package org.example.tableorder.controller

import org.example.tableorder.model.Auth
import org.example.tableorder.security.TokenProvider
import org.example.tableorder.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val memberService: MemberService, private val tokenProvider: TokenProvider) {

    @PostMapping("/signup")
    fun signup(@RequestBody request: Auth.SignUp): ResponseEntity<Any> {
        val result = memberService.register(request)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/signin")
    fun signin(@RequestBody request: Auth.SignIn): ResponseEntity<Any> {

        // 패스워드 검증
        val member = memberService.authenticate(request)

        // 토큰 생성 후 반환
        val token = tokenProvider.generateToken(member.username, member.roles)

        return ResponseEntity.ok(token)

    }


}
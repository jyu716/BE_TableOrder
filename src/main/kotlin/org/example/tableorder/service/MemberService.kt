package org.example.tableorder.service

import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.example.tableorder.model.Auth
import org.example.tableorder.model.Member
import org.example.tableorder.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Slf4j
@Service
@AllArgsConstructor
class MemberService(
    private val passwordEncoder: PasswordEncoder,
    private val memberRepository: MemberRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return memberRepository.findByEmail(username!!)
            .orElseThrow { UsernameNotFoundException("User with username $username not found") }
    }


    fun register(member: Auth.SignUp): Member {
        System.out.println("inner register")
        val exists = memberRepository.existsByEmail(member.email)
        if(exists) {
            throw RuntimeException("User with username ${member.email} already exists")
        }

        member.pwd = passwordEncoder.encode(member.pwd)
        return memberRepository.save(member.toEntity())
    }

    // 패스워드 인증
    fun authenticate(member: Auth.SignIn): Member {
        val user = memberRepository.findByEmail(member.email).orElseThrow {throw RuntimeException("존재 하지 않는 ID 입니다.")  }

        if(!passwordEncoder.matches(member.pwd, user.pwd)){
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }

        return user
    }
}
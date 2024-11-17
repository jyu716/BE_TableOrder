package org.example.tableorder.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ClaimsMutator
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import lombok.RequiredArgsConstructor
import lombok.Value
import org.example.tableorder.service.MemberService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*

@Component
@RequiredArgsConstructor
class TokenProvider(private val memberService: MemberService) {
    companion object {
        val TOKEN_EXPIRE_TIME = 1000*60*60
        val KEY_ROLES = "roles"
    }

    var secureKey: String? = "d9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85"


    // 토큰 생성
    fun generateToken(username:String, roles: List<String>): String {

        var claims = Jwts.claims().setSubject(username)
        claims[KEY_ROLES] = roles

        var now = Date()
        var expiredDate = Date(now.time + TOKEN_EXPIRE_TIME)

        return Jwts.builder().setClaims(claims)
                    .setIssuedAt(now) // 토큰 생성 시간
                    .setExpiration(expiredDate) // 토큰 만료 시간
                    .signWith(SignatureAlgorithm.HS512, secureKey) // 사용 암호화 알고리즘, 비밀키
                    .compact()
    }

    fun getUsername(token: String): String {
        return parseClaims(token).subject
    }

    fun validateToken(token: String): Boolean {
        if(!StringUtils.hasText(token)) return false

        var claims = parseClaims(token)
        return !claims.expiration.before(Date())
    }

    private fun parseClaims(token: String): Claims {
       try {
           return Jwts.parserBuilder().setSigningKey(secureKey).build().parseClaimsJws(token).body
       }catch (e: ExpiredJwtException){
           return e.claims
       }
    }

    fun getAuthentication(jwt: String): Authentication {
        val userDetails = memberService.loadUserByUsername(getUsername(jwt))

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)

    }
}
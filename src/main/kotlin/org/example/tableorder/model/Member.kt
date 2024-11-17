package org.example.tableorder.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity(name = "MEMBER")
@NoArgsConstructor
@AllArgsConstructor
class Member : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0

    var email : String = ""

    var pwd: String = ""

    @ElementCollection(fetch = FetchType.EAGER)
    var roles: List<String> = listOf()


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.map { SimpleGrantedAuthority(it) }.toMutableList()
    }


    override fun getPassword(): String {
       return pwd
    }

    override fun getUsername(): String {
       return email
    }


}
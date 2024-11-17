package org.example.tableorder.model

import lombok.Data

class Auth {

    @Data
    data class SignIn(
        val email :String,
        val pwd :String
    )

    data class SignUp(
        val email :String,
        var pwd :String,
        val roles: List<String>

    ) {
        fun toEntity(): Member {

            val member = Member()
            member.email = email
            member.pwd = pwd
            member.roles = roles

            return member
        }
    }

}
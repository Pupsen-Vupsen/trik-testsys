package trik.testsys.security.encoders

import org.springframework.security.crypto.password.PasswordEncoder

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SHA256PasswordEncoder : PasswordEncoder {

    override fun encode(rawPassword: CharSequence): String {
        return hashWithSHA512(rawPassword.toString())
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        val hashedPassword = encode(rawPassword)
        return encodedPassword == hashedPassword
    }

    private fun hashWithSHA512(input: String): String {
        try {
            val md = MessageDigest.getInstance("SHA-256")

            return md.digest(input.toByteArray()).fold("") { str, it -> str + "%02x".format(it) }
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Bad algorithm")
        }

    }
}
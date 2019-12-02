package com.kurotkin.directlotapp.domain.crypto

import java.io.IOException

object CryptoPouch {

    // Зашифрованный токен
    private const val a1 = "ugmMzwA5/w/w2xnrm702u7LGo5mlHQnh2QZfEM90PX+ry8OIXVaPybsLtYoD8eflkjjWKGoY9Xn0" +
            "Fk7cP8yjkhhiJ1v5eA12AE+DURCAnYq8yFjwVzkIjNnb+2lSWfNCuRqJA5qQmmioxxZpHZn5lBQz" +
            "1cWrEe45kfKwvhcpCGw="

    private const val publicKey = "rO0ABXNyAC1jb20uYW5kcm9pZC5vcmcuY29uc2NyeXB0Lk9wZW5TU0xSU0FQdWJsaWNLZXkBtW2K" +
            "WyYApAMAAkwAB21vZHVsdXN0ABZMamF2YS9tYXRoL0JpZ0ludGVnZXI7TAAOcHVibGljRXhwb25l" +
            "bnRxAH4AAXhwc3IAFGphdmEubWF0aC5CaWdJbnRlZ2VyjPyfH6k7+x0DAAJJAAZzaWdudW1bAAlt" +
            "YWduaXR1ZGV0AAJbQnhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAAF1cgACW0Ks" +
            "8xf4BghU4AIAAHhwAAAAgNOF3w/5mZ9hO77P16tUCzhPCHe07zYv9lPFaYmQFEqQiTkrT/fT30XS" +
            "FIrZaaKYkUCzOEOTLFAwEXiGkh86YsBglR5FLOKasfBr71z9AC4QqObvhAREBNWBZudnbAbNdq7r" +
            "wZ9QxqgpTRZCuofwyXWRK9cUokktVtNnkXR/SMyleHNxAH4AAwAAAAF1cQB+AAcAAAADAQABeHg="

    fun getToken() : String{
        val cryptoMaker = CryptoMakerImp(publicKey)
        return cryptoMaker.decode(a1) ?: throw IOException("No token")
    }
}
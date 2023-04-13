package exchange.az.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

suspend fun configService(url: String): HttpResponse {

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    return client.get(url)

}
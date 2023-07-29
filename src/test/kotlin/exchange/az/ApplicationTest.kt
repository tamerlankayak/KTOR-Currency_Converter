package exchange.az

import exchange.az.model.Currencies
import exchange.az.plugins.configureTemplating
import exchange.az.service.configService
import exchange.az.util.Constants
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
//testing
class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureTemplating()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

    @Test
    fun checkApi() = testApplication {
        val response: HttpResponse = configService(Constants.BASE_URL + Constants.LIST_OF_CURRENCIES)
        val curr: Currencies = response.body()
        assertNotNull(curr.currencies)
    }
}

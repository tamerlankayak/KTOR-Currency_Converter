package exchange.az

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import exchange.az.plugins.*

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
}

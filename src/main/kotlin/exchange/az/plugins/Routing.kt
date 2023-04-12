package exchange.az.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        static("assets") {

        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

    }
}

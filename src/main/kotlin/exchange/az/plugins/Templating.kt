package exchange.az.plugins

import exchange.az.Constants
import freemarker.cache.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    routing {

        get("/hey") {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    gson()
                }
            }
            val response: HttpResponse = client.get(Constants.BASE_URL)
            client.close()

            val curr: Currencies = response.body()

            val item = listOf(curr)
            val currList= ArrayList<CurrenciesItem>()


            item.forEach {
                it.currencies.forEach { (t) ->
                    currList.add(CurrenciesItem(t))
                }
            }

            currList.forEach {
                println("---${it.currencyName}---")
            }
            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to currList), ""))

        }


    }


}

data class IndexData(val items: List<Int>)

data class CurrenciesItem(val currencyName: String) {}

data class Currencies(val currencies: HashMap<String, String>) {}

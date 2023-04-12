package exchange.az.plugins

import com.google.gson.Gson
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
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

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
            val currList = ArrayList<CurrenciesItem>()


            item.forEach {
                it.currencies.forEach { (t) ->
                    currList.add(CurrenciesItem(t))
                }
            }

            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to currList.sortedBy { it.currencyName }), ""))

        }


        get("/convert/{from}/{to}/{amount}") {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    gson()
                }
            }
            var from = call.parameters["from"]
            var to = call.parameters["to"]
            var amount = call.parameters["amount"]

            val response: HttpResponse =
                client.get("https://api.fastforex.io/convert?from=${from}&to=${to}&amount=${amount}&api_key=a50e450774-047e7c3ee4-rszo4u")
            val result: Result = response.body()
            val gson = Gson()

            call.respond(gson.toJson(ResultItem(result.result.entries.first().value.toString())))
            client.close()


        }
    }
}

@Serializable
data class Result(val result: HashMap<String, Double>)

@Serializable
data class ResultItem(val value: String)

data class CurrenciesItem(val currencyName: String) {}

data class Currencies(val currencies: HashMap<String, String>) {}
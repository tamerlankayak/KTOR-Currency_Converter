package exchange.az.plugins

import com.google.gson.Gson
import exchange.az.util.Constants
import exchange.az.model.Currencies
import exchange.az.model.CurrenciesItem
import exchange.az.model.Result
import exchange.az.model.ResultItem
import exchange.az.service.configService
import exchange.az.util.Constants.API_KEY
import exchange.az.util.Constants.BASE_URL
import exchange.az.util.Constants.LIST_OF_CURRENCIES
import freemarker.cache.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureTemplating() {

    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing {

        static("/static") {
            resources("files")
        }

        //getting all currecies list from api
        get("/") {

            val response: HttpResponse = configService(BASE_URL + LIST_OF_CURRENCIES)
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


        get("/convert/{from}/{to}/{inputAmount}") {

            val from = call.parameters["from"]
            val to = call.parameters["to"]
            val amount = call.parameters["inputAmount"]

            val response: HttpResponse =
                configService("${BASE_URL}/convert?from=${from}&to=${to}&amount=${amount}&api_key=${API_KEY}")
            val result: Result = response.body()
            val gson = Gson()

            call.respond(gson.toJson(ResultItem(result.result.entries.first().value.toString())))

        }

    }
}





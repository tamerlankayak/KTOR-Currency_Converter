package exchange.az

import io.ktor.server.application.*
import exchange.az.plugins.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

 fun main(args: Array<String>){
    io.ktor.server.netty.EngineMain.main(args)


}



@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureTemplating()
    configureRouting()


}



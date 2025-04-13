package com.digitalisierung

import com.digitalisierung.payments.paytax.models.configurePayTaxRouting
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        configurePayTaxRouting()
    }
}

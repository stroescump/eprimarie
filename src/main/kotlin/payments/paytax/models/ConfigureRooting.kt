package com.digitalisierung.payments.paytax.models

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Route.configurePayTaxRouting() {
    get("/error-test") { throw IllegalStateException() }

    post("/plati/platesteTaxa") {
        val tax = call.receive<Tax>()
        val taxError = getTaxErrorOrNull(tax)

        if (taxError != null) {
            call.respond(HttpStatusCode.UnprocessableEntity, taxError)
        } else {
            call.respond(HttpStatusCode.Created)
        }
    }
}

fun getTaxErrorOrNull(tax: Tax): String? {
    if (tax.amount <= 0) return "Amount must be greater than 0"
    if (tax.taxType.name.isBlank()) return "Tax type name cannot be blank"
    if (tax.taxBeneficiary.name.isBlank()) return "Tax beneficiary name cannot be blank"
    return null // valid
}

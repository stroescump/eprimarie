package com.digitalisierung

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import kotlinx.serialization.ExperimentalSerializationApi
import models.ApiResult

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@OptIn(ExperimentalSerializationApi::class)
fun Application.module() {
    configureFrameworks()
    configureSerialization()
    configureSecurity()
    configureRouting()

    install(StatusPages) {
        exception<IllegalStateException> { call, cause ->
            call.respondText { "App in illegal state. Cause: ${cause.message}" }
        }

        exception<BadRequestException> { call, badRequest ->
            val rootCause = when (val rootCause = badRequest.cause ?: badRequest) {
                is JsonConvertException -> rootCause.cause ?: rootCause
                else -> rootCause
            }

            call.respond(
                HttpStatusCode.BadRequest,
                ApiResult.Failure(rootCause::class.java.simpleName, rootCause.localizedMessage)
            )
        }

        exception<Exception> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
        }
    }
}

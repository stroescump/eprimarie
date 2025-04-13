package com.digitalisierung

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureFrameworks() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            single<HelloService> {
                object : HelloService {
                    override fun sayHello() {
                        println(environment.log.info("Hello, World!"))
                    }

                    override fun saySomethingElse() {
                        println(environment.log.info("Hello, WorldsSomethingElse!"))
                    }
                }
            }
        })
    }
}

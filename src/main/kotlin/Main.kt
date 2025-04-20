package org.example

import org.koin.core.context.startKoin

suspend fun main() {

    val koinApp = startKoin {
        modules(KoinExtensions.addMediatorModule())
    }

    val mediator = koinApp.koin.get<MySender>()

    val result = mediator.send(PingCommand("hello!"))
    println(result)
}
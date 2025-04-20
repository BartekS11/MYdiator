package org.example

import org.koin.core.module.Module
import org.koin.dsl.module

class KoinExtensions {
    companion object Factory {
        // Add self registration for handlers
        fun addMediatorModule() : Module
        {
            val mediatorModule = module {
                single<BaseHandler> { PingCommandHandler() }
                single<IMyRequestHandler<PingCommand, String>> { get() as PingCommandHandler }
                single { MySender(getKoin()) }
            }
            return mediatorModule;
        }
    }

}
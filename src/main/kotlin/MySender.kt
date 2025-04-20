package org.example

import org.koin.core.Koin
import kotlin.reflect.KClass
import kotlin.reflect.full.callSuspend

class MySender(private val koin: Koin) : IMySender
{
    override suspend fun <TResponse: Any> send(request: IMyRequest<TResponse>): TResponse {
            val handler = findHandler(request)
            return invokeHandle(handler, request)
    }

    private fun <TResponse : Any> findHandler(request: IMyRequest<TResponse>): Any {
        val handlers = koin.getAll<BaseHandler>()

        return handlers.firstOrNull { candidate ->
            candidate::class.supertypes.any { type ->
                type.classifier == IMyRequestHandler::class &&
                        type.arguments.getOrNull(0)?.type?.classifier == request::class &&
                        type.arguments.getOrNull(1)?.type?.classifier == request.responseType()
            }
        } ?: error("No handler found for ${request::class.simpleName}")
    }

    @Suppress("UNCHECKED_CAST")
    private suspend fun <TResponse : Any> invokeHandle(handler: Any, request: IMyRequest<TResponse>): TResponse {
        val method = handler::class.members.firstOrNull { it.name == "handle" }
            ?: error("Handler has no Handle() function")

        return method.callSuspend(handler, request) as TResponse
    }
    private fun <T> IMyRequest<T>.responseType(): KClass<*> {
        val superType = this::class.supertypes.firstOrNull {
            it.classifier == IMyRequest::class
        } ?: return Any::class

        return (superType.arguments.firstOrNull()?.type?.classifier as? KClass<*>) ?: Any::class
    }
}
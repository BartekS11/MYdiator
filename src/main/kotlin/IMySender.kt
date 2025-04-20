package org.example

interface IMySender
{
    suspend fun<TResponse: Any> send(request: IMyRequest<TResponse>) : TResponse
}
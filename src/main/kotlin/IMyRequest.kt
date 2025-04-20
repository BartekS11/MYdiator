package org.example

interface BaseHandler

interface IMyRequest<TResponse>

interface IMyRequestHandler<TRequest, TResponse> where TRequest : IMyRequest<TResponse>
{
    suspend fun handle(request: TRequest) : TResponse;
}



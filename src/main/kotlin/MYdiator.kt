package org.example

interface IMyRequest<out TRequest>

interface IMyQuery<in TRequest, TResponse> : IMyRequest<TResponse>
interface IMyCommand<in TRequest, TResponse> : IMyRequest<TResponse>

interface IMyQueryHandler<in TRequest, TResponse> where TRequest : IMyQuery<TRequest, TResponse>
{
    suspend fun<TResponse> Handle(request: TRequest) : TResponse
}

interface IMyCommandHandler<in TRequest, TResponse> where TRequest : IMyCommand<TRequest, TResponse>
{
    suspend fun<TResponse> Handle(request: TRequest) : TResponse
}

interface IMYdiator
{
    suspend fun<TResponse> Send(request: IMyRequest<TResponse>) : TResponse
}

class MYdiator : IMYdiator {
    override suspend fun <TResponse> Send(request: IMyRequest<TResponse>): TResponse {
        TODO("Not yet implemented")
    }
}
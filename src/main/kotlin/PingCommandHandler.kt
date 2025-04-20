package org.example

data class PingCommand(val message: String) : IMyRequest<String>

class PingCommandHandler : BaseHandler, IMyRequestHandler<PingCommand, String> {
    override suspend fun handle(request: PingCommand): String {
       return "Hola amigos: ${request.message}";
    }
}
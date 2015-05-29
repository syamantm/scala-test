package com.schibsted.example

import io.netty.buffer.ByteBuf
import io.netty.handler.codec.http.HttpResponseStatus
import io.reactivex.netty.protocol.http.server.{HttpServerResponse, HttpServerRequest, RequestHandler}
import rx.Observable


/**
 * @author syamantak
 */
class HelloHandler extends RequestHandler[ByteBuf, ByteBuf] {
  override def handle(request: HttpServerRequest[ByteBuf], response: HttpServerResponse[ByteBuf]): Observable[Void] = {
    request.getUri match {
      case "*/hello" => response.writeString("Hello from Scala")
      case _ => response.setStatus(HttpResponseStatus.NOT_FOUND)
    }
    response.close(true)
  }
}

object HelloHandler {
  def apply(): HelloHandler = new HelloHandler
}

package com.schibsted.example

import io.netty.buffer.ByteBuf
import io.netty.handler.codec.http.HttpResponseStatus
import io.reactivex.netty.protocol.http.server.{HttpServerRequest, HttpServerResponse}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.{verify, when}
import org.scalatest.Assertions
import org.scalatest.mock.MockitoSugar
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * @author : syamantak
 */
class HelloHandlerTest extends Assertions with MockitoSugar {

  val helloHandler = HelloHandler()


  @Test
  def shouldHandleHelloUrl = {
    val request = mock[HttpServerRequest[ByteBuf]]
    val response = mock[HttpServerResponse[ByteBuf]]

    when(request.getUri).thenReturn("http://localhost:8080/hello")

    helloHandler.handle(request, response)

    val capture = ArgumentCaptor.forClass(classOf[String])

    verify(response).writeString(capture.capture)

    assertEquals(capture.getValue, "Hello from Scala")
  }


  @Test
  def shouldNotFoundResourceIfnotHello = {
    val request = mock[HttpServerRequest[ByteBuf]]
    val response = mock[HttpServerResponse[ByteBuf]]

    when(request.getUri).thenReturn("http://localhost:8080/goodbye")

    helloHandler.handle(request, response)

    val capture = ArgumentCaptor.forClass(classOf[HttpResponseStatus])

    verify(response).setStatus(capture.capture)

    assertEquals(capture.getValue, HttpResponseStatus.NOT_FOUND)
  }


}

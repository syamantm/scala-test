package com.schibsted.example

import io.netty.buffer.ByteBuf
import io.reactivex.netty.protocol.http.server.{HttpServerResponse, HttpServerRequest}
import org.scalatest.Assertions
import org.scalatest.mock.MockitoSugar
import org.mockito.{Matchers, MockitoAnnotations, Mock, ArgumentCaptor}
import org.mockito.Mockito.{mock, verify, when}
import org.mockito.BDDMockito.given
import org.testng.annotations.{BeforeMethod, Test}
import org.testng.Assert.assertEquals

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

}

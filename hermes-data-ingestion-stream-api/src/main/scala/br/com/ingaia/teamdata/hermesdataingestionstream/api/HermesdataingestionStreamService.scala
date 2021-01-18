package br.com.ingaia.teamdata.hermesdataingestionstream.api

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

/**
  * The hermes-data-ingestion stream interface.
  *
  * This describes everything that Lagom needs to know about how to serve and
  * consume the HermesdataingestionStream service.
  */
trait HermesdataingestionStreamService extends Service {

  def stream: ServiceCall[Source[String, NotUsed], Source[String, NotUsed]]

  override final def descriptor: Descriptor = {
    import Service._

    named("hermes-data-ingestion-stream")
      .withCalls(
        namedCall("stream", stream)
      ).withAutoAcl(true)
  }
}


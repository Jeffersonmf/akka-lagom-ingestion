package br.com.ingaia.teamdata.hermesdataingestion.impl

import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import br.com.ingaia.teamdata.hermesdataingestion.api._

class HermesdataingestionServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {

  private val server = ServiceTest.startServer(
    ServiceTest.defaultSetup
      .withCassandra()
  ) { ctx =>
    new HermesdataingestionApplication(ctx) with LocalServiceLocator
  }

  val client: HermesdataingestionService = server.serviceClient.implement[HermesdataingestionService]

  override protected def afterAll(): Unit = server.stop()

  "hermes-data-ingestion service" should {

    "say hello" in {
      client.hello("Alice").invoke().map { answer =>
        answer should ===("Hello, Alice!")
      }
    }

    "allow responding with a custom message" in {
      for {
        _ <- client.useGreeting("Bob").invoke(GreetingMessage("Hi"))
        answer <- client.hello("Bob").invoke()
      } yield {
        answer should ===("Hi, Bob!")
      }
    }
  }
}

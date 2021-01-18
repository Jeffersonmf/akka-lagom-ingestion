package br.com.ingaia.teamdata.hermesdataingestionstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import br.com.ingaia.teamdata.hermesdataingestionstream.api.HermesdataingestionStreamService
import br.com.ingaia.teamdata.hermesdataingestion.api.HermesdataingestionService
import com.softwaremill.macwire._

class HermesdataingestionStreamLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new HermesdataingestionStreamApplication(context) {
      override def serviceLocator: NoServiceLocator.type = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new HermesdataingestionStreamApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[HermesdataingestionStreamService])
}

abstract class HermesdataingestionStreamApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = serverFor[HermesdataingestionStreamService](wire[HermesdataingestionStreamServiceImpl])

  // Bind the HermesdataingestionService client
  lazy val hermesdataingestionService: HermesdataingestionService = serviceClient.implement[HermesdataingestionService]
}

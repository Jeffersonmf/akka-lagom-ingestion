package br.com.ingaia.teamdata.hermesdataingestionstream.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import br.com.ingaia.teamdata.hermesdataingestionstream.api.HermesdataingestionStreamService
import br.com.ingaia.teamdata.hermesdataingestion.api.HermesdataingestionService

import scala.concurrent.Future

/**
  * Implementation of the HermesdataingestionStreamService.
  */
class HermesdataingestionStreamServiceImpl(hermesdataingestionService: HermesdataingestionService) extends HermesdataingestionStreamService {
  def stream = ServiceCall { hellos =>
    Future.successful(hellos.mapAsync(8)(hermesdataingestionService.hello(_).invoke()))
  }
}

import cats.effect._
import fs2.StreamApp.ExitCode
import fs2.{Stream, StreamApp}
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext.Implicits.global


object Main extends StreamApp[IO] {

  val service = HttpService[IO] {
    case GET -> Root => Ok("Home Page")
  }

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(9090, "0.0.0.0")
      .mountService(service, "/")
      .serve
}

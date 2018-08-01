import cats.effect._
import fs2.StreamApp.ExitCode
import fs2.{Stream, StreamApp}
import io.circe.syntax._
import io.circe.literal._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext.Implicits.global


object Main extends StreamApp[IO] {

  val service = HttpService[IO] {
    case GET -> Root => Ok("Home Page".asJson)
    case GET -> Root / "name" / name => Ok(json"""{"name": $name}""")
    case _ -> url => NotFound(s"Sorry, but there is no handler for $url url!".asJson)
  }

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    BlazeBuilder[IO]
      .bindHttp(9090, "0.0.0.0")
      .mountService(service, "/")
      .serve
}

import cats.effect.IO
import io.circe.literal._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._

class HomeService {
  val service: HttpService[IO] = HttpService[IO] {
    case GET -> Root => Ok("Home Page".asJson)
    case GET -> Root / "name" / name => Ok(json"""{"name": $name}""")
    case _ -> url => NotFound(s"Sorry, but there is no handler for $url url!".asJson)
  }
}

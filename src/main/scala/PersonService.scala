import cats.effect.IO
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._

class PersonService {
  case class Human(name: String, age: Int)

  val service: HttpService[IO] = HttpService[IO]{
    case req @ POST -> Root => Ok(req.decodeJson[Human].map(_.asJson))
  }
}

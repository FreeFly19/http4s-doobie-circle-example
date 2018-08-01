import cats.effect.IO
import io.circe._
import io.circe.generic.semiauto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._

class PersonService {
  case class Human(name: String, age: Int)
  implicit val humanDecoder: Decoder[Human] = deriveDecoder
  implicit val humanEncoder: Encoder[Human] = deriveEncoder

  val service: HttpService[IO] = HttpService[IO]{
    case req @ POST -> Root => Ok(req.decodeJson[Human].map(_.asJson))
  }
}

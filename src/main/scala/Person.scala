import cats.effect._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._

case class Person(id: Long, name: String, age: Int)
case class CreatePersonCommand(name: String, age: Int)

class PersonService(personRepository: PersonRepository[IO]) {
  val service: HttpService[IO] = HttpService[IO]{
    case req @ POST -> Root => Ok {
      req
        .decodeJson[CreatePersonCommand]
        .flatMap(personRepository.save)
        .map(_.asJson)
    }
    case GET -> Root => Ok(personRepository.findAll.map(_.asJson))
  }
}

trait PersonRepository[F[_]] {
  def save(p: CreatePersonCommand): F[Person]
  def findAll: F[List[Person]]
}

class InMemoryPersonRepository extends PersonRepository[IO] {
  var persons = List[Person]()
  var idGenerator = 1

  override def save(p: CreatePersonCommand): IO[Person] = {
    val person = Person(idGenerator, p.name, p.age)
    persons = persons :+ person

    idGenerator += 1

    IO.pure(person)
  }

  override def findAll: IO[List[Person]] =
    IO.pure(persons)
}
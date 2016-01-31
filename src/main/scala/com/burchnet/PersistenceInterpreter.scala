package com.burchnet

import cats.free.Free
import cats.{Functor, Id, ~>}
import com.burchnet.PersistenceInterpreter._

trait PersistenceInterpreter[M <: Model[M, I], I] extends (Command ~> cats.Id){

    override def apply[A](service: Command[A]): Id[A] =
    {
        service match
        {
            case FindOne(dBQuery, auth, next) => ((findOne _).tupled andThen next)(dBQuery, auth)
            case FindAll(dBQuery, auth, next) => ((findAll _).tupled andThen next)(dBQuery, auth)
            case Delete(dBQuery, auth, next) => ((delete _).tupled andThen next)(dBQuery, auth)
            case Update(model: M, auth, next) => ((update _).tupled andThen next)(model, auth)
            case Create(model: M, auth, next) => ((create _).tupled andThen next)(model, auth)
        }
    }

    protected def findOne(dbQuery: DBQuery, authorization: Authorization): Option[M]

    protected def findAll(dbQuery: DBQuery, authorization: Authorization): List[M]

    protected def delete(dbQuery: DBQuery, authorization: Authorization): Either[Error, Unit]

    protected def update(model: M, authorization: Authorization): Either[Error, M]

    protected def create(model: M, authorization: Authorization): Either[Error, M]
}

object PersistenceInterpreter {
    
    type DBCommand[Result] = Free[Command, Result]

    sealed trait Command[Next]

    sealed case class FindOne[Next](a: DBQuery, auth: Authorization,
                                    result: Option[Model[_, _]] => Next) extends Command[Next]

    sealed case class FindAll[Next](a: DBQuery, auth: Authorization,
                                    result: List[Model[_, _]] => Next) extends Command[Next]

    sealed case class Delete[Next](a: DBQuery, auth: Authorization,
                                   result: Either[Error, Unit] => Next) extends Command[Next]

    sealed case class Update[Next](m: Model[_, _], auth: Authorization,
                                   result: Either[Error, Model[_, _]] => Next) extends Command[Next]

    sealed case class Create[Next](m: Model[_, _], auth: Authorization,
                                   result: Either[Error, Model[_, _]] => Next) extends Command[Next]

    implicit val persistenceFunctor = new Functor[Command]
    {
        override def map[A, B](command: Command[A])(f: (A) => B): Command[B] =
        {
            command match
            {
                case FindOne(dBQuery, auth, onResult) => FindOne(dBQuery, auth, onResult andThen f)
                case FindAll(dBQuery, auth, onResult) => FindAll(dBQuery, auth, onResult andThen f)
                case Delete(dBQuery, auth, onResult) => Delete(dBQuery, auth, onResult andThen f)
                case Update(model, auth, onResult) => Update(model, auth, onResult andThen f)
                case Create(model, auth, onResult) => Create(model, auth, onResult andThen f)
            }
        }
    }
}
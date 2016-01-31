package com.burchnet

abstract class MongoPersistenceInterpreter[M <: MongoModel[M]] extends PersistenceInterpreter[M, Long]
{

    protected def findOne(dbQuery: DBQuery, authorization: Authorization): Option[M] = None

    protected def findAll(dbQuery: DBQuery, authorization: Authorization): List[M] = Nil

    protected def delete(dbQuery: DBQuery, authorization: Authorization): Either[Error, Unit] = Left(Error.notImplemented)

    protected def update(model: M, authorization: Authorization): Either[Error, M] = Left(Error.notImplemented)

    protected def create(model: M, authorization: Authorization): Either[Error, M] = Left(Error.notImplemented)
}
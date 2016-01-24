package com.burchnet

trait PersistenceInturputer[M <: Model[M]]
{
	protected def findOne(dbQuery: DBQuery, authorization: Authorization): Option[M]

	protected def findAll(dbQuery: DBQuery, authorization: Authorization): List[M]

	protected def delete(dbQuery: DBQuery, authorization: Authorization): Either[Error, Unit]

	protected def update(model: M, authorization: Authorization): Either[Error, M]

	protected def create(model: M, authorization: Authorization): Either[Error, M]
}
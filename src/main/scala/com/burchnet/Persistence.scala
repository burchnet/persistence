package com.burchnet

import cats.free.Free.liftF
import com.burchnet.PersistenceInterpreter._

object Persistence {

    def findOne(dBQuery: DBQuery, auth: Authorization): DBCommand[Option[Model[_, _]]] =
        liftF(FindOne(dBQuery, auth, (identity _)))

    def findAll(dBQuery: DBQuery, auth: Authorization): DBCommand[List[Model[_, _]]] =
        liftF(FindAll(dBQuery, auth, (identity _)))

    def delete(dBQuery: DBQuery, auth: Authorization): DBCommand[Either[Error, Unit]] =
        liftF(Delete(dBQuery, auth, (identity _)))

    def update(model: Model[_, _], auth: Authorization): DBCommand[Either[Error, Model[_, _]]] =
        liftF(Update(model, auth, (identity _)))

    def create(model: Model[_, _], auth: Authorization): DBCommand[Either[Error, Model[_, _]]] =
        liftF(Create(model, auth, (identity _)))
}

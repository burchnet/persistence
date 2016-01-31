package com.burchnet

import com.burchnet.IDLike._

abstract class Model[IDType, I](id: IDLike[IDType, I])

abstract class MongoModel[IDType](id: ID[IDType]) extends Model[IDType, Long](id)

case class IDLike[IDType, I](i: I)

object IDLike
{

    type ID[IDType] = IDLike[IDType, Long]
}
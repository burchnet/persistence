package com.burchnet

import org.springframework.data.annotation.Id
import beans.BeanProperty

abstract class Model[IDType](@Id @BeanProperty id: ID[IDType])

abstract class MongoModel[IDType](@Id @BeanProperty id: ID[IDType]) extends Model[IDType](id)

case class ID[IDType](i: IDType)
package com.burchnet

case class Keyword(phrase: String = "", fields: List[String] = Nil)

case class DBQuery(sortOrder: SortOrder = NoOrder(), keyword: Option[Keyword] = None, params: List[ParamLike] = Nil)

abstract class ParamLike(name: String)

case class RangeParam[Value](name: String, start: Value, end: Value) extends ParamLike(name)

case class Param[Value](name: String, value: Value) extends ParamLike(name)

abstract class SortOrder(fieldName: String)

case class Ascending(fieldName: String) extends SortOrder(fieldName)

case class Descending(fieldName: String) extends SortOrder(fieldName)

case class NoOrder() extends SortOrder("")
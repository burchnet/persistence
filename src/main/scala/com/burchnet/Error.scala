package com.burchnet

abstract class Error(message: String, code: Int)

case class NotImplemented() extends Error("Not implemented", 500)

object Error {
    val notImplemented = NotImplemented()
}
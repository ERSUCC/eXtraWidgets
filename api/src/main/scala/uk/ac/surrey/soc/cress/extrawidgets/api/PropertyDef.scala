package uk.ac.surrey.soc.cress.extrawidgets.api

import org.nlogo.api.Syntax._

abstract class PropertyDef[+W <: ExtraWidget, T](
  val widget: W,
  val setter: T ⇒ Unit,
  val getter: () ⇒ T,
  val default: () ⇒ T) {
  val inputTypeConstant: Int
  val outputTypeConstant: Int
  def asInputType(obj: AnyRef): T = obj.asInstanceOf[T] // TODO: handle this
  def setValueObj(obj: AnyRef): Unit = setter(asInputType(obj))
  def unsetValue(): Unit = setter(default())
}

class StringPropertyDef[+W <: ExtraWidget](
  w: W,
  setter: String ⇒ Unit,
  getter: () ⇒ String,
  default: () ⇒ String = () ⇒ "")
  extends PropertyDef(w, setter, getter, default) {
  val inputTypeConstant = StringType
  val outputTypeConstant = StringType
  override def asInputType(obj: AnyRef): String = obj.toString
}

class IntegerPropertyDef[+W <: ExtraWidget](
  w: W,
  setter: java.lang.Integer ⇒ Unit,
  getter: () ⇒ java.lang.Integer,
  default: () ⇒ java.lang.Integer = () ⇒ Int.box(0))
  extends PropertyDef(w, setter, getter, default) {
  val inputTypeConstant = NumberType
  val outputTypeConstant = NumberType
  override def asInputType(obj: AnyRef): java.lang.Integer = obj match {
    case d: java.lang.Double ⇒ d.intValue
    case _ ⇒ super.asInputType(obj)
  }
}

/*
                    ,i::,
               :;;;;;;;
              ;:,,::;.
            1ft1;::;1tL
              t1;::;1,
               :;::;               _____        __  ___              __
          fCLff ;:: tfLLC         / ___/      /  |/  /____ _ _____ / /_
         CLft11 :,, i1tffLi       \__ \ ____ / /|_/ // __ `// ___// __ \
         1t1i   .;;   .1tf       ___/ //___// /  / // /_/ // /__ / / / /
       CLt1i    :,:    .1tfL.   /____/     /_/  /_/ \__,_/ \___//_/ /_/
       Lft1,:;:       , 1tfL:
       ;it1i ,,,:::;;;::1tti      s_mach.codetools
         .t1i .,::;;; ;1tt        Copyright (c) 2014 S-Mach, Inc.
         Lft11ii;::;ii1tfL:       Author: lance.gatlin@gmail.com
          .L1 1tt1ttt,,Li
            ...1LLLL...
*/
package s_mach.codetools.reflectPrint


trait ReflectPrintValueTypeImplicits {
  implicit object ReflectPrint_Boolean extends SimpleReflectPrint[Boolean] {
    override def print(a: Boolean)(implicit cfg: ReflectPrintFormat): String = {
      a.toString
    }
  }
  implicit object ReflectPrint_Byte extends ValueTypeReflectPrint[Byte]
  implicit object ReflectPrint_Short extends ValueTypeReflectPrint[Short]
  implicit object ReflectPrint_Int extends ValueTypeReflectPrint[Int]
  implicit object ReflectPrint_Long extends SimpleReflectPrint[Long] {
    override def print(a: Long)(implicit cfg: ReflectPrintFormat): String = {
      s"${a}l"
    }
  }
  implicit object ReflectPrint_Float extends ValueTypeReflectPrint[Float]
  implicit object ReflectPrint_Double extends ValueTypeReflectPrint[Double]
  implicit object ReflectPrint_BigInt extends ReflectPrint[BigInt] {
    override def printApply(
      a: BigInt
    )(implicit 
      cfg: ReflectPrintFormat
    ): String = {
      s"""BigInt("$a")"""
    }
    override def printUnapply(
      a: BigInt
    )(implicit 
      cfg: ReflectPrintFormat
    ): String = s""""$a""""
  }
  implicit object ReflectPrint_BigDecimal extends ReflectPrint[BigDecimal] {
    override def printApply(
      a: BigDecimal
    )(implicit 
      cfg: ReflectPrintFormat
    ): String = {
      s"""BigDecimal("$a")"""
    }
    override def printUnapply(
      a: BigDecimal
    )(implicit 
      cfg: ReflectPrintFormat
    ): String = s""""$a""""
  }
  implicit object ReflectPrint_Char extends SimpleReflectPrint[Char] {
    override def print(a: Char)(implicit cfg: ReflectPrintFormat): String = {
      s"'$a'"
    }
  }
  implicit object ReflectPrint_String extends SimpleReflectPrint[String] {
    override def print(a: String)(implicit cfg: ReflectPrintFormat): String = {
      import scala.reflect.runtime.universe._
      // Note: this takes care of escaping strings: http://stackoverflow.com/questions/9913971/scala-how-can-i-get-an-escaped-representation-of-a-string
      Literal(Constant(a)).toString()
    }
  }
}

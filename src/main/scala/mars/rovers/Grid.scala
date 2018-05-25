package mars.rovers

import scala.util.Try

case class Grid(rows: Int, columns: Int)

object Grid {
  def apply(input: String): Option[Grid] = {
    val maybeSizes = Try(input.split(" ")).toOption
    maybeSizes.collect {
      case sizes if sizes.length == 2 =>
        val maybeRow = Try(sizes.head.toInt).toOption
        val maybeColumn = Try(sizes(1).toInt).toOption

        val a: Option[Grid] = for {
          row <- maybeRow
          column <- maybeColumn
        } yield Grid(row, column)

        a
      case _ => None
    }.flatten
  }
}

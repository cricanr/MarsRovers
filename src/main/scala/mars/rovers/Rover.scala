package mars.rovers

object Orientation extends Enumeration {
  type Orientation = Value
  val N, S, W, E = Value

  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)
}

object RoverCommand extends Enumeration {
  type RoverCommand = Value
  val L, R, M = Value

  def withNameOpt(s: String): Option[Value] = values.find(_.toString == s)
}

import mars.rovers.Orientation.{Orientation, _}
import mars.rovers.RoverCommand.{L, M, R, RoverCommand}

import scala.util.Try

case class Coordinates(x: Int, y: Int)

case class Command(commands: Seq[RoverCommand])

object Command {
  def apply(input: String): Command = {
    val roverCommands = input.collect { case command if RoverCommand.withNameOpt(command.toString).isDefined =>
      RoverCommand.withNameOpt(command.toString).get
    }

    Command(roverCommands)
  }
}

case class Rover(coordinates: Coordinates, orientation: Orientation) {
  private def decideMove(roverCommand: RoverCommand, grid: Grid): Rover = {
    roverCommand match {
      case M =>
        singleMove(grid)
      case L =>
        rotateLeft
      case R =>
        rotateRight
    }
  }

  private def withinBoundaries(grid: Grid): Boolean = {
    orientation match {
      case N =>
        coordinates.y + 1 <= grid.columns
      case S =>
        coordinates.y - 1 >= 0
      case E =>
        coordinates.x + 1 <= grid.rows
      case W =>
        coordinates.x - 1 >= 0
    }
  }

  private def singleMove(grid: Grid) = {
    if (withinBoundaries(grid))
      orientation match {
        case N =>
          Rover(Coordinates(coordinates.x, coordinates.y + 1), orientation)
        case S =>
          Rover(Coordinates(coordinates.x, coordinates.y - 1), orientation)
        case E =>
          Rover(Coordinates(coordinates.x + 1, coordinates.y), orientation)
        case W =>
          Rover(Coordinates(coordinates.x - 1, coordinates.y), orientation)
      }
    else throw new OutsideGridMoveException
  }

  private def rotateLeft: Rover = {
    orientation match {
      case N =>
        Rover(coordinates, W)
      case S =>
        Rover(coordinates, E)
      case E =>
        Rover(coordinates, N)
      case W =>
        Rover(coordinates, S)
    }
  }

  def rotateRight: Rover = {
    orientation match {
      case N =>
        Rover(coordinates, E)
      case S =>
        Rover(coordinates, W)
      case E =>
        Rover(coordinates, S)
      case W =>
        Rover(coordinates, N)
    }
  }

  def move(command: Command, grid: Grid): Rover =
    if (command.commands.size == 1)
      decideMove(command.commands.head, grid)
    else
      command.commands.zipWithIndex.foldLeft(decideMove(command.commands.head, grid))((acc, cmd) => if (cmd._2 != 0) acc.decideMove(cmd._1, grid) else acc)
}

object Rover {
  def apply(input: String): Option[Rover] = {
    val props = input.split(" ")
    val maybeX = Try(props.head.toInt).toOption
    val maybeY = Try(props(1).toInt).toOption
    val maybeOrientation = Orientation.withNameOpt(props(2))

    for {
      x <- maybeX
      y <- maybeY
      orientation <- maybeOrientation
    } yield Rover(Coordinates(x, y), orientation)
  }
}
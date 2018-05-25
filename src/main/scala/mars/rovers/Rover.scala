package mars.rovers

object Orientation extends Enumeration {
  type Orientation = Value
  val N, S, W, E = Value
}

object RoverCommand extends Enumeration {
  type RoverCommand = Value
  val L, R, M = Value
}

import mars.rovers.Orientation.Orientation
import mars.rovers.RoverCommand.RoverCommand
import Orientation._

case class Coordinates(x: Int, y: Int)
case class Command(commands: Seq[RoverCommand])

case class Rover(coordinates: Coordinates, orientation: Orientation) {
  private def singleMove = {
    orientation match {
      case N =>
        Rover(Coordinates(coordinates.x + 1, coordinates.y), orientation)
      case S =>
        Rover(Coordinates(coordinates.x - 1, coordinates.y), orientation)
      case E =>
        Rover(Coordinates(coordinates.x, coordinates.y + 1), orientation)
      case W =>
        Rover(Coordinates(coordinates.x, coordinates.y - 1), orientation)
    }
  }

  private def rotateLeft(): Rover = {
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

  def rotateRight(): Rover = {
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

  def move(command: Command): Rover = {
    import RoverCommand._
    command match {
      case Command(Seq(M )) =>
        singleMove
      case Command(Seq(L)) =>
        rotateLeft()
      case Command(Seq(R)) =>
        rotateRight()
    }
  }
}
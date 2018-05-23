package mars.rovers

object Orientation extends Enumeration {
  type Orientation = Value
  val N, S, W, E = Value
}

object RoverCommand extends Enumeration {
  type RoverCommand = Value
  val L, R, M = Value
}

import RoverCommand.{RoverCommand, _}
import mars.rovers.Orientation.Orientation

case class Coordinates(x: Int, y: Int)
case class Command(commands: Seq[RoverCommand])

case class Rover(coordinates: Coordinates, orientation: Orientation) {
  def move(command: Command): Rover = {
    ???
  }
}
package mars.rovers

object RoverController {

  def main(args: Array[String]): Unit = {
    println("Welcome to the Mars Rover app! Please specify your input as described next:")
    println("Line 1: grid size")
    println("Line 2: first rovers start coordinates")
    println("Line 3: first rovers moves")
    println("Line 4: second rovers start coordinates")
    println("Line 5: second rovers moves")
    println("---------------------------------------------------------------------------")

    val gridSizeStr = scala.io.StdIn.readLine()
    val firstRoverCoordinatesStr = scala.io.StdIn.readLine()
    val firstRoverMovesStr = scala.io.StdIn.readLine()

    val secondRoverCoordinatesStr = scala.io.StdIn.readLine()
    val secondRoverMovesStr = scala.io.StdIn.readLine()

    val maybeGrid = Grid.apply(gridSizeStr)
    val maybeFirstRover = Rover.apply(firstRoverCoordinatesStr)
    val firstRoverCommands = Command.apply(firstRoverMovesStr)
    val maybeSecondRover = Rover.apply(secondRoverCoordinatesStr)
    val secondRoverCommands = Command.apply(secondRoverMovesStr)
  }
}
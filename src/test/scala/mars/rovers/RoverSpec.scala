package mars.rovers

import mars.rovers.RoverCommand._
import org.scalatest.{Matchers, WordSpec}

class RoverSpec extends WordSpec with Matchers {
  "The Rover" when {
    "calling move method" should {
      "return new position" in {
        val initialRover = Rover(Coordinates(1, 2), Orientation.N)
        val expectedRover = Rover(Coordinates(1, 3), Orientation.N)
        val destinationRover = initialRover.move(Command(Seq(L, M, L, M, L, M, L, M, M)))

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move when North oriented" should {
      "increment x axis position" in {
        val initialRover = Rover(Coordinates(1, 2), Orientation.N)
        val expectedRover = Rover(Coordinates(2, 2), Orientation.N)
        val destinationRover = initialRover.move(Command(Seq(M)))

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move left when North oriented" should {
      "change orientation to West" in {
        val initialRover = Rover(Coordinates(1, 2), Orientation.N)
        val expectedRover = Rover(Coordinates(1, 2), Orientation.W)
        val destinationRover = initialRover.move(Command(Seq(L)))

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move right when North oriented" should {
      "change orientation to East" in {
        val initialRover = Rover(Coordinates(1, 2), Orientation.N)
        val expectedRover = Rover(Coordinates(1, 2), Orientation.E)
        val destinationRover = initialRover.move(Command(Seq(R)))

        destinationRover shouldEqual expectedRover
      }
    }
  }
}

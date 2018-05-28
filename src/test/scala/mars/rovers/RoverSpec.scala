package mars.rovers

import mars.rovers.Orientation._
import mars.rovers.RoverCommand._
import org.scalatest.{Matchers, WordSpec}

class RoverSpec extends WordSpec with Matchers {
  "The Rover" when {
    "calling apply method with input" should {
      "return a new rover" in {
        val input = "1 2 N"
        val expectedRover = Rover(Coordinates(1, 2), N)
        val destinationRover = Rover(input)

        destinationRover shouldEqual Some(expectedRover)
      }
    }

    "calling move method" should {
      "return new position" in {
        val initialRover = Rover(Coordinates(1, 2), N)
        val expectedRover = Rover(Coordinates(1, 3), N)
        val destinationRover = initialRover.move(Command(Seq(L, M, L, M, L, M, L, M, M)))

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move when North oriented" should {
      "increment y axis position" in {
        val initialRover = Rover(Coordinates(1, 2), N)
        val expectedRover = Rover(Coordinates(1, 3), N)
        val destinationRover = initialRover.move(Command(Seq(M)))

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move left when North oriented" should {
      "change orientation to West" in {
        val initialRover = Rover(Coordinates(1, 2), N)
        val expectedRover = Rover(Coordinates(1, 2), W)
        val destinationRover = initialRover.move(Command(Seq(L)))

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move right when North oriented" should {
      "change orientation to East" in {
        val initialRover = Rover(Coordinates(1, 2), N)
        val expectedRover = Rover(Coordinates(1, 2), E)
        val destinationRover = initialRover.move(Command(Seq(R)))

        destinationRover shouldEqual expectedRover
      }
    }
  }
}

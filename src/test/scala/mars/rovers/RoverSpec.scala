package mars.rovers

import mars.rovers.Orientation._
import mars.rovers.RoverCommand._
import org.scalatest.{Matchers, WordSpec}

class RoverSpec extends WordSpec with Matchers {
  "The Rover" when {

    val grid = Grid(6, 6)

    "calling apply method with input" should {
      "return a new rover" in {
        val input = "1 2 N"
        val expectedRover = Rover(Coordinates(1, 2), N)
        val destinationRover = Rover(input)

        destinationRover shouldEqual Some(expectedRover)
      }
    }

    "calling move method for a given drone 1: Rover(Coordinates(1, 2), N) with movements: Command(Seq(L, M, L, M, L, M, L, M, M))" should {
      "return new position: Rover(Coordinates(1, 3), N)" in {
        val initialRover = Rover(Coordinates(1, 2), N)
        val expectedRover = Rover(Coordinates(1, 3), N)
        val destinationRover = initialRover.move(Command(Seq(L, M, L, M, L, M, L, M, M)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move method for a given drone 2: Rover(Coordinates(3, 3), E) with movements: Command(Seq(M, M, R, M, M, R, M, R, R, M))" should {
      "return new position" in {
        val initialRover = Rover(Coordinates(3, 3), E)
        val expectedRover = Rover(Coordinates(5, 1), E)
        val destinationRover = initialRover.move(Command(Seq(M, M, R, M, M, R, M, R, R, M)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move when North oriented" should {
      "increment y axis position" in {
        val initialRover = Rover(Coordinates(1, 2), N)
        val expectedRover = Rover(Coordinates(1, 3), N)
        val destinationRover = initialRover.move(Command(Seq(M)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move when South oriented" should {
      "decrement y axis position" in {
        val initialRover = Rover(Coordinates(1, 2), S)
        val expectedRover = Rover(Coordinates(1, 1), S)
        val destinationRover = initialRover.move(Command(Seq(M)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move when West oriented" should {
      "decrement x axis position" in {
        val initialRover = Rover(Coordinates(1, 2), W)
        val expectedRover = Rover(Coordinates(0, 2), W)
        val destinationRover = initialRover.move(Command(Seq(M)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move when East oriented" should {
      "increment x axis position" in {
        val initialRover = Rover(Coordinates(1, 2), E)
        val expectedRover = Rover(Coordinates(2, 2), E)
        val destinationRover = initialRover.move(Command(Seq(M)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move left when North oriented" should {
      "change orientation to West" in {
        val initialRover = Rover(Coordinates(1, 2), N)
        val expectedRover = Rover(Coordinates(1, 2), W)
        val destinationRover = initialRover.move(Command(Seq(L)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move left when South oriented" should {
      "change orientation to West" in {
        val initialRover = Rover(Coordinates(1, 2), S)
        val expectedRover = Rover(Coordinates(1, 2), E)
        val destinationRover = initialRover.move(Command(Seq(L)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move left when West oriented" should {
      "change orientation to South" in {
        val initialRover = Rover(Coordinates(1, 2), W)
        val expectedRover = Rover(Coordinates(1, 2), S)
        val destinationRover = initialRover.move(Command(Seq(L)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move left when East oriented" should {
      "change orientation to Noth" in {
        val initialRover = Rover(Coordinates(1, 2), E)
        val expectedRover = Rover(Coordinates(1, 2), N)
        val destinationRover = initialRover.move(Command(Seq(L)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move right when North oriented" should {
      "change orientation to East" in {
        val initialRover = Rover(Coordinates(1, 2), N)
        val expectedRover = Rover(Coordinates(1, 2), E)
        val destinationRover = initialRover.move(Command(Seq(R)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move right when South oriented" should {
      "change orientation to West" in {
        val initialRover = Rover(Coordinates(1, 2), S)
        val expectedRover = Rover(Coordinates(1, 2), W)
        val destinationRover = initialRover.move(Command(Seq(R)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move right when West oriented" should {
      "change orientation to North" in {
        val initialRover = Rover(Coordinates(1, 2), W)
        val expectedRover = Rover(Coordinates(1, 2), N)
        val destinationRover = initialRover.move(Command(Seq(R)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move right when East oriented" should {
      "change orientation to East" in {
        val initialRover = Rover(Coordinates(1, 2), E)
        val expectedRover = Rover(Coordinates(1, 2), S)
        val destinationRover = initialRover.move(Command(Seq(R)), grid)

        destinationRover shouldEqual expectedRover
      }
    }

    "calling move when North oriented but would exit grid" should {
      "give an error and not move" in {
        val grid = Grid(2, 2)
        val initialRover = Rover(Coordinates(1, 2), N)

        assertThrows[OutsideGridMoveException] {
          initialRover.move(Command(Seq(M)), grid)
        }
      }
    }
  }
}
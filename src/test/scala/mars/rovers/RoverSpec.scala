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
  }
}

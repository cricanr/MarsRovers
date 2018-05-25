package mars.rovers

import mars.rovers.Orientation.N
import mars.rovers.RoverCommand.{L, M}
import org.scalatest.{Matchers, WordSpec}

class GridTest extends WordSpec with Matchers {
  "The Grid" when {
    "calling apply with a given input string" should {
      "return a grid" in {
        val inputStr = "5 5"
        val maybeGrid = Grid(inputStr)

        maybeGrid shouldEqual Some(Grid(5, 5))
      }
    }
  }
}

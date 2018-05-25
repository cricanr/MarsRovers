package mars.rovers

import org.scalatest.{Matchers, WordSpec}
import RoverCommand._

class CommandTest extends WordSpec with Matchers {
  "The Command" when {
    "calling apply method" should {
      "return a new Command" in {
        val input = "LMLMLMLMM"
        val expectedCommand = Command(Seq(L, M, L, M, L, M, L, M, M))
        val actualCommand = Command(input)

        actualCommand shouldEqual expectedCommand
      }
    }
  }
}
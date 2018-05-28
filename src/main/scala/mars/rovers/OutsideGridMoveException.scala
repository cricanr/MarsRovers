package mars.rovers

class OutsideGridMoveException extends Throwable {
  override def getMessage(): String = {
    "Cannot move drone, position outside grid boundaries"
  }
}
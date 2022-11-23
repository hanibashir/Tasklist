package tasklist
import tasklist.extras.Messages.GOOD_BYE
import tasklist.data.UserInput.getUserInput

fun main() {
    val running = getUserInput()
    if (!running) println(GOOD_BYE)
}

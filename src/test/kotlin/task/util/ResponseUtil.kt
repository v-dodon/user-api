package task.util

class ResponseUtil {
    companion object {
        fun readResponseFromFile(fileName: String): String {
            return this::class.java.getResource("/com/developmentaid/responses/$fileName").readText()
        }
    }
}
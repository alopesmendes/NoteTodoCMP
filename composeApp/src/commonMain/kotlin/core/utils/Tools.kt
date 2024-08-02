package core.utils

object Tools {
    fun parseToULong(colorHex: String): Long {
        val color = colorHex.removePrefix("#")
        val parsedColor = when (color.length) {
            6 -> color.toLong(16) or 0x00000000FF000000
            8 -> color.toLong(16)
            else -> throw IllegalArgumentException("Unknown color")
        }
        return parsedColor
    }
}
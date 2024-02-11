package util

import androidx.compose.ui.graphics.Color
import data.models.entity.Stat

fun Int.getStat(): Stat {
    return when (this) {
        0 -> Stat.HP
        1 -> Stat.ATTACK
        2 -> Stat.DEFENSE
        3 -> Stat.SPECIAL_ATTACK
        4 -> Stat.SPECIAL_DEFENSE
        5 -> Stat.SPEED
        else -> throw IndexOutOfBoundsException()
    }
}

fun String.capitalize(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

fun String.toColor(): Color {
    val rgb = this.chunked(2)
    return Color(red = rgb[0].toInt(16), green = rgb[1].toInt(16), blue = rgb[2].toInt(16))
}
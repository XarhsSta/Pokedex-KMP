package data.models.entity

import androidx.compose.ui.graphics.Color

enum class Stat(val formattedName: String, val color: Color){
    HP("HP", Color.Red),
    ATTACK("Att", Color.DarkGray),
    DEFENSE("Def", Color.Yellow),
    SPECIAL_ATTACK("SpA", Color.Blue),
    SPECIAL_DEFENSE("SpD", Color.Green),
    SPEED("Spd", Color.LightGray),
    UNKNOWN("Unknown", Color.Black)
}

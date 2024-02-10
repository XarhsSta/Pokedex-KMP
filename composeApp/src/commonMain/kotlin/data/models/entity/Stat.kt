package data.models.entity

enum class Stat(val formattedName: String){
    HP("HP"),
    ATTACK("Att"),
    DEFENSE("Def"),
    SPECIAL_ATTACK("SpA"),
    SPECIAL_DEFENSE("SpD"),
    SPEED("Spd"),
    UNKNOWN("Unknown")
}

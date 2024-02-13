package data.models.entity

enum class Stat(val formattedName: String, val hexColor: String, val backgroundHexColor: String){
    HP("HP", "A60000", "FF5959"),
    ATTACK("Att", "9C531F", "F5AC78"),
    DEFENSE("Def", "A1871F", "FAE078"),
    SPECIAL_ATTACK("SpA", "445E9C", "9DB7F5"),
    SPECIAL_DEFENSE("SpD", "4E8234", "A7DB8D"),
    SPEED("Spd", "A13959", "FA92B2"),
    UNKNOWN("Unknown", "000000", "000000")
}

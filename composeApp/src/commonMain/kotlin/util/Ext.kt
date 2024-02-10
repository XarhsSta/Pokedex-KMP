package util

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
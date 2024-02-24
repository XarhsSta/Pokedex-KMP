package data.models.entity

interface BaseModel {
    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean = !isEmpty()
}
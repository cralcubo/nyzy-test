package com.croman.nyzytest.entities

import jakarta.persistence.*

@Table(name = "human_values")
@Entity
class ValueEntity(
    @Column(nullable = false)
    override val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int? = null
): CharacteristicEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ValueEntity

        if (name != other.name) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (id ?: 0)
        return result
    }
}
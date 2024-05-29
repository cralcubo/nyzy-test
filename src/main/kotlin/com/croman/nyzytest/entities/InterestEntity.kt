package com.croman.nyzytest.entities

import jakarta.persistence.*

@Table(name = "interests")
@Entity
class InterestEntity(
    @Column(nullable = false)
    override val name: String,

    @OneToMany(mappedBy = "pk.interest")
    val userInterests: List<UserInterestEntity>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int? = null
): CharacteristicEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InterestEntity

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
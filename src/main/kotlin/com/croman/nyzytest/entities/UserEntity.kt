package com.croman.nyzytest.entities

import jakarta.persistence.*

@Table(name = "users")
@Entity
class UserEntity(
    @Column(nullable = false)
    val age: Int,

    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Column(name = "last_name", nullable = false)
    val lastName: String,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userInterests: List<UserInterestEntity> = emptyList(),

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userValues: List<UserValueEntity> = emptyList(),

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_connections",
        joinColumns = [JoinColumn(name = "user_a")],
        inverseJoinColumns = [JoinColumn(name = "user_b")]
    )
    val connections: List<UserEntity> = emptyList(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (age != other.age) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = age
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + (id ?: 0)
        return result
    }
}
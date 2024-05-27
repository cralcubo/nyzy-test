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

    @OneToMany(mappedBy = "pk.user")
    val userInterests: List<UserInterestEntity>,

    @OneToMany(mappedBy = "pk.user")
    val userValues: List<UserValueEntity>,

    @ManyToMany
    @JoinTable(
        name = "user_connections",
        joinColumns = [JoinColumn(name = "user_a")],
        inverseJoinColumns = [JoinColumn(name = "user_b")]
    )
    val connections: List<UserEntity>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int
)
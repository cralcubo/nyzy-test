package com.croman.nyzytest.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Table(name = "users")
@Entity
class UserEntity(
    @Column(nullable = false)
    val age: Int,

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
    val id: Int
)
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
    override val id: Int
): CharacteristicEntity
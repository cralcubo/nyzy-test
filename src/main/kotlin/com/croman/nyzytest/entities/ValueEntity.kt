package com.croman.nyzytest.entities

import jakarta.persistence.*

@Table(name = "human_values")
@Entity
class ValueEntity(
    @Column(nullable = false)
    override val name: String,

    @OneToMany(mappedBy = "pk.value")
    val userValues: List<UserValueEntity>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int
): CharacteristicEntity
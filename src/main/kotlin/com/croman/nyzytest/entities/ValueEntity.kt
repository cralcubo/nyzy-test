package com.croman.nyzytest.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Table(name = "values")
@Entity
class ValueEntity(
    @Column(nullable = false)
    override val name: String,

    @OneToMany(mappedBy = "pk.value")
    val userValues: List<UserValueEntity>,

    @Id
    override val id: Int
): CharacteristicEntity
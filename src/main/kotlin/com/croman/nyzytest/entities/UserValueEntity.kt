package com.croman.nyzytest.entities

import jakarta.persistence.*
import java.io.Serializable
import kotlin.jvm.Transient

@Table(name = "users_interests")
@Entity
@AssociationOverrides(
    AssociationOverride(name = "pk.user", joinColumns = [JoinColumn(name = "id")]),
    AssociationOverride(name = "pk.value", joinColumns = [JoinColumn(name = "id")])
)
class UserValueEntity(
    @Column(nullable = false)
    override val weight: Float,
    @EmbeddedId
    val pk: UserValuePK
): UserCharacteristicEntity {

    @Transient
    override val characteristicId =
        pk.value.id

    @Transient
    override val userId =
        pk.user.id
}

@Embeddable
class UserValuePK(
    @ManyToOne
    val user: UserEntity,
    @ManyToOne
    val value: ValueEntity
) : Serializable
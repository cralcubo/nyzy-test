package com.croman.nyzytest.entities

import jakarta.persistence.*
import java.io.Serializable
import kotlin.jvm.Transient

@Table(name = "users_values")
@Entity
@AssociationOverrides(
    AssociationOverride(name = "pk.user", joinColumns = [JoinColumn(name = "user_id")]),
    AssociationOverride(name = "pk.value", joinColumns = [JoinColumn(name = "value_id")])
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
    @JoinColumn(name = "user_id")
    val user: UserEntity,
    @ManyToOne
    @JoinColumn(name = "value_id")
    val value: ValueEntity
) : Serializable
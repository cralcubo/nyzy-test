package com.croman.nyzytest.entities

import jakarta.persistence.*
import java.io.Serializable
import kotlin.jvm.Transient

@Table(name = "users_values")
@Entity
class UserValueEntity(
    @Column(nullable = false)
    override val weight: Float,

    @Transient
    @MapsId("userId")
    private val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "value_id")
    @MapsId("valueId")
    override val featureEntity: ValueEntity
): UserFeatureEntity {

    @EmbeddedId
    val pk: UserValuePK = UserValuePK(user.id!!, featureEntity.id!!)

}

@Embeddable
class UserValuePK(
    @Column(name = "user_id")
    val userId: Int,
    @Column(name = "value_id")
    val valueId: Int
) : Serializable
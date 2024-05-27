package com.croman.nyzytest.entities

import jakarta.persistence.*
import java.io.Serializable
import kotlin.jvm.Transient

@Table(name = "users_interests")
@Entity
@AssociationOverrides(
    AssociationOverride(name = "pk.interest", joinColumns = [JoinColumn(name = "id")]),
    AssociationOverride(name = "pk.user", joinColumns = [JoinColumn(name = "id")]),
)
class UserInterestEntity(
    @Column(nullable = false)
    override val weight: Float,
    @EmbeddedId
    val pk: UserInterestPK
) : UserCharacteristicEntity {

    @Transient
    override val characteristicId =
        pk.interest.id

    @Transient
    override val userId =
        pk.user.id

}

@Embeddable
class UserInterestPK(
    @ManyToOne
    val user: UserEntity,
    @ManyToOne
    val interest: InterestEntity
) : Serializable

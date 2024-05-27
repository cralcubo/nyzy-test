package com.croman.nyzytest.entities

import jakarta.persistence.*
import java.io.Serializable
import kotlin.jvm.Transient

@Table(name = "users_interests")
@Entity
@AssociationOverrides(
    AssociationOverride(name = "pk.interest", joinColumns = [JoinColumn(name = "interest_id")]),
    AssociationOverride(name = "pk.user", joinColumns = [JoinColumn(name = "user_id")]),
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
    @JoinColumn(name = "user_id")
    val user: UserEntity,
    @ManyToOne
    @JoinColumn(name = "interest_id")
    val interest: InterestEntity
) : Serializable

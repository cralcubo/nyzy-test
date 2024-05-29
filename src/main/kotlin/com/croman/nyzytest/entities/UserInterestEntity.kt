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
    @Transient
    private val user: UserEntity,
    @Transient
    private val interest: InterestEntity
) : UserCharacteristicEntity {

    @EmbeddedId
    private val pk: UserInterestPK = UserInterestPK(user, interest)

    @Transient
    override val characteristicId =
        interest.id!!

    @Transient
    override val userId =
        user.id!!
}

@Embeddable
class UserInterestPK( // should be private
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity,
    @ManyToOne
    @JoinColumn(name = "interest_id")
    val interest: InterestEntity
) : Serializable

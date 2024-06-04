package com.croman.nyzytest.entities

import jakarta.persistence.*
import java.io.Serializable
import kotlin.jvm.Transient

@Table(name = "users_interests")
@Entity
class UserInterestEntity(
    @Column(nullable = false)
    override val weight: Float,

    @Transient
    @MapsId("userId")
    private val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "interest_id")
    @MapsId("interestId")
    override val characteristicEntity: InterestEntity
) : UserCharacteristicEntity {

    @EmbeddedId
    private val pk: UserInterestPK = UserInterestPK(user.id!!, characteristicEntity.id!!)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserInterestEntity

        if (weight != other.weight) return false
        if (pk != other.pk) return false

        return true
    }

    override fun hashCode(): Int {
        var result = weight.hashCode()
        result = 31 * result + pk.hashCode()
        return result
    }

}

@Embeddable
class UserInterestPK(
    @Column(name = "user_id")
    val userId: Int,

    @Column(name = "interest_id")
    val interestId: Int
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserInterestPK

        if (userId != other.userId) return false
        if (interestId != other.interestId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + interestId
        return result
    }
}

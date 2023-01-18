package com.fury.forms.entity

import com.fury.forms.model.UserDetailsModel
import org.hibernate.Hibernate
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "user_table")
data class UserDetailsEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "created_at")
    var createdAt:Date,
    @Column(name = "username")
    var username:String,
    @Column(name = "firstname")
    var firstname: String,
    @Column(name = "lastname")
    var lastname:String,
    @Column(name = "blood_type")
    var bloodType:String,
    @Column(name = "medical_cover")
    var medicalCover:String,
    @Column(name = "updated_at")
    var updatedAt:Date,
    @ManyToOne(fetch = FetchType.EAGER)
    val role:UserRolesEntity,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as UserDetailsEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdAt = $createdAt , username = $username , firstname = $firstname , lastname = $lastname , bloodType = $bloodType , medicalCover = $medicalCover , updatedAt = $updatedAt , role = $role )"
    }
    companion object{
        var now = Date()
        fun newUserDetails(user:UserDetailsModel,role: UserRolesEntity):UserDetailsEntity{
            val newUser = UserDetailsEntity(null, createdAt = now,user.username,user.firstname!!,user.lastname!!,user.bloodType!!,user.medicalCover!!,updatedAt = now, role)
            return newUser
        }
    }
}

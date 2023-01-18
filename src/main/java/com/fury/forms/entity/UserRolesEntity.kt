package com.fury.forms.entity

import org.hibernate.Hibernate
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "role")
data class UserRolesEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "role_name", unique = true, nullable = false)
    @field:NotEmpty
    @field:NotNull
    val name: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as UserRolesEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name )"
    }
    companion object{
        @JvmStatic
        fun newRoles(roleName:String):UserRolesEntity{
            val roleType = UserRolesEntity(null,roleName)
            return roleType
        }
    }
}
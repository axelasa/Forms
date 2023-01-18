package com.fury.forms.service


import com.fury.forms.entity.UserRolesEntity
import java.util.*

interface RoleService {

    fun createRole(role:String): UserRolesEntity

    fun getRole(roleName:String):Optional<UserRolesEntity>

    fun defaultRoles()

}
package com.fury.forms.repository

import com.fury.forms.entity.UserRolesEntity
import com.fury.forms.enums.Roles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository:JpaRepository<UserRolesEntity,Long> {
    fun findByName(name: String): Optional<UserRolesEntity>
}
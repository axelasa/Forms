package com.fury.forms.service

import com.fury.forms.entity.UserRolesEntity
import com.fury.forms.enums.Roles
import com.fury.forms.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleServiceImpl(@Autowired var roleRepository:RoleRepository) : RoleService {
    override fun createRole(role: String): UserRolesEntity {
        val existingRole = getRole(role)
        if (existingRole.isPresent) throw RuntimeException("Role Already Exists")
        val roles = UserRolesEntity.newRoles(role)
        return roleRepository.save(roles)
    }

    override fun getRole(roleName: String): Optional<UserRolesEntity> {
        return roleRepository.findByName(roleName)
    }

    override fun defaultRoles() {
        for (role in Roles.values()){
            if (getRole(role.roles).isEmpty){
                createRole(role.roles)
            }
        }
    }

}
package com.fury.forms.service

import com.fury.forms.entity.UserDetailsEntity
import com.fury.forms.enums.Roles
import com.fury.forms.exceptions.ControllerExceptionHandler
import com.fury.forms.model.UserDetailsModel
import com.fury.forms.repository.UserDetailsRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsServiceImpl(var userDetailsRepo: UserDetailsRepository,var roleService: RoleService) :
    UserDetailsService {
    override fun insertUser(user: UserDetailsModel): UserDetailsEntity {

        val optionalRole = roleService.getRole(Roles.USER.roles)
        if (optionalRole.isEmpty) throw ControllerExceptionHandler.notFound("This Role Does not Exist")

        val existingUser = getUser(user.username)
        if (existingUser.isPresent) throw ControllerExceptionHandler.conflicts("This User Already Exists")
        val newUser = UserDetailsEntity.newUserDetails(user,optionalRole.get())

        return userDetailsRepo.save(newUser)
    }

    override fun getUser(username: String): Optional<UserDetailsEntity> {
        return userDetailsRepo.findByUsername(username)
    }

    override fun getUser(id: Long): Optional<UserDetailsEntity> {
        return userDetailsRepo.findById(id)
    }

    override fun updateUser(userDetails: UserDetailsEntity): UserDetailsEntity {
        return userDetailsRepo.save(userDetails)
    }

    override fun updateUser(userDetails: UserDetailsModel): UserDetailsEntity {
        val existingUser = getUser(userDetails.username)
        if (existingUser.isEmpty) throw RuntimeException("This User Does not Exist")
        var updatedUserDetails = existingUser.get()
        if (userDetails.firstname != null) updatedUserDetails.firstname = userDetails.firstname
        if (userDetails.lastname != null)  updatedUserDetails.lastname = userDetails.lastname
        if (userDetails.bloodType != null) updatedUserDetails.bloodType = userDetails.bloodType
        if (userDetails.medicalCover != null) updatedUserDetails.medicalCover = userDetails.medicalCover
        updatedUserDetails.updatedAt = Date()
        return userDetailsRepo.save(updatedUserDetails)
    }

    override fun getAllUsers(): List<UserDetailsEntity> {
       return userDetailsRepo.findAll()
    }
}
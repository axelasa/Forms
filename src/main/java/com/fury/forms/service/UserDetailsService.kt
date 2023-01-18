package com.fury.forms.service

import com.fury.forms.entity.UserDetailsEntity
import com.fury.forms.model.UserDetailsModel
import java.util.*

interface UserDetailsService  {

    fun insertUser(user:UserDetailsModel):UserDetailsEntity

    fun getUser(username:String):Optional<UserDetailsEntity>

    fun getUser(id:Long):Optional<UserDetailsEntity>

    fun updateUser(userDetails:UserDetailsEntity):UserDetailsEntity

    fun updateUser(userDetails:UserDetailsModel):UserDetailsEntity

    fun getAllUsers():List<UserDetailsEntity>
}
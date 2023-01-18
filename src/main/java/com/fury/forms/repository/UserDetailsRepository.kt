package com.fury.forms.repository

import com.fury.forms.entity.UserDetailsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserDetailsRepository:JpaRepository<UserDetailsEntity,Long> {

    fun findByUsername(username: String): Optional<UserDetailsEntity>
}
package com.fury.forms.controller

import com.fury.forms.entity.UserDetailsEntity
import com.fury.forms.model.ApiResponse
import com.fury.forms.model.UserDetailsModel
import com.fury.forms.service.UserDetailsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("user_details")
class UserDetailsController(var userDetailService:UserDetailsService) {
    @PostMapping
    fun createUser(@Valid @RequestBody userDetails:UserDetailsModel):ResponseEntity<Any>{
        val newUser = userDetailService.insertUser(userDetails)
        return ResponseEntity(ApiResponse(HttpStatus.CREATED.value(),"User Created",newUser), HttpStatus.CREATED)
    }
    @GetMapping
    fun getUser(@RequestParam("username", required = true) username:String):ResponseEntity<Any>{
        val existingUser = userDetailService.getUser(username)
        if (existingUser.isEmpty){
            return ResponseEntity(ApiResponse(HttpStatus.NOT_FOUND.value(),"This User Does Not Exist",existingUser.get()),HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(ApiResponse(HttpStatus.OK.value(),"User Found",existingUser.get()),HttpStatus.OK)
    }
    @PutMapping
    fun updateUser(@RequestBody userDetails: UserDetailsModel):ResponseEntity<Any>{
        val currentUser = userDetailService.updateUser(userDetails)
        return ResponseEntity(ApiResponse(HttpStatus.CREATED.value(),"User Data Updated Successfully",currentUser),HttpStatus.CREATED)
    }
    @GetMapping("all")
    fun getAllUsers(): List<UserDetailsEntity> = userDetailService.getAllUsers()
}
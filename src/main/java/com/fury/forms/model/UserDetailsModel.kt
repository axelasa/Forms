package com.fury.forms.model

import javax.validation.constraints.NotEmpty

data class UserDetailsModel(@field:NotEmpty(message="You must Have A UserName") val username:String,@field:NotEmpty(message="You must Have A FirstName")val firstname: String?,@field:NotEmpty(message="You must Have A LastName")val lastname: String?,@field:NotEmpty(message="You must Have A BloodType")val bloodType:String?,val medicalCover:String?)

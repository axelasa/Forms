package com.fury.forms.app

import com.fury.forms.service.RoleService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OnApplicationStart(val roleService: RoleService) {
    @EventListener(ApplicationReadyEvent::class)
    fun onStartUp(){
        roleService.defaultRoles()
    }
}
package task

import task.service.StackOverflowUsersService

fun main(args: Array<String>){
        val stackOverflowUsersService = StackOverflowUsersService()
         stackOverflowUsersService.printStackOverflowUsers()
    }


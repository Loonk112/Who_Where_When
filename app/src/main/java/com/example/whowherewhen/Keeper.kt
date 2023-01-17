package com.example.whowherewhen

class Keeper {

    fun setTaskGroupId (id: Int) {
        taskGroupId = id
    }

    fun getTaskGroupId (): Int {
        return taskGroupId
    }

    fun setTaskId (id: Int) {
        taskId = id
    }

    fun getTaskId (): Int {
        return taskId
    }

    fun setEmployeeId (id: Int) {
        employeeId = id
    }

    fun getEmployeeId (): Int {
        return employeeId
    }

    fun setUserID (id: Int) {
        userId = id
    }

    fun getUserID (): Int {
        return userId
    }

    fun setGroupEmployeeID (id: Int) {
        groupEmployeeId = id
    }

    fun getGroupEmployeeID (): Int {
        return groupEmployeeId
    }


    companion object {
        var userId: Int = 0
        var taskGroupId: Int = 0
        var taskId: Int = 0
        var employeeId: Int = 0
        var groupEmployeeId: Int = 0
    }
}
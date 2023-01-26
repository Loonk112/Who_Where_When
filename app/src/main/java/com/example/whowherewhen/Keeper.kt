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

    fun setManMain (boolean: Boolean) {
        manMain = boolean
    }

    fun getManMain (): Boolean {
        return manMain
    }

    fun setManEmp (boolean: Boolean) {
        manEmp = boolean
    }

    fun getManEmp (): Boolean {
        return manEmp
    }

    fun setManTask (boolean: Boolean) {
        manTask = boolean
    }

    fun getManTask (): Boolean {
        return manTask
    }

    fun setEmpMain (boolean: Boolean) {
        empMain = boolean
    }

    fun getEmpMain (): Boolean {
        return empMain
    }


    companion object {
        var userId: Int = 0
        var taskGroupId: Int = 0
        var taskId: Int = 0
        var employeeId: Int = 0
        var manMain: Boolean = true
        var manEmp: Boolean = true
        var manTask: Boolean = true
        var empMain: Boolean = true
    }
}
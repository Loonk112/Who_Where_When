package com.example.whowherewhen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.whowherewhen.data.EmployeeData
import com.example.whowherewhen.data.ExtendedGroupEmployeeData
import com.example.whowherewhen.data.TaskData
import com.example.whowherewhen.data.TaskGroupData

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {

        val employeeQuery = ("CREATE TABLE $EMPLOYEE_TABLE_NAME ( $EMPLOYEE_ID_COL INTEGER PRIMARY KEY, $EMPLOYEE_NAME_COl TEXT, $EMPLOYEE_SURNAME_COL TEXT, $EMPLOYEE_PERMISSION_COL TEXT)")
        db.execSQL(employeeQuery)
        val passQuery = ("CREATE TABLE $PASS_TABLE_NAME ( $PASS_ID_COL INTEGER PRIMARY KEY, $PASS_EMPLOYEE_COL INTEGER, $PASS_LOGIN_COl TEXT, $PASS_BODY_COl TEXT, " +
                "FOREIGN KEY ($PASS_EMPLOYEE_COL) REFERENCES ${EMPLOYEE_TABLE_NAME}($EMPLOYEE_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE)")
        db.execSQL(passQuery)
        val taskGroupQuery = ("CREATE TABLE $TASK_GROUP_TABLE_NAME ( $TASK_GROUP_ID_COL INTEGER PRIMARY KEY, $TASK_GROUP_NAME_COL TEXT)")
        db.execSQL(taskGroupQuery)
        val taskQuery = ("CREATE TABLE $TASK_TABLE_NAME ( $TASK_ID_COL INTEGER PRIMARY KEY, $TASK_GROUP_COL INTEGER, $TASK_NAME_COL TEXT, $TASK_STATUS_COL INTEGER, " +
                "FOREIGN KEY ($TASK_GROUP_COL) REFERENCES ${TASK_GROUP_TABLE_NAME}($TASK_GROUP_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE)")
        db.execSQL(taskQuery)
        val groupWorkerQuery = ("CREATE TABLE $GROUP_WORKER_TABLE_NAME ( $GROUP_WORKER_ID_COL INTEGER PRIMARY KEY, $GROUP_WORKER_EMPLOYEE_COL INTEGER, $GROUP_WORKER_GROUP_COL INTEGER, " +
                "FOREIGN KEY ($GROUP_WORKER_EMPLOYEE_COL) REFERENCES ${EMPLOYEE_TABLE_NAME}($EMPLOYEE_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY ($GROUP_WORKER_GROUP_COL) REFERENCES ${TASK_GROUP_TABLE_NAME}($TASK_GROUP_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE)")
        db.execSQL(groupWorkerQuery)
        val timeQuery = ("CREATE TABLE $TIME_TABLE_NAME ( $TIME_ID_COL INTEGER PRIMARY KEY, $TIME_EMPLOYEE_COL INTEGER, $TIME_TASK_COL INTEGER, $TIME_START_COL INTEGER, $TIME_STOP_COL INTEGER, " +
                "FOREIGN KEY ($TIME_EMPLOYEE_COL) REFERENCES ${EMPLOYEE_TABLE_NAME}($EMPLOYEE_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY ($TIME_TASK_COL) REFERENCES ${TASK_TABLE_NAME}($TASK_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE)")
        db.execSQL(timeQuery)
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.execSQL("PRAGMA foreign_keys = ON;")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $EMPLOYEE_TABLE_NAME")
        //TODO: Passwords
        db.execSQL("DROP TABLE IF EXISTS $TASK_GROUP_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TASK_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $GROUP_WORKER_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TIME_TABLE_NAME")
        onCreate(db)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////Employees
    fun addEmployee(name : String, surname : String, perms : String){

        val values = ContentValues()

        values.put(EMPLOYEE_NAME_COl, name)
        values.put(EMPLOYEE_SURNAME_COL, surname)
        values.put(EMPLOYEE_PERMISSION_COL, perms)

        val db = this.writableDatabase

        db.insert(EMPLOYEE_TABLE_NAME, null, values)

        db.close()
    }
    fun deleteEmployee(id: Int) {
        val db = this.writableDatabase
        db.delete(
            EMPLOYEE_TABLE_NAME,
            "$EMPLOYEE_ID_COL = ?",
            arrayOf(id.toString())
        )
    }
    fun getAllEmployees(): ArrayList<EmployeeData> {

        val db = this.readableDatabase
        val sql = "SELECT * FROM $EMPLOYEE_TABLE_NAME"
        val storeEmployees = ArrayList<EmployeeData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val name = cursor.getString(1)
                val surname = cursor.getString(2)
                val perms = cursor.getString(3)
                storeEmployees.add(EmployeeData(id, name, surname, perms))
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return storeEmployees
    }

    fun changeEmployeePerms(id: Int, perms: String) {
        val db = this.writableDatabase
        val rawSQL = "UPDATE $EMPLOYEE_TABLE_NAME SET $EMPLOYEE_PERMISSION_COL = '$perms' WHERE $EMPLOYEE_ID_COL = $id"
        db.execSQL(rawSQL)
    }

    fun getLastAddedEmployeeID(): Int {
        val db = this.readableDatabase
        val sql = "SELECT $EMPLOYEE_ID_COL FROM $EMPLOYEE_TABLE_NAME ORDER BY $EMPLOYEE_ID_COL DESC LIMIT 1"
        var id = 0
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(0).toInt()
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return id
    }

    fun getUnconnectedEmployees(id: Int): ArrayList<EmployeeData> {
        val db = this.readableDatabase
        val sql = "SELECT ${EMPLOYEE_TABLE_NAME}.$EMPLOYEE_ID_COL, $EMPLOYEE_NAME_COl, $EMPLOYEE_SURNAME_COL, $EMPLOYEE_PERMISSION_COL FROM $EMPLOYEE_TABLE_NAME EXCEPT SELECT ${EMPLOYEE_TABLE_NAME}.$EMPLOYEE_ID_COL, $EMPLOYEE_NAME_COl, $EMPLOYEE_SURNAME_COL, $EMPLOYEE_PERMISSION_COL FROM $EMPLOYEE_TABLE_NAME INNER JOIN $GROUP_WORKER_TABLE_NAME ON ${EMPLOYEE_TABLE_NAME}.$EMPLOYEE_ID_COL = ${GROUP_WORKER_TABLE_NAME}.$GROUP_WORKER_EMPLOYEE_COL WHERE ${GROUP_WORKER_TABLE_NAME}.$GROUP_WORKER_GROUP_COL IS $id"
        val storeEmployees = ArrayList<EmployeeData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val name = cursor.getString(1)
                val surname = cursor.getString(2)
                val status = cursor.getString(3)
                storeEmployees.add(EmployeeData(id, name, surname, status))
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return storeEmployees
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////Passwords

    fun addPassword(employee_id : Int, login : String, body : String){

        val values = ContentValues()

        values.put(PASS_EMPLOYEE_COL, employee_id)
        values.put(PASS_LOGIN_COl, login)
        values.put(PASS_BODY_COl, body)

        val db = this.writableDatabase

        db.insert(PASS_TABLE_NAME, null, values)

        db.close()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////Task Groups

    fun addTaskGroup(name : String){

        val values = ContentValues()

        values.put(TASK_GROUP_NAME_COL, name)

        val db = this.writableDatabase

        db.insert(TASK_GROUP_TABLE_NAME, null, values)

        db.close()
    }
    fun deleteTaskGroup(id: Int) {
        val db = this.writableDatabase
        db.delete(
            TASK_GROUP_TABLE_NAME,
            "$TASK_GROUP_ID_COL = ?",
            arrayOf(id.toString())
        )
    }
    fun getAllTaskGroups(): ArrayList<TaskGroupData> {

        val db = this.readableDatabase
        val sql = "SELECT * FROM $TASK_GROUP_TABLE_NAME"
        val storeTaskGroups = ArrayList<TaskGroupData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val name = cursor.getString(1)
                storeTaskGroups.add(TaskGroupData(id, name))
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return storeTaskGroups
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////Tasks

    fun addTaskToGroup(groupId: Int, name : String){
        val values = ContentValues()
        values.put(TASK_NAME_COL, name)
        values.put(TASK_GROUP_COL, groupId)
        values.put(TASK_STATUS_COL, 0)

        val db = this.writableDatabase

        db.insert(TASK_TABLE_NAME, null, values)

        db.close()
    }
    fun deleteTask(id: Int) {
        val db = this.writableDatabase
        db.delete(
            TASK_TABLE_NAME,
            "$TASK_ID_COL = ?",
            arrayOf(id.toString())
        )
    }
    fun getTaskGroupTasks(desiredGroupId: Int): ArrayList<TaskData> {

        val db = this.readableDatabase
        val sql = "SELECT * FROM $TASK_TABLE_NAME WHERE $TASK_GROUP_COL == $desiredGroupId"
        val storeTasks = ArrayList<TaskData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val groupID = cursor.getString(1).toInt()
                val name = cursor.getString(2)
                val status = cursor.getString(3).toInt()
                storeTasks.add(TaskData(id, groupID, name, status))
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return storeTasks
    }

    fun changeTaskStatus(id: Int, status: Int) {
        val db = this.writableDatabase
        val rawSQL = "UPDATE $TASK_TABLE_NAME SET $TASK_STATUS_COL = $status WHERE $TASK_ID_COL = $id"
        db.execSQL(rawSQL)
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////Group - Worker

    fun addGroupWorker(employeeId: Int, groupId: Int){
        val values = ContentValues()
        values.put(GROUP_WORKER_EMPLOYEE_COL, employeeId)
        values.put(GROUP_WORKER_GROUP_COL, groupId)

        val db = this.writableDatabase

        db.insert(GROUP_WORKER_TABLE_NAME, null, values)

        db.close()
    }

    fun getTaskGroupEmployees(id: Int): ArrayList<ExtendedGroupEmployeeData> {
        val db = this.readableDatabase
        val sql = "SELECT ${GROUP_WORKER_TABLE_NAME}.$GROUP_WORKER_ID_COL, ${GROUP_WORKER_TABLE_NAME}.$GROUP_WORKER_GROUP_COL, ${EMPLOYEE_TABLE_NAME}.$EMPLOYEE_ID_COL, $EMPLOYEE_NAME_COl, $EMPLOYEE_SURNAME_COL, $EMPLOYEE_PERMISSION_COL FROM $EMPLOYEE_TABLE_NAME INNER JOIN $GROUP_WORKER_TABLE_NAME ON ${EMPLOYEE_TABLE_NAME}.$EMPLOYEE_ID_COL = ${GROUP_WORKER_TABLE_NAME}.$GROUP_WORKER_EMPLOYEE_COL WHERE ${GROUP_WORKER_TABLE_NAME}.$GROUP_WORKER_GROUP_COL IS $id"
        val storeEmployees = ArrayList<ExtendedGroupEmployeeData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val gro_id = cursor.getString(1).toInt()
                val emp_id = cursor.getString(2).toInt()
                val name = cursor.getString(3)
                val surname = cursor.getString(4)
                val status = cursor.getString(5)
                storeEmployees.add(ExtendedGroupEmployeeData(id, gro_id, emp_id, name, surname, status))
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return storeEmployees
    }

    fun deleteGroupWorker(id: Int) {
        val db = this.writableDatabase
        db.delete(
            GROUP_WORKER_TABLE_NAME,
            "$GROUP_WORKER_ID_COL = ?",
            arrayOf(id.toString())
        )
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////Time table

    companion object{

        private const val DATABASE_NAME = "MAIN_DATABASE"

        private val DATABASE_VERSION = 4

        const val EMPLOYEE_TABLE_NAME = "employee_table"
        const val EMPLOYEE_ID_COL = "id"
        const val EMPLOYEE_NAME_COl = "name"
        const val EMPLOYEE_SURNAME_COL = "surname"
        const val EMPLOYEE_PERMISSION_COL = "permission"

        const val PASS_TABLE_NAME = "password_table"
        const val PASS_ID_COL = "id"
        const val PASS_EMPLOYEE_COL = "employee_id"
        const val PASS_LOGIN_COl = "login"
        const val PASS_BODY_COl = "pass"

        const val TASK_GROUP_TABLE_NAME = "task_group_table"
        const val TASK_GROUP_ID_COL = "id"
        const val TASK_GROUP_NAME_COL = "name"

        const val TASK_TABLE_NAME = "task_table"
        const val TASK_ID_COL = "id"
        const val TASK_GROUP_COL = "group_id"
        const val TASK_NAME_COL = "name"
        const val TASK_STATUS_COL = "status"

        const val GROUP_WORKER_TABLE_NAME = "group_worker_table"
        const val GROUP_WORKER_ID_COL = "id"
        const val GROUP_WORKER_EMPLOYEE_COL = "employee_id"
        const val GROUP_WORKER_GROUP_COL = "group_id"

        const val TIME_TABLE_NAME = "time_table"
        const val TIME_ID_COL = "id"
        const val TIME_EMPLOYEE_COL = "employee_id"
        const val TIME_TASK_COL = "task_id"
        const val TIME_START_COL = "time_start"
        const val TIME_STOP_COL = "time_stop"



    }
}
package com.example.whowherewhen

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {

        var coursesQuery = ("CREATE TABLE $EMPLOYEE_TABLE_NAME ( $EMPLOYEE_ID_COL INTEGER PRIMARY KEY, $EMPLOYEE_NAME_COl TEXT, $EMPLOYEE_SURNAME_COL TEXT, $EMPLOYEE_PERMISSION_COL TEXT)")
        db.execSQL(coursesQuery)
        //TODO: Passwords
        coursesQuery = ("CREATE TABLE $TASK_GROUP_TABLE_NAME ( $TASK_GROUP_ID_COL INTEGER PRIMARY KEY, $TASK_GROUP_NAME_COL TEXT)")
        db.execSQL(coursesQuery)
        coursesQuery = ("CREATE TABLE $TASK_TABLE_NAME ( $TASK_ID_COL INTEGER PRIMARY KEY, $TASK_GROUP_COL INTEGER, $TASK_NAME_COL TEXT," +
                " FOREIGN KEY ($TASK_GROUP_COL) REFERENCES ${TASK_GROUP_TABLE_NAME}($TASK_GROUP_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE)")
        db.execSQL(coursesQuery)
        coursesQuery = ("CREATE TABLE $GROUP_WORKER_TABLE_NAME ( $GROUP_WORKER_ID_COL INTEGER PRIMARY KEY, $GROUP_WORKER_EMPLOYEE_COL INTEGER, $GROUP_WORKER_GROUP_COL INTEGER, " +
                "FOREIGN KEY ($GROUP_WORKER_EMPLOYEE_COL) REFERENCES ${EMPLOYEE_TABLE_NAME}($EMPLOYEE_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY ($GROUP_WORKER_GROUP_COL) REFERENCES ${TASK_GROUP_TABLE_NAME}($TASK_GROUP_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE)")
        db.execSQL(coursesQuery)
        coursesQuery = ("CREATE TABLE $TIME_TABLE_NAME ( $TIME_ID_COL INTEGER PRIMARY KEY, $TIME_EMPLOYEE_COL INTEGER, $TIME_TASK_COL INTEGER, $TIME_START_COL INTEGER, $TIME_STOP_COL INTEGER" +
                "FOREIGN KEY ($TIME_EMPLOYEE_COL) REFERENCES ${EMPLOYEE_TABLE_NAME}($EMPLOYEE_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY ($TIME_TASK_COL) REFERENCES ${TASK_TABLE_NAME}($TASK_ID_COL) ON UPDATE CASCADE ON DELETE CASCADE)")
        db.execSQL(coursesQuery)

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
    fun deleteCourse(id: Int) {
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
        val storeCourses = ArrayList<EmployeeData>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(0).toInt()
                val name = cursor.getString(1)
                val surname = cursor.getString(2)
                val perms = cursor.getString(3)
                storeCourses.add(EmployeeData(id, name, surname, perms))
            }
            while (cursor.moveToNext())
        }
        cursor.close()

        return storeCourses
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////Passwords


    ////////////////////////////////////////////////////////////////////////////////////////////////Task Groups


    ////////////////////////////////////////////////////////////////////////////////////////////////Tasks


    ////////////////////////////////////////////////////////////////////////////////////////////////Group - Worker


    ////////////////////////////////////////////////////////////////////////////////////////////////Time table

    companion object{

        private const val DATABASE_NAME = "MAIN_DATABASE"

        private val DATABASE_VERSION = 1

        const val EMPLOYEE_TABLE_NAME = "employee_table"
        const val EMPLOYEE_ID_COL = "id"
        const val EMPLOYEE_NAME_COl = "name"
        const val EMPLOYEE_SURNAME_COL = "surname"
        const val EMPLOYEE_PERMISSION_COL = "permission"

        //TODO: PASSWORDS TABLE

        const val TASK_GROUP_TABLE_NAME = "task_group_table"
        const val TASK_GROUP_ID_COL = "id"
        const val TASK_GROUP_NAME_COL = "name"

        const val TASK_TABLE_NAME = "task_table"
        const val TASK_ID_COL = "id"
        const val TASK_GROUP_COL = "group_id"
        const val TASK_NAME_COL = "name"

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
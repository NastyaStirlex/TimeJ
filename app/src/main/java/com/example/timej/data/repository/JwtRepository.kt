package com.example.timej.data.repository

import android.content.Context
import javax.inject.Inject

class JwtRepository @Inject constructor() {

    // ACCESS TOKEN

    fun saveAccessToken(context: Context, accessToken: String) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("accessToken", accessToken)
            apply()
        }
    }

    fun getAccessToken(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("accessToken", null)
    }

    fun deleteAccessToken(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("accessToken", null)
            apply()
        }
    }



    // USER ROLE

    fun saveUserRole(role: String, context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("role", role)
            apply()
        }
    }

    fun getUserRole(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("role", null)
    }

    fun deleteUserRole(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("role", null)
            apply()
        }
    }



    // STUDENT'S GROUP ID

    fun saveGroupId(groupId: String, context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("groupId", groupId)
            apply()
        }
    }

    fun getGroupId(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("groupId", null)
    }

    fun deleteGroupId(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("groupId", null)
            apply()
        }
    }



    // STUDENT'S NAME

    fun saveStudentName(name: String, context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("studentName", name)
            apply()
        }
    }

    fun getStudentName(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("studentName", null)
    }

    fun deleteStudentName(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("studentName", null)
            apply()
        }
    }




    // STUDENT'S GROUP NUMBER

    fun saveGroupNumber(groupNumber: String, context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("groupNumber", groupNumber)
            apply()
        }
    }

    fun getGroupNumber(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("groupNumber", null)
    }

    fun deleteGroupNumber(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("groupNumber", null)
            apply()
        }
    }


    // TEACHER NAME

    fun saveTeacherName(name: String, context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("teacherName", name)
            apply()
        }
    }

    fun getTeacherName(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("teacherName", null)
    }

    fun deleteTeacherName(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("teacherName", null)
            apply()
        }
    }


    // TEACHER'S ID

    fun saveTeacherId(id: String, context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("teacherId", id)
            apply()
        }
    }

    fun getTeacherId(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("teacherId", null)
    }

    fun deleteTeacherId(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("teacherId", null)
            apply()
        }
    }



    //REFRESH TOKEN

    fun saveRefreshToken(context: Context, refreshToken: String) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("refreshToken", refreshToken)
            apply()
        }
    }

    fun getRefreshToken(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("refreshToken", null)
    }

    fun deleteRefreshToken(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("refreshToken", null)
            apply()
        }
    }

    //USER'S  EMAIL

    fun saveEmail(context: Context, email: String) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("email", email)
            apply()
        }
    }

    fun getEmail(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("email", null)
    }

    fun deleteEmail(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("email", null)
            apply()
        }
    }


    //USER'S  PASSWORD

    fun savePassword(context: Context, password: String) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("password", password)
            apply()
        }
    }

    fun getPassword(context: Context): String? {
        return context.getSharedPreferences("jwt", Context.MODE_PRIVATE).getString("password", null)
    }

    fun deletePassword(context: Context) {
        val sharedPref = context.getSharedPreferences("jwt", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("password", null)
            apply()
        }
    }
}
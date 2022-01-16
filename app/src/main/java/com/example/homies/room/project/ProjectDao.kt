package com.example.homies.room.project

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProjectDao {
    @Query("SELECT * FROM project ORDER BY clientName")
    fun getAllProjects() : LiveData<List<Project>>

    @Query("SELECT * FROM project WHERE projectCategory = :categoryName ORDER BY clientName")
    fun getAllProjectsByCategory(categoryName : String) : LiveData<List <Project>>

    @Query("SELECT * FROM project WHERE projectId = :id ")
    suspend fun findById(id : Long) : Project

    @Update
    suspend fun update(project : Project)

    @Insert
    suspend fun insert(project : Project)

    @Delete
    suspend fun delete(project : Project)
}
package com.example.homies.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homies.room.project.Project
import com.example.homies.room.project.ProjectDao

@Database(
    entities = [Project::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao() : ProjectDao
}

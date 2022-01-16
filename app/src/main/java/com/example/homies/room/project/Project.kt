package com.example.homies.room.project

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity(tableName = "project")
@Parcelize
data class Project(
    @PrimaryKey(autoGenerate = true) val projectId : Long,
    val clientName : String,
    val clientFirstname : String,
    val clientEmail : String,
    val clientPhone : String,
    val clientAddress : String,
    val projectCategory : String,
    val projectType : String,
    val projectT : String,
    val projectExposition : String,
    val projectSurface : Int,
    val projectLandSurface : Int,
    val projectMainRoomSurface: String,
    val projectKitchenSurface : String,
    val projectRooms : String,
    val projectRoomsSurface: String,
    val projectBathrooms: String,
    val projectOtherRooms: String,
    val projectBalcony : String,
    val projectGarage : String,
    val projectLocation : String,
    val projectPrice : Int,
    val landCharges: Int,
    val charges: String,
    val projectFinancing : String,
    val comments : String,
    val appointmentDate : String,
) : Parcelable

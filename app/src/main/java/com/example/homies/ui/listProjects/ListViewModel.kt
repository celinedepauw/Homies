package com.example.homies.ui.listProjects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homies.room.MyApp
import com.example.homies.room.project.Project
import com.example.homies.room.project.ProjectDao

class ListViewModel : ViewModel() {
    val projectsBuyers : LiveData<List<Project>> = MyApp.db.projectDao().getAllProjectsByCategory("Achat")
    val projectSalers : LiveData<List<Project>> = MyApp.db.projectDao().getAllProjectsByCategory("Vente")
}
package com.example.homies.ui.updateProject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homies.R
import com.example.homies.room.MyApp
import com.example.homies.room.project.Project
import com.example.homies.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateProjectViewModel : ViewModel(){

    private val _isUpdated = MutableLiveData<Boolean>()
    val isUpdated : LiveData<Boolean> = _isUpdated

    private val _projectToUpdate = MutableLiveData<Project>()
    val projectToUpdate : LiveData<Project> = _projectToUpdate

    private val _messageUser = MutableLiveData<Event<Int>>()
    val messageUser : LiveData<Event<Int>> = _messageUser

    init{
        _isUpdated.value = false
    }

    fun displayProject(id : Long){
        var project : Project
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                project = MyApp.db.projectDao().findById(id)
            }
            _projectToUpdate.value = project
        }
    }

    fun updateProject(project : Project){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                MyApp.db.projectDao().update(project)
            }
            _isUpdated.value = true
            _messageUser.value = Event(R.string.projet_modifie)
        }
    }
}
package com.example.homies.ui.detailsProject

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

class DetailsProjectViewModel : ViewModel() {

    private val _projectToDisplay = MutableLiveData<Project>()
    val projectToDisplay : LiveData<Project> = _projectToDisplay

    private val _isDeleted = MutableLiveData<Boolean>()
    val isDeleted : LiveData<Boolean> = _isDeleted

    private val _messageUser = MutableLiveData<Event<Int>>()
    val messageUser : LiveData<Event<Int>> = _messageUser

    init {
        _isDeleted.value = false
    }

    fun displayProject(id : Long){
        var project : Project
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                project = MyApp.db.projectDao().findById(id)
            }
            _projectToDisplay.value = project
        }
    }

    fun deleteProject(project : Project){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                MyApp.db.projectDao().delete(project)
            }
            _isDeleted.value = true
            _messageUser.value = Event(R.string.projet_supprime)
        }
    }
}
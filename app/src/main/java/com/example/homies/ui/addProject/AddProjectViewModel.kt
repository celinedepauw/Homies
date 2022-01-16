package com.example.homies.ui.addProject

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

class AddProjectViewModel : ViewModel() {

    private val _isCreated = MutableLiveData<Boolean>()
    val isCreated : LiveData<Boolean> = _isCreated

    private val _messageUser = MutableLiveData<Event<Int>>()
    val messageUser : LiveData<Event<Int>> = _messageUser

    init{
        _isCreated.value = false
    }

    fun addProject(project : Project){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                MyApp.db.projectDao().insert(project)
            }
            _isCreated.value = true
            _messageUser.value = Event(R.string.projet_cree)
        }
    }
}
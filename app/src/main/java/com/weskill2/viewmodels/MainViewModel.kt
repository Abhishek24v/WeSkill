package com.weskill2.viewmodels

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.weskill2.helper.loggedIn
import com.weskill2.network.ApiException
import com.weskill2.network.models.Resource
import com.weskill2.network.models.trials.TrialsResponse
import com.weskill2.network.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sp: SharedPreferences, private val repository: NetworkRepository) : ViewModel() {

    fun isLoggedIn(): Boolean {
        return sp.getBoolean(loggedIn, false)
    }

    fun getTrials() = liveData<Resource<ArrayList<TrialsResponse>>>(Dispatchers.Default) {
        emit(Resource.Loading())
        try {
            val result = repository.getTrials()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            if (e is ApiException) {
                Timber.e("${e.code} ${e.msg}")
                emit(Resource.Error(e.msg))
            } else {
                Timber.e(e.message.toString())
                emit(Resource.Error("Something went wrong!"))
            }
        }
    }

}
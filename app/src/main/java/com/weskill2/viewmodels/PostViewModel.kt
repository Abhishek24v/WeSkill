package com.weskill2.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.weskill2.network.ApiException
import com.weskill2.network.models.Resource
import com.weskill2.network.models.likes_and_comments.CommentsResponse
import com.weskill2.network.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    fun getComments(postId: String) =
        liveData<Resource<ArrayList<CommentsResponse>>>(Dispatchers.Default) {
            emit(Resource.Loading())
            try {
                val result = repository.getComments(postId = postId)
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

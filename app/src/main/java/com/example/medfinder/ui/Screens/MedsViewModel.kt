package com.example.medfinder.ui.Screens



import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.medfinder.model.MedObject
import com.example.medfinder.model.Meds
import com.example.medfinder.network.MedsApi
import com.example.medfinder.network.MedsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

sealed interface MedsUiState {
    data class Success(val Med: ArrayList<Meds>?) : MedsUiState
    object Error : MedsUiState
    object Loading :MedsUiState
}
class MedsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var medsUiState: MedsUiState by mutableStateOf(MedsUiState.Loading)

        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMeds()
    }

    /**
     * Gets Mars photos information from the Mars API
     */
    fun getMeds() {
       val apiInterface = MedsApi.client.create(MedsApiService::class.java)

        val call = apiInterface.getMeds()

        call.enqueue(object : Callback<MedObject> {
            override fun onResponse(call: Call<MedObject>, response: Response<MedObject>) {
                Log.d("Success!", response.toString())
                var text = response.body()
                val bookList = text?.values
                medsUiState = MedsUiState.Success(bookList)
            }

            override fun onFailure(call: Call<MedObject>, t: Throwable)                  {
                Log.e("Failed Query :(", t.toString())

            }
        })
    }
    }


package com.example.medfinder.ui.Screens



import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medfinder.model.Meds
import com.example.medfinder.network.MedsApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
private val KEY = "AIzaSyAsXkF_dIpWcaVhoiZUtxth6tzjU9pSuPQ"
private val spreadSheetID = "1VEA_bLxt76K2t3b_fnXqcQcAlIGtKpbFUzhs4OkmMy4"
sealed interface MedsUiState {
    data class Success(val meds: List<Meds>) : MedsUiState
    object Error : MedsUiState
    object Loading :MedsUiState
}
class MedsViewModel : ViewModel() {
    var medsUiState: MedsUiState by mutableStateOf(MedsUiState.Loading)
        private set


    init {
        getMeds()
    }


    fun getMeds() {
        viewModelScope.launch {
            medsUiState = MedsUiState.Loading
            val listResult = MedsApi.retrofitService.getValues().values
            var q = ArrayList<Meds>()

            for (items in listResult) {

                   q.add( Meds(
                        MedName = items[0],
                        MedPrice = items[1],
                        MedApteka = items[2],
                        MedAddress = items[3],
                        MedRecept = items[4],
                        MedAnalogue = items[5],
                        MedCategory = items[6],
                        MedId = items[7],
                    ))

            }
            medsUiState = try {
                MedsUiState.Success(q)
            }catch (e: HttpException){
                    MedsUiState.Error

            }catch(e: IOException){
                    MedsUiState.Error
             }

        }

    }
}



package com.toscano.test1.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toscano.test1.logic.entities.FullInfoAnimeLG
import com.toscano.test1.logic.login.jikan.JikanAnimeUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel :ViewModel() {


    val anime = MutableLiveData<FullInfoAnimeLG>()
    val error = MutableLiveData<String>()

    fun loadInfoAnime(animeID : Int){

        viewModelScope.launch (Dispatchers.Main){

            //Llamado intermediode de dos estados
            val resp = withContext(Dispatchers.IO){
                JikanAnimeUserCase().invoke(animeID)
            }

            resp.onSuccess {

                anime.postValue(it)
            }

            resp.onFailure {
                error.postValue(it.message.toString())
            }

        }
    }
}
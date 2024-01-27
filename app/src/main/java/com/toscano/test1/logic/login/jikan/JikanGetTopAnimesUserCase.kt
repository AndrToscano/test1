package com.toscano.test1.logic.login.jikan

import android.util.Log
import com.toscano.test1.data.network.repository.RetrofitBase
import com.toscano.test1.core.Constants
import com.toscano.test1.core.getFullInfoAnimeLG
import com.toscano.test1.data.network.endpoints.TopAnimesEndPoint
import com.toscano.test1.logic.entities.FullInfoAnimeLG

class JikanGetTopAnimesUserCase {

    suspend fun invoke() : Result<List<FullInfoAnimeLG>> {

        var result : Result<List<FullInfoAnimeLG>>? = null

        var items = ArrayList<FullInfoAnimeLG>()

        try {
            val baseService = RetrofitBase.getRetrofitJikanConncetion()

            val service = baseService.create(TopAnimesEndPoint::class.java)

            val callService = service.getAllTopAnimes()


            if (callService.isSuccessful) {

                val infoAnime = callService.body()!!
                infoAnime.data.forEach{
                    items.add(it.getFullInfoAnimeLG())
                }

                result = Result.success(items.toList())

            }

            else{
                Log.e(Constants.TAG, "Error al llamado de la API de Jikan")
                result = Result.failure(Exception("Error al llamado de la API de Jikan"))
            }
        }

        catch(ex: Exception) {
            Log.e(Constants.TAG, ex.stackTraceToString())
            result = Result.failure(Exception(ex))
        }

        return result!!
    }
}
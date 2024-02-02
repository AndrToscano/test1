package com.toscano.test1.logic.login.jikan

import android.util.Log
import com.toscano.test1.core.Constants
import com.toscano.test1.core.getFullInfoAnimeLG
import com.toscano.test1.data.network.endpoints.AnimeEndPoint
import com.toscano.test1.data.network.repository.RetrofitBase
import com.toscano.test1.logic.entities.FullInfoAnimeLG

class JikanAnimeUserCase {

    suspend fun invoke(nameAnime : Int): Result<FullInfoAnimeLG> {

        var result: Result<FullInfoAnimeLG>? = null
        var infoAnime = FullInfoAnimeLG()

        try {
            val baseService = RetrofitBase.getRetrofitJikanConncetion()

            val service = baseService.create(AnimeEndPoint::class.java)

            val callService = service.getAnimeFullInfo(nameAnime)


            if (callService.isSuccessful) {

                val a = callService.body()!!
                infoAnime = a.getFullInfoAnimeLG()
                result = Result.success(infoAnime)

                Log.d(Constants.TAG, "result: "+result)
            }

            else {
                Log.d(Constants.TAG, "Error al llamado de la API de Jikan")

                result = Result.failure(Exception("Error al llamado de la API de Jikan"))
            }

        }
        catch (ex: Exception) {
            Log.e(Constants.TAG,"catch:"+ ex.stackTraceToString())

            result = Result.failure(Exception(ex))
        }

        return result!!
    }
}
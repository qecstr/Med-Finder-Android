package com.example.medfinder

import com.example.medfinder.model.Meds
import com.example.medfinder.network.MedsApiService


interface MedsRepo {
    suspend fun getMeds() : ArrayList<Meds>
}
class Repos (
    private val MedsApiService: MedsApiService
    ) :MedsRepo {
    override suspend fun getMeds(): ArrayList<Meds> {
        val res = MedsApiService.getValues().values
        var q = ArrayList<Meds>(res.size)
        for (items in res) {
            items.map { m ->
                q.add(
                    Meds(
                        MedName = m,
                        MedPrice = m,
                        MedApteka = m,
                        MedAddress = m,
                        MedRecept = m,
                        MedAnalogue = m,
                        MedCategory = m,
                        MedId = m,
                    )
                )
            }
        }
        return q

    }

}

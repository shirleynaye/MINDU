package ec.repositoriocompartido.IngredienteComponets

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getCanalesYoutube(@Url URL:String):Response<IngredienteResponse>
}
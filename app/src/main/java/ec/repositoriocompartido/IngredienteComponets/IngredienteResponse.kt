package ec.repositoriocompartido.IngredienteComponets

import com.google.gson.annotations.SerializedName

data class IngredienteResponse(
    @SerializedName("version") var version:String,
    @SerializedName("ingredientes") var ingredientes:List<Ingrediente>

    )
package ec.repositoriocompartido.IngredienteComponets

data class Ingrediente (
    var id:Int,
    var nombre:String,
    var categoria:String,
    var isselected:Boolean=false
)
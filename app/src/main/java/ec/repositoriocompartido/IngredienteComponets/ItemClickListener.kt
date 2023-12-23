package ec.repositoriocompartido.IngredienteComponets

interface ItemClickListener {
    //Con esta interface le agregamos el actionlistener a cada elmento que se cargue
    //como plantilla con esta accion para llamar al metodo para que se active al seleccionar o desseleccionar el checkbox
    //dfe cada elemento cargado del API
    fun onItemClick(item: Ingrediente)
}
package dgtic.unam.fastfruitandroid

class FoodListSingleton private constructor() {
    companion object {
        private var instance: FoodListSingleton? = null
        fun getInstance(): FoodListSingleton {
            if (instance == null) {
                instance = FoodListSingleton()
            }
            return instance!!
        }
    }
    var foodList = mutableListOf<Food>()
    init {
        foodList.add(Food( R.drawable.tomate, "20", "Tomate","Verduras"))
        foodList.add(Food( R.drawable.calabazas, "25", "Calabacita","Verduras"))
        foodList.add(Food( R.drawable.lechuga, "18", "Lechuga","Verduras"))
        foodList.add(Food( R.drawable.nopal, "10", "Nopal","Verduras"))
        foodList.add(Food( R.drawable.zanahoria, "10", "Zanahoria","Verduras"))
        foodList.add(Food( R.drawable.esparrago, "60", "Esparrago","Verduras"))
        foodList.add(Food( R.drawable.platano, "15", "Plátano","Frutas"))
        foodList.add(Food( R.drawable.manzana, "38", "Manzana","Frutas"))
        foodList.add(Food( R.drawable.sandia, "20", "Sandia","Frutas"))
        foodList.add(Food( R.drawable.papaya, "18", "Papaya","Frutas"))
        foodList.add(Food( R.drawable.uva, "90", "Uva","Frutas"))
        foodList.add(Food( R.drawable.naranja, "25", "Naranja","Frutas"))
        foodList.add(Food( R.drawable.frijol, "18", "Frijol","Otros"))
        foodList.add(Food( R.drawable.aba, "60", "Aba","Otros"))
        foodList.add(Food( R.drawable.chicharo, "24", "Chicharo","Otros"))
        foodList.add(Food( R.drawable.guajillo, "100", "ChileGuajillo","Otros"))
        foodList.add(Food( R.drawable.morita, "90", "ChileMorita","Otros"))
        foodList.add(Food( R.drawable.ancho, "150", "ChileAncho","Otros"))


    }
}

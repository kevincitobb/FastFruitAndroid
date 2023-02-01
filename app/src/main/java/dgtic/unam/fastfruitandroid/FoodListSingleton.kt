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
        foodList.add(Food( R.drawable.tomate, "20", "Plátano","Frutas"))
    }
}

package com.example.thesisapp

class PlasticKindsSet() {
    val plasticKinds: ArrayList<ArrayList<plasticTypeModel>> = ArrayList()
    fun getPlasticTypes(): ArrayList<ArrayList<plasticTypeModel>> {

        val foodPackage: ArrayList<plasticTypeModel> = ArrayList()
        foodPackage.add(plasticTypeModel("Plastic bottle", 100))
        foodPackage.add(plasticTypeModel("Plastic bag", 15))
        foodPackage.add(plasticTypeModel("Yogurt Cup", 70))
        foodPackage.add(plasticTypeModel("Chips bag", 30))

        val cosmetics: ArrayList<plasticTypeModel> = ArrayList()
        cosmetics.add(plasticTypeModel("Shampoo", 40))
        cosmetics.add(plasticTypeModel("Shower gel", 70))
        cosmetics.add(plasticTypeModel("Cream", 20))
        cosmetics.add(plasticTypeModel("Cotton bud", 70))

        val disposablePlastic: ArrayList<plasticTypeModel> = ArrayList()
        disposablePlastic.add(plasticTypeModel("Straw", 40))
        disposablePlastic.add(plasticTypeModel("Plastic cup", 70))
        disposablePlastic.add(plasticTypeModel("Plastic plate", 20))
        disposablePlastic.add(plasticTypeModel("Plastic ", 20))

        plasticKinds.add(foodPackage) // 0
        plasticKinds.add(cosmetics) // 1
        plasticKinds.add(disposablePlastic) // 2

        return plasticKinds
    }

}
//
//    val foodPackage: ArrayList<plasticTypeModel> = ArrayList()
//
//        foodPackage.add(plasticTypeModel("Plastic bottle", 100))
//        foodPackage.add(plasticTypeModel("Plastic bag", 15))
//        foodPackage.add(plasticTypeModel("Yogurt Cup", 70))
//        foodPackage.add(plasticTypeModel("Chips bag", 30))
//
//        plasticKinds.add(foodPackage)
//    }
//}

package com.example.thesisapp.expandableListAddPlastic

import com.example.thesisapp.expandableListAddPlastic.plasticTypeModel

class PlasticKindsSet() {
    val plasticKinds: ArrayList<ArrayList<plasticTypeModel>> = ArrayList()
    fun getPlasticTypes(): ArrayList<ArrayList<plasticTypeModel>> {

        val foodPackage: ArrayList<plasticTypeModel> = ArrayList()
        foodPackage.add(
            plasticTypeModel(
                "Plastic bottle",
                30
            )
        )
        foodPackage.add(
            plasticTypeModel(
                "Plastic bag",
                1
            )
        )
        foodPackage.add(
            plasticTypeModel(
                "Yogurt Cup",
                20
            )
        )
        foodPackage.add(
            plasticTypeModel(
                "Chips bag",
                5
            )
        )

        val cosmetics: ArrayList<plasticTypeModel> = ArrayList()
        cosmetics.add(
            plasticTypeModel(
                "Shampoo",
                40
            )
        )
        cosmetics.add(
            plasticTypeModel(
                "Shower gel",
                40
            )
        )
        cosmetics.add(
            plasticTypeModel(
                "Cream",
                40
            )
        )
        cosmetics.add(
            plasticTypeModel(
                "Cotton bud",
                5
            )
        )
        cosmetics.add(
            plasticTypeModel(
                "Blade razor",
                50
            )
        )

        val disposablePlastic: ArrayList<plasticTypeModel> = ArrayList()
        disposablePlastic.add(
            plasticTypeModel(
                "Straw",
                15
            )
        )
        disposablePlastic.add(
            plasticTypeModel(
                "Plastic cup",
                10
            )
        )
        disposablePlastic.add(
            plasticTypeModel(
                "Plastic plate",
                10
            )
        )
        disposablePlastic.add(
            plasticTypeModel(
                "Plastic cutlery",
                10
            )
        )

        val electronicWastePlastic: ArrayList<plasticTypeModel> = ArrayList()
        electronicWastePlastic.add(
            plasticTypeModel(
                "Headphones",
                80
            )
        )
        electronicWastePlastic.add(
            plasticTypeModel(
                "Phone",
                200
            )
        )
        electronicWastePlastic.add(
            plasticTypeModel(
                "Charger",
                100
            )
        )
        electronicWastePlastic.add(
            plasticTypeModel(
                "Cable",
                50
            )
        )
        electronicWastePlastic.add(
            plasticTypeModel(
                "Laptop",
                3000
            )
        )

        plasticKinds.add(foodPackage) // 0
        plasticKinds.add(cosmetics) // 1
        plasticKinds.add(disposablePlastic) // 2
        plasticKinds.add(electronicWastePlastic) // 3

        return plasticKinds
    }

}

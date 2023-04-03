package com.mobilearts.nftworld.objects

import com.mobilearts.nftworld.R
import com.mobilearts.nftworld.model.BottomModel

object BottomObjects {
    fun getBottomAdapterList(position: Int): List<BottomModel> {
        return when (position) {
            // WATER
            0 -> listOf(
                BottomModel(R.drawable.img_water, "Water",100,false),
                BottomModel(R.drawable.img_wateraleo_water, "Aleo Water",96,false),
                BottomModel(R.drawable.img_waterantioxidant_water, "Antioxidant Water",98,false),
                BottomModel(R.drawable.img_waterartesian_water, "Artesian Water",100,false)
            )
            // DETOX
            1 -> listOf(
                BottomModel(R.drawable.img_detoxenergy_drink, "Energy Drink",88,true),
                BottomModel(R.drawable.img_detoxrecovery_drink, "Recovery Drink",92,true),
                BottomModel(R.drawable.img_detoxprotein_shake, "Protein Shake",92,true),
                BottomModel(R.drawable.img_detoxsports_drink, "Sports Drink",93,true),
                BottomModel(R.drawable.img_detoxzero_sports, "Zero Sports Drink",98,true),
                BottomModel(R.drawable.img_detoxzero_sports, "Green Detox",88,true),
            )
            // MILKS
            2 -> listOf(
                BottomModel(R.drawable.img_milk, "Milk",90,true),
                BottomModel(R.drawable.img_milkalmond_milk, "Almond Milk",96,true),
                BottomModel(R.drawable.img_milkchocolate_milk, "Chocolate Milk",82,true),
                BottomModel(R.drawable.img_milkcashew_milk, "Cashew Milk",96,true),
                BottomModel(R.drawable.img_milklactose_free, "Lactose Free Milk",88,true),
            )
            // BEERS
            3 -> listOf(
                BottomModel(R.drawable.img_alcoholbeer, "Beer",-60,true),
                BottomModel(R.drawable.img_alcoholapple_wine, "Apple Wine",-50,true),
                BottomModel(R.drawable.img_alcoholbrandi, "Brandi",-480,true),
                BottomModel(R.drawable.img_alcoholgin, "Gin",-480,true),
                BottomModel(R.drawable.img_alcoholwhite_wine, "White Wine",-140,true),
            )
            // COFFEE
            4 -> listOf(
                BottomModel(R.drawable.img_coffeesamericano, "Americano",96,true),
                BottomModel(R.drawable.img_coffeescappucino, "Cappucino",80,true),
                BottomModel(R.drawable.img_coffeeschicory, "Chicory",95,true),
                BottomModel(R.drawable.img_coffeescortado, "Cortado",92,true),
                BottomModel(R.drawable.img_coffeesiced_coffee, "Iced Coffee",86,true),
            )
            // JUICES
            5 -> listOf(
                BottomModel(R.drawable.img_juiceapple_juice, "Apple Juice",80,true),
                BottomModel(R.drawable.img_juicecarrot_juice, "Carrot Juice",89,true),
                BottomModel(R.drawable.img_juicecompote, "Compote",70,true),
                BottomModel(R.drawable.img_juicekissel, "Kissel",60,true),
                BottomModel(R.drawable.img_juicetomato_juice, "Tomato Juice",93,true),
            )
            // TEA
            6 -> listOf(
                BottomModel(R.drawable.img_teasblack_tea, "Black Tea",98,true),
                BottomModel(R.drawable.img_teasice_tea, "Ice Tea",90,true),
                BottomModel(R.drawable.img_teaskombucha, "Kombucha",80,true),
                BottomModel(R.drawable.img_teasmate, "Mate",45,true),
                BottomModel(R.drawable.img_teasoolong_tea, "Oolong Tea",97,true),
            )
            // OTHERS
            7 -> listOf(
                BottomModel(R.drawable.img_othersayran, "Ayran",43,true),
                BottomModel(R.drawable.img_otherscacao, "Cacao",65,true),
                BottomModel(R.drawable.img_othershot_chocolate, "Hot Chocolate",80,true),
                BottomModel(R.drawable.img_otherskefir, "Kefir",89,true),
                BottomModel(R.drawable.img_othersmilkshake, "Milkshake",72,true),
            )
            else -> listOf(
                BottomModel(R.drawable.img_water, "Water", 100,false),
                BottomModel(R.drawable.img_wateraleo_water, "Aleo Water", 96,false),
                BottomModel(R.drawable.img_waterantioxidant_water, "Antioxidant Water", 98,false),
                BottomModel(R.drawable.img_waterartesian_water, "Artesian Water", 100,false)
            )
        }
    }
}

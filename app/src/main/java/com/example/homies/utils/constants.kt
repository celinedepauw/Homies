package com.example.homies

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object Category{
    val BUY = "Achat"
    val SALE = "Vente"
    val NOCATEGORY = "Catégorie non communiquée"
}

object Type{
    val HOUSE = "Maison"
    val APPARTMENT = "Appartement"
    val LOCAL = "Local"
    val LAND = "Terrain"
    val NOTYPE = "Type non communiqué"
}

object Financing{
    val BANK = "Crédit banque"
    val CASH = "Paiement comptant"
    val MIDDLEMAN = "Courtier"
    val NOFINANCING = "Non communiqué"
}

fun Context.showMyDialog(message: String? = null, resId: Int = 0){
    val msg = message ?: this.getString(resId)
    AlertDialog.Builder(this).run{
        setTitle("")
        setMessage(msg)
        setPositiveButton("OK"){ dialog, _ -> dialog.cancel()}
        show()
    }
}





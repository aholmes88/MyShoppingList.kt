package com.example.myshoppinglist


import java.util.Locale
import android.icu.text.SimpleDateFormat
import java.util.Date


//class ShoppingItem {
//    data class ShoppingItem(
//        val id: Int,
//        var name: String,
//        var qty: Int,
//        val createdAt: String = SimpleDateFormat("yyyy", Locale.getDefault()).toString(),
//        var isEditing: Boolean = false
// )

data class ShoppingItem(private val id: Int, private var name: String, private var qty: Int, private var isEditing:Boolean) {
    private var createAt: String =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())


    fun getId(): Int {
        return this.id
    }

    fun getName(): String {
        return this.name
    }

    fun getQty(): Int {
        return this.qty
    }

    fun setId(): Int {
        return this.id
    }

    fun setName(): String {
        return this.name
    }

    fun setQty(): Int {
        return this.qty
    }

    override
    fun toString(): String {
        return "Item(id: ${this.id}, Name: ${this.name}, Qty: ${this.qty}, CreatedAt: ${this.createAt},isEditing: ${this.isEditing}"
    }


}
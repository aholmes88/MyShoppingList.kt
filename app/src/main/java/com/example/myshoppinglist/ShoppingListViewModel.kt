package com.example.myshoppinglist


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Create the class to make the view model

class ShoppingListViewModel : ViewModel() {
    private val _shoppingListItems = MutableStateFlow<List<ShoppingItem>>(emptyList())
    val shoppingListItems: StateFlow<List<ShoppingItem>> = _shoppingListItems

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    /*
    * 5 functions to add to the viewmodel
    *
    * [x] 1  add shopping list
    * 2 delete the shoppign list
    * 3 update
    * 4 toggle edit
    * 5 show dialog
    */


    fun addShoppingItem(name: String, qty: Int ,isEditing :Boolean) {
        val newItem = ShoppingItem(
            id = _shoppingListItems.value.size + 1,
            name = name,
            qty = qty,
            isEditing = false
        )
        _shoppingListItems.value += newItem
    }

    fun removeShoppingItem(item: ShoppingItem) {
        _shoppingListItems.value = _shoppingListItems.value.filter { it.getId() != item.getId() }
    }

    //Setters
    fun updateShoppingItem(item: ShoppingItem, newName: String, newQty: Int) {
        _shoppingListItems.value = _shoppingListItems.value.map {
            if (it.getId() == item.getId()) {
                it.copy(name = newName, qty = newQty, isEditing = false)
            } else {
                it
            }

        }
    }

    fun toggleEditing(item: ShoppingItem) {
        _shoppingListItems.value = _shoppingListItems.value.map {
            it.copy(isEditing = it.getId() == item.getId())

        }
    }

    fun setShowDialog(show: Boolean) {
        _showDialog.value = show
    }


    ///Getters
fun getShoppingItemById(id : Int):ShoppingItem? {
    return _shoppingListItems.value.find { it.getId() == id }

    }







}
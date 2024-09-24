package com.example.myshoppinglist

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Locale


//    How do we store the items & quantities
//    A: We create a class with the parameters/variables we need for the object. Such as shopping list item
//    which takes an Id, name, qty, created and isEditing


@Composable
fun MyShoppingListApp() {
    var shoppingListItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQty by remember { mutableStateOf("1") }

    var example1 = ShoppingItem(id = 1, "Milk", qty = 5, isEditing = false)

    shoppingListItems += example1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),


        ) {

        Button(onClick = { showDialog = true }) {
            Text("Add Item")
        }

        LazyColumn(modifier = Modifier.fillMaxSize())
        {
            items(listOf("Item 1", "Item 2", "Item 3", "Item 4")) { item ->
                Text(item)
            }


        }
        if (showDialog) {
            AlertDialog(onDismissRequest = { showDialog = false },
                confirmButton = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {
                            if (itemName.isNotEmpty()) {
                                val newItem = ShoppingItem(
                                    id = shoppingListItems.size + 1,
                                    name = itemName,
                                    qty = itemQty.toIntOrNull() ?: 1,
                                    isEditing = false
                                )
                                shoppingListItems += newItem
                            }

                        }) {
                            Text("Add")
                        }
                        Button(onClick = {
                            showDialog = false
                            itemName = ""
                            itemQty = "1"
                        }) {
                            Text("Cancel")
                        }
                    }
                },
                title = {
                    Text("Add Shopping Items")
                },
                text = {
                    Column() {
                        OutlinedTextField(
                            value = itemName,
                            onValueChange = { itemName = it },
                            placeholder = { Text("Enter Item Here:") })

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = itemQty,
                            onValueChange = { itemName = it },
                            placeholder = { Text("Enter Item Here:") })
                    }
                })
        }
    }


    @Composable
    fun ShoppingListItemContainer(
        item: ShoppingItem,
        onEditClick: () -> Unit,
        onDeleteClick: () -> Unit
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = item.name, modifier = Modifier.padding(16.dp))
            Text(text = "Qty: ${item.qty}", modifier = Modifier.padding(16.dp))
        }
        IconButton(onClick = { onEditClick }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = { onDeleteClick }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }

    }
    @Composable
    fun EditShoppingItem(item: ShoppingItem, onEditComplete: () -> Unit) {
        var editItemName by remember { mutableStateOf(item.name)}
            var editItemQty by remember { mutableStateOf(item.qty.toString())}
                var isEditing by remember { mutableStateOf(item.isEditing) }

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceEvenly){

        }
        Column {
            Text("Edit Item: ${item.id}")
            TextField(
                value = editItemName,
                onValueChange = {editItemName = it},
                singleLine = true,
                modifier = Modifier.wrapContentSize().padding(16.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = editItemQty,
                onValueChange = {editItemQty = it},
                singleLine = true,
                modifier = Modifier.wrapContentSize().padding(16.dp))

        Button(onClick = {
            isEditing= false
            onEditComplete()
        })
        }

            }}
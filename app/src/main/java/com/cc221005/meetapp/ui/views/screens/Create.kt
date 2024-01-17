package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221005.meetapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Create() {

    var title by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var date by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var cost by remember { mutableStateOf(TextFieldValue("")) }
    var attend by remember { mutableStateOf(TextFieldValue("")) }




    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),

    ) {

//        Text(text = "Image", fontSize = 50.sp, style = TextStyle(fontFamily = FontFamily.Cursive))
        Image(
            painter = painterResource(id = R.drawable.group_create),
            contentDescription = "group",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(220.dp)
                .width(330.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(20.dp))


        TextField(
            modifier = Modifier.width(330.dp),
            value = title,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.Yellow,
                errorIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Black,

                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Gray,
                disabledLabelColor = Color.Yellow,
                errorLabelColor = Color.Red,

                cursorColor = Color.Gray,
                containerColor = Color.Transparent
            ),
            onValueChange = { newText ->
                title = newText
            },

            label = { Text(text = "Title*", style = TextStyle(fontFamily = FontFamily.Default)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.textfield_icon),
                    contentDescription = null,
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            modifier = Modifier.width(330.dp),
            value = date,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.Yellow,
                errorIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Black,

                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Gray,
                disabledLabelColor = Color.Yellow,
                errorLabelColor = Color.Red,

                cursorColor = Color.Gray,
                containerColor = Color.Transparent
            ),

            onValueChange = { newText ->
                date = newText
            },

            label = { Text(text = "Date*", style = TextStyle(fontFamily = FontFamily.Default)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            modifier = Modifier.width(330.dp),
            value = description,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.Yellow,
                errorIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Black,

                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Gray,
                disabledLabelColor = Color.Yellow,
                errorLabelColor = Color.Red,

                cursorColor = Color.Gray,
                containerColor = Color.Transparent
            ),

            onValueChange = { newText ->
                description = newText
            },

            label = {
                Text(text = "Description", style = TextStyle(fontFamily = FontFamily.Default)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(

        ) {
            TextField(
                modifier = Modifier.width(155.dp),
                value = cost,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Gray,
                    disabledIndicatorColor = Color.Yellow,
                    errorIndicatorColor = Color.Red,
                    unfocusedIndicatorColor = Color.Black,

                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    disabledLabelColor = Color.Yellow,
                    errorLabelColor = Color.Red,

                    cursorColor = Color.Gray,
                    containerColor = Color.Transparent
                ),

                onValueChange = { newText ->
                    cost = newText
                },
                label = { Text(text = "€/ticket", style = TextStyle(fontFamily = FontFamily.Default)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.wallet_icon),
                        contentDescription = null,
                    )
                }
            )

            Spacer(modifier = Modifier.width(20.dp))

            TextField(
                modifier = Modifier.width(155.dp),
                value = attend,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Gray,
                    disabledIndicatorColor = Color.Yellow,
                    errorIndicatorColor = Color.Red,
                    unfocusedIndicatorColor = Color.Black,

                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    disabledLabelColor = Color.Yellow,
                    errorLabelColor = Color.Red,

                    cursorColor = Color.Gray,
                    containerColor = Color.Transparent
                ),

                onValueChange = { newText ->
                    attend = newText
                },

                label = { Text(text = "Max. attend.", style = TextStyle(fontFamily = FontFamily.Default)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.group_icon),
                        contentDescription = null,
                    )
                }
            )

        }



//        Text(text = "Create")


        Button(
            onClick = { },
            modifier = Modifier.padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text(text = "Save", fontSize = 20.sp, style = TextStyle(fontFamily = FontFamily.Default),
                fontWeight = FontWeight.Bold)
        }
    }
}

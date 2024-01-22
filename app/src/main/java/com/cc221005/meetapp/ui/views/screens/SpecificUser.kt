package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221005.meetapp.R

@Composable
fun User() {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {

        //---------------------------------------------------------------------------------------
        //-------------------------------------Profile Info--------------------------------------
        //---------------------------------------------------------------------------------------
        Image(
            painter = painterResource(id = R.drawable.face),
            contentDescription = "group",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Trevor", style = TextStyle(fontFamily = FontFamily.Default, fontSize = 25.sp))

        Spacer(modifier = Modifier.height(30.dp))

        //-------------------------------------Followers/ing count-------------------------------------
        Row {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "120", style = TextStyle(fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold, fontSize = 19.sp))
                Text(text = "Followers", style = TextStyle(fontFamily = FontFamily.Default, fontSize = 13.sp))
            }

            Spacer(modifier = Modifier.width(70.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "56", style = TextStyle(fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold, fontSize = 19.sp))
                Text(text = "Following", style = TextStyle(fontFamily = FontFamily.Default, fontSize = 13.sp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //-------------------------------------Description and #-------------------------------------
        Text(modifier = Modifier
            .padding(start = 20.dp)
            .padding(end = 20.dp),
            text = "I’m Trevor, a 28-year-old graphic designer living in Los Angeles. " +
                    "I love connecting with others in my community.",
            style = TextStyle(fontFamily = FontFamily.Default, fontSize = 17.sp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(modifier = Modifier
            .padding(start = 20.dp)
            .padding(end = 20.dp),
            text = "#graphic #gaming #single #la #travel #cycling #sports #gym #eat #drink #pets #dogs",
            style = TextStyle(fontFamily = FontFamily.Default, fontSize = 13.sp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        //-------------------------------------Follow Button-------------------------------------
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .padding(end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text(text = "Follow", fontSize = 17.sp, style = TextStyle(fontFamily = FontFamily.Default),
                fontWeight = FontWeight.Bold)
        }

        //-------------------------------------Unfolllow and Message Button-------------------------------------

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.width(170.dp)
            ) {
                Text(text = "Unfollow", style = TextStyle(fontFamily = FontFamily.Default, fontSize = 18.sp),
                    fontWeight = FontWeight.Bold, color = Color.Black)

            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = { },
                modifier = Modifier.width(170.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(text = "Message", style = TextStyle(fontFamily = FontFamily.Default, fontSize = 18.sp),
                    fontWeight = FontWeight.Bold)
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        //------------------------------------------------------------------------------------------
        //-------------------------------------Event Navigation-------------------------------------
        //------------------------------------------------------------------------------------------

        val hostedBorder = remember { mutableStateOf(Color.Transparent) }
        val upcomingBorder = remember { mutableStateOf(Color.Transparent) }
        val visitedBorder = remember { mutableStateOf(Color.Transparent) }

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {

            //-------------------------------------Hosted-------------------------------------
            Button(
                onClick = { hostedBorder.value = Color.Black
                    upcomingBorder.value = Color.Transparent
                    visitedBorder.value = Color.Transparent
                },
                modifier = Modifier
                    .height(60.dp)
                    .drawWithContent {
                        drawContent()
                        drawRoundRect(
                            color = hostedBorder.value,
                            size = Size(width = size.width, height = 6.dp.toPx()),
                            cornerRadius = CornerRadius(x = 36.dp.toPx(), y = 36.dp.toPx()),
                            topLeft = Offset(x = 0.dp.toPx(), y = 55.dp.toPx())
                        )
                        drawLine(
                            color = hostedBorder.value,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 10f,
                        )
                    },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "5", style = TextStyle(fontFamily = FontFamily.Default,
                        fontSize = 14.sp, color = Color.Black))

                    Text(text = "Hosted", style = TextStyle(fontFamily = FontFamily.Default, fontSize = 18.sp),
                        fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }

            //-------------------------------------Upcoming-------------------------------------
            Button(
                onClick = { upcomingBorder.value = Color.Black
                    hostedBorder.value = Color.Transparent
                    visitedBorder.value = Color.Transparent
                },
                modifier = Modifier
                    .height(60.dp)
                    .drawWithContent {
                        drawContent()
                        drawRoundRect(
                            color = upcomingBorder.value,
                            size = Size(width = size.width, height = 6.dp.toPx()),
                            cornerRadius = CornerRadius(x = 36.dp.toPx(), y = 36.dp.toPx()),
                            topLeft = Offset(x = 0.dp.toPx(), y = 55.dp.toPx())
                        )
                        drawLine(
                            color = upcomingBorder.value,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 10f
                        )
                    },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "4", style = TextStyle(fontFamily = FontFamily.Default,
                        fontSize = 14.sp, color = Color.Black))

                    Text(text = "Upcoming", style = TextStyle(fontFamily = FontFamily.Default, fontSize = 18.sp),
                        fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }


            //-------------------------------------Visited-------------------------------------
            Button(
                onClick = { visitedBorder.value = Color.Black
                    hostedBorder.value = Color.Transparent
                    upcomingBorder.value = Color.Transparent
                },
                modifier = Modifier
                    .height(60.dp)
                    .drawWithContent {
                        drawContent()
                        drawRoundRect(
                            color = visitedBorder.value,
                            size = Size(width = size.width, height = 6.dp.toPx()),
                            cornerRadius = CornerRadius(x = 36.dp.toPx(), y = 36.dp.toPx()),
                            topLeft = Offset(x = 0.dp.toPx(), y = 55.dp.toPx())
                        )
                        drawLine(
                            color = visitedBorder.value,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 10f
                        )
                    },
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "25", style = TextStyle(fontFamily = FontFamily.Default,
                        fontSize = 14.sp, color = Color.Black))

                    Text(text = "Visited", style = TextStyle(fontFamily = FontFamily.Default, fontSize = 18.sp),
                        fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        //-------------------------------------Line-------------------------------------
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 1.dp)
                .background(color = Color.Gray)
        )
        //---------------------------------------------------------------------------------------
        //-------------------------------------Event Content-------------------------------------
        //---------------------------------------------------------------------------------------

//        items(state.value.students){
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
            .padding(2.dp)
            .padding(2.dp)
//                .clickable { viewModel.editStudent(it) }
        ){

            Row(modifier = Modifier.offset( y = 10.dp, x = 10.dp )) {
                Image(
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "group",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }


            Column (modifier = Modifier.offset(y = 15.dp, x = 10.dp),) {

                Text(text = "Canine & Canvas Art Mixer", fontSize = 16.sp,
                    style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .padding(end = 20.dp),
                )

                Text(text = "in Downtown LA", fontSize = 11.sp, style = TextStyle(fontFamily = FontFamily.Default),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .padding(end = 20.dp),
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "Unleash your creativity in a unique art experience! Join us for a...",
                    fontSize = 15.sp, style = TextStyle(fontFamily = FontFamily.Default),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .padding(end = 20.dp),
                )

            }
        }




    }
}

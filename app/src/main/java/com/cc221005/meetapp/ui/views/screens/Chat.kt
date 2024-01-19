package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221005.meetapp.R

@Composable
fun Chat() {
    Column (
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {

        //Chat 1
        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
//                .clickable { viewModel.editStudent(it) }
        ){


            Image(
                painter = painterResource(id = R.drawable.face),
                contentDescription = "group",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )



            Column (modifier = Modifier.offset(y = 15.dp, x = 10.dp),
                verticalArrangement = Arrangement.Center,) {

                Text(text = "MeetApp Team", fontSize = 15.sp,
                    style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 5.dp),
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "\uD83C\uDF89 Welcome to MeetApp! \uD83C\uDF1F Get ready... ",
                    fontSize = 13.sp, style = TextStyle(fontFamily = FontFamily.Default),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 5.dp),
                )
            }

            Column (modifier = Modifier.offset(y = 15.dp, x = 10.dp),
                verticalArrangement = Arrangement.Center,) {

                Text(text = "12:45", fontSize = 11.sp,
                    style = TextStyle(fontFamily = FontFamily.Default),
                    modifier = Modifier
                        .padding(end = 20.dp),
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    modifier = Modifier
                        .offset(y = 5.dp, x = 15.dp)
                        .drawBehind {
                            drawCircle(
                                color = Color.Magenta,
                                radius = 25f,
                            )
                        },
                    text = "1", fontSize = 12.sp, color = Color.White,
                    style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold)
                )

            }



        }

        //Chat 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .padding(start = 20.dp)
//                .clickable { viewModel.editStudent(it) }
        ){


            Image(
                painter = painterResource(id = R.drawable.face),
                contentDescription = "group",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )



            Column (modifier = Modifier.offset(y = 15.dp, x = 10.dp),
                verticalArrangement = Arrangement.Center,) {

                Text(text = "Liam_Lagoon", fontSize = 15.sp,
                    style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 20.dp),
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "Just discovered a hidden gem for our next... ",
                    fontSize = 13.sp, style = TextStyle(fontFamily = FontFamily.Default),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 20.dp),
                )

            }
        }

        //Chat 3
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .padding(start = 20.dp)
//                .clickable { viewModel.editStudent(it) }
        ){


            Image(
                painter = painterResource(id = R.drawable.face),
                contentDescription = "group",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )



            Column (modifier = Modifier.offset(y = 15.dp, x = 10.dp),
                verticalArrangement = Arrangement.Center,) {

                Text(text = "Johnny_Jonson", fontSize = 15.sp,
                    style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 20.dp),
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "Planning the next great adventure – any...",
                    fontSize = 13.sp, style = TextStyle(fontFamily = FontFamily.Default),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 20.dp),
                )

            }
        }

        //Chat 4
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .padding(start = 20.dp)
//                .clickable { viewModel.editStudent(it) }
        ){


            Image(
                painter = painterResource(id = R.drawable.face),
                contentDescription = "group",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )



            Column (modifier = Modifier.offset(y = 15.dp, x = 10.dp),
                verticalArrangement = Arrangement.Center,) {

                Text(text = "Olivia_Onyx", fontSize = 15.sp,
                    style = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 20.dp),
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(text = "Sunsets are proof that no matter what,...",
                    fontSize = 13.sp, style = TextStyle(fontFamily = FontFamily.Default),
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .padding(end = 20.dp),
                )

            }
        }








    }



}
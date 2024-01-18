package com.cc221005.meetapp.ui.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cc221005.meetapp.R

@Composable
fun Home() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        //Users Stories
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {

            //YOUR STORY
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 20.dp),

                ) {
                Image(
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "group",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "Your Story",
                    style = TextStyle(fontFamily = FontFamily.Default, fontSize = 13.sp)
                )
            }


            //USERS
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(end = 20.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "group",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.Black, CircleShape)
                )
                Text(
                    text = "lilli_faraday",
                    style = TextStyle(fontFamily = FontFamily.Default, fontSize = 13.sp)
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(end = 20.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "group",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.Black, CircleShape)
                )
                Text(
                    text = "lilli_faraday",
                    style = TextStyle(fontFamily = FontFamily.Default, fontSize = 13.sp)
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(end = 20.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "group",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color.Black, CircleShape)
                )
                Text(
                    text = "lilli_faraday",
                    style = TextStyle(fontFamily = FontFamily.Default, fontSize = 13.sp)
                )
            }
        }




        Spacer(modifier = Modifier.height(25.dp))

//------------------------------------------------------------------------------------------------------
        // Recommended Title
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Column {

                    Text(
                        text = "Recommended", style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = "for you", style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 14.sp
                        )
                    )
                }

                //
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                ) {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            Icons.Rounded.KeyboardArrowRight, "Edit",
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
            }
        }




        Spacer(modifier = Modifier.height(15.dp))


        // Recommended Events
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {

            // EVENTS 1
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .width(340.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .paint(
                        painterResource(id = R.drawable.waterfall),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.BottomCenter,
            )
            {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(320.dp)
                        .height(100.dp)
                        .padding(bottom = 10.dp),
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.black),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        alpha = 0.6F,
                        modifier = Modifier
                            .height(220.dp)
                            .width(330.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Column(modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 5.dp)
                        ) {

                        Text(
                            text = "Student Party", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 17.sp,
                                color = Color.White, fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "in St. Polten", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 11.sp, color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(3.dp))


                        Text(
                            text = "Get ready to unleash the ultimate student party experience!" +
                                    " Join us for a night of...", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 14.sp, color = Color.White
                            )
                        )


                    }
                }
            }



            // EVENTS 2
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 20.dp)
                    .width(340.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .paint(
                        painterResource(id = R.drawable.waterfall),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.BottomCenter,
            )
            {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(320.dp)
                        .height(100.dp)
                        .padding(bottom = 10.dp),
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.black),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        alpha = 0.6F,
                        modifier = Modifier
                            .height(220.dp)
                            .width(330.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Column(modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 5.dp)
                    ) {

                        Text(
                            text = "Student Party", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 17.sp,
                                color = Color.White, fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "in St. Polten", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 11.sp, color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(3.dp))


                        Text(
                            text = "Get ready to unleash the ultimate student party experience!" +
                                    " Join us for a night of...", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 14.sp, color = Color.White
                            )
                        )


                    }

                }


            }
        }


        Spacer(modifier = Modifier.height(25.dp))

        //------------------------------------------------------------------------------------------------------
        // Top Events Title
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Column {

                    Text(
                        text = "Top Events", style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = "nearby St. Polten", style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 14.sp
                        )
                    )
                }

                //
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                ) {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            Icons.Rounded.KeyboardArrowRight, "Edit",
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
            }
        }




        Spacer(modifier = Modifier.height(15.dp))


        // Top Events
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {

            // EVENTS 1
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .width(340.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .paint(
                        painterResource(id = R.drawable.waterfall),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.BottomCenter,
            )
            {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(320.dp)
                        .height(100.dp)
                        .padding(bottom = 10.dp),
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.black),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        alpha = 0.6F,
                        modifier = Modifier
                            .height(220.dp)
                            .width(330.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Column(modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 5.dp)
                    ) {

                        Text(
                            text = "Student Party", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 17.sp,
                                color = Color.White, fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "in St. Polten", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 11.sp, color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(3.dp))


                        Text(
                            text = "Get ready to unleash the ultimate student party experience!" +
                                    " Join us for a night of...", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 14.sp, color = Color.White
                            )
                        )


                    }
                }
            }



            // EVENTS 2
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 20.dp)
                    .width(340.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .paint(
                        painterResource(id = R.drawable.waterfall),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.BottomCenter,
            )
            {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(320.dp)
                        .height(100.dp)
                        .padding(bottom = 10.dp),
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.black),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        alpha = 0.6F,
                        modifier = Modifier
                            .height(220.dp)
                            .width(330.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Column(modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 5.dp)
                    ) {

                        Text(
                            text = "Student Party", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 17.sp,
                                color = Color.White, fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "in St. Polten", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 11.sp, color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(3.dp))


                        Text(
                            text = "Get ready to unleash the ultimate student party experience!" +
                                    " Join us for a night of...", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 14.sp, color = Color.White
                            )
                        )


                    }

                }


            }
        }


        Spacer(modifier = Modifier.height(25.dp))

        //------------------------------------------------------------------------------------------------------
        // Meet People Title
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Column {

                    Text(
                        text = "Meet People", style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = "with the same interests", style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 14.sp
                        )
                    )
                }

                //
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                ) {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            Icons.Rounded.KeyboardArrowRight, "Edit",
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(15.dp))


        // Meet People
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {

            // Person 1
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .width(170.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .background(Color.LightGray),
            )
            {


                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.face),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            text = "Fred Feuerstein", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            textAlign = TextAlign.Center,
                            text = "Gaming, Sports, Gym, Cycling", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 15.sp
                            )
                        )


                    }
                }
            }

            // Person 2
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .width(170.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .background(Color.LightGray),
            )
            {


                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.face),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            text = "Fred Feuerstein", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            textAlign = TextAlign.Center,
                            text = "Gaming, Sports, Gym, Cycling", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 15.sp
                            )
                        )


                    }
                }
            }

            // Person 3
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 20.dp)
                    .width(170.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .background(Color.LightGray),
            )
            {


                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.face),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            text = "Fred Feuerstein", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            textAlign = TextAlign.Center,
                            text = "Gaming, Sports, Gym, Cycling", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 15.sp
                            )
                        )


                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        //------------------------------------------------------------------------------------------------------
        // Your Friends Liked Title
        Column {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Column {

                    Text(
                        text = "Your Friends Liked", style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        text = "events your friends added to their list", style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 14.sp
                        )
                    )
                }

                //
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                ) {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            Icons.Rounded.KeyboardArrowRight, "Edit",
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
            }
        }




        Spacer(modifier = Modifier.height(15.dp))


        // Liked friends events
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {

            // EVENTS 1
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .width(340.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .paint(
                        painterResource(id = R.drawable.waterfall),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.BottomCenter,
            )
            {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(320.dp)
                        .height(100.dp)
                        .padding(bottom = 10.dp),
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.black),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        alpha = 0.6F,
                        modifier = Modifier
                            .height(220.dp)
                            .width(330.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Column(modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 5.dp)
                    ) {

                        Text(
                            text = "Student Party", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 17.sp,
                                color = Color.White, fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "in St. Polten", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 11.sp, color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(3.dp))


                        Text(
                            text = "Get ready to unleash the ultimate student party experience!" +
                                    " Join us for a night of...", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 14.sp, color = Color.White
                            )
                        )


                    }
                }
            }



            // EVENTS 2
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 20.dp)
                    .width(340.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { }
                    .paint(
                        painterResource(id = R.drawable.waterfall),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.BottomCenter,
            )
            {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(320.dp)
                        .height(100.dp)
                        .padding(bottom = 10.dp),
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.black),
                        contentDescription = "group",
                        contentScale = ContentScale.FillWidth,
                        alpha = 0.6F,
                        modifier = Modifier
                            .height(220.dp)
                            .width(330.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )

                    Column(modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(vertical = 5.dp)
                    ) {

                        Text(
                            text = "Student Party", style = TextStyle(
                                fontFamily = FontFamily.Default, fontSize = 17.sp,
                                color = Color.White, fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "in St. Polten", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 11.sp, color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(3.dp))


                        Text(
                            text = "Get ready to unleash the ultimate student party experience!" +
                                    " Join us for a night of...", style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontSize = 14.sp, color = Color.White
                            )
                        )


                    }

                }


            }
        }


        Spacer(modifier = Modifier.height(25.dp))
    }
}

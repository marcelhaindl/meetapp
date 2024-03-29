package com.cc221005.meetapp.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.widgets.OnboardingPagination
import com.cc221005.meetapp.ui.theme.md_theme_dark_surfaceContainer
import com.cc221005.meetapp.ui.theme.md_theme_light_surfaceContainer

/**
 * # Onboarding Flow 1
 * Greeting screen
 */
@Composable
fun OnboardingFlow1() {
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.flow1),
                contentDescription = null,
                modifier = Modifier.size(260.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.welcome_to_meetapp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.where_moments_become_memories),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.onboarding_flow_1_text),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            OnboardingPagination(Screen.OnboardingFlow1)
            Spacer(modifier = Modifier.height(24.dp))
        }
}

/**
 * # Onboarding Flow 2
 * Greeting screen
 */
@Composable
fun OnboardingFlow2() {
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.flow2),
                contentDescription = null,
                modifier = Modifier.size(260.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.all_you_need),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.navigate_your_social_world_your_way),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.onboarding_flow_2_text),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            OnboardingPagination(Screen.OnboardingFlow2)
            Spacer(modifier = Modifier.height(24.dp))
        }

}

/**
 * # Onboarding Flow 3
 * The Onboarding FLow 3 Screen is used to sign up the user with email and password. That's why it contains a headline, a subtext
 * and two text fields. Also it contains a button to switch to the login screen.
 *
 * @param navController (NavController) Navigation Controller to navigate to other screens
 * @param userModel (UserModel) User Model to interact with user states
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingFlow3(navController: NavController, userModel: UserModel) {
    val userState = userModel.userState.collectAsState()
    var email by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue(userState.value.email)) }
    var password by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    var showPassword by remember { mutableStateOf(value = false) }

    if(email.text.isNotEmpty() && password.text.isNotEmpty()) {
        userModel.updateEmailAndPassword(email = email.text, password = password.text)
    }

    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.flow3),
                contentDescription = null,
                modifier = Modifier.size(260.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.register),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.create_an_account_to_persist_your_data),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(R.string.email_address)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.mail), contentDescription = null) },
                value = email,
                onValueChange = { newValue -> email = newValue },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(R.string.password)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock), contentDescription = null) },
                trailingIcon = {
                    IconButton(
                        onClick = { showPassword = !showPassword }
                    ) {
                        Icon(
                            painter = if(showPassword) painterResource(id = R.drawable.eye) else painterResource(id = R.drawable.eye_off),
                            contentDescription = null)
                    } },
                value = password,
                onValueChange = { newValue -> password = newValue },
                singleLine = true,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                supportingText = { Text(text = stringResource(R.string.at_least_6_characters)) }
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextButton(
                onClick = { navController.navigate(Screen.OnboardingFlow3Login.route) },
            ) {
                Text(text = stringResource(R.string.already_have_an_account), color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = stringResource(R.string.login_exclamation), color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.weight(1f))
            OnboardingPagination(Screen.OnboardingFlow3)
            Spacer(modifier = Modifier.height(24.dp))
        }
}

/**
 * # Onboarding Flow 3 Login
 * The Onboarding FLow 3 Login Screen is used to login the user with email and password. That's why it contains a headline, a subtext
 * and two text fields. Also it contains a button to switch to the sign up screen.
 *
 * @param navController (NavController) Navigation Controller to navigate to other screens
 * @param userModel (UserModel) User Model to interact with user states
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingFlow3Login(navController: NavController, userModel: UserModel) {
    val userState = userModel.userState.collectAsState()
    var email by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue(userState.value.email)) }
    var password by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    var showPassword by remember { mutableStateOf(value = false) }

    if(email.text.isNotEmpty() && password.text.isNotEmpty()) {
        userModel.updateEmailAndPassword(email = email.text, password = password.text)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.flow3),
            contentDescription = null,
            modifier = Modifier.size(260.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.onboarding_flow_3_login_subtext),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = stringResource(R.string.email_address)) },
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.mail), contentDescription = null) },
            value = email,
            onValueChange = { newValue -> email = newValue },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = stringResource(R.string.password)) },
            leadingIcon = { Icon(painter = painterResource(id = R.drawable.lock), contentDescription = null) },
            trailingIcon = {
                IconButton(
                    onClick = { showPassword = !showPassword }
                ) {
                    Icon(
                        painter = if(showPassword) painterResource(id = R.drawable.eye) else painterResource(id = R.drawable.eye_off),
                        contentDescription = null)
                } },
            value = password,
            onValueChange = { newValue -> password = newValue },
            singleLine = true,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            supportingText = { Text(text = stringResource(R.string.at_least_6_characters)) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(
            onClick = { navController.navigate(Screen.OnboardingFlow3.route) },
        ) {
            Text(text = stringResource(R.string.don_t_have_an_account), color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = stringResource(R.string.register_exclamation), color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.weight(1f))
        OnboardingPagination(Screen.OnboardingFlow3)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * # Onboarding Flow 4
 * The Onboarding FLow 4 Screen is used to set the users username and name and save it to the database. That's why it contains
 * a headline, a subtext and two text fields.
 *
 * @param userModel (UserModel) User Model to interact with user states
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingFlow4(userModel: UserModel) {
    var username by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var name by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    userModel.updateUsernameAndName(username = username.text, name = name.text)

    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.flow3),
                contentDescription = null,
                modifier = Modifier.size(260.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.let_s_get_started),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.onboarding_flow_4_subtext),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(R.string.username)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.person), contentDescription = null) },
                value = username,
                onValueChange = { newValue -> username = newValue },
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = stringResource(R.string.name)) },
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.person), contentDescription = null) },
                value = name,
                onValueChange = { newValue -> name = newValue },
                singleLine = true,
            )
            Spacer(modifier = Modifier.weight(1f))
            OnboardingPagination(Screen.OnboardingFlow4)
            Spacer(modifier = Modifier.height(24.dp))
        }
}

/**
 * # Onboarding Flow 5
 * The Onboarding FLow 5 Screen is used to select the users' interests using chips.
 *
 * @param userModel (UserModel) User Model to interact with user states
 */
@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun OnboardingFlow5(userModel: UserModel) {
    val userState = userModel.userState.collectAsState()
    // Initial interest list
    val interestList: List<String> = listOf(
        "Lifestyle",
        "Make-up",
        "Sports",
        "Music",
        "Travel",
        "Food",
        "Technology",
        "Art",
        "Fitness",
        "Reading",
        "Gaming",
        "Movies",
        "Fashion",
        "Photography",
        "Cooking",
        "Outdoor Activities",
        "Crafting",
        "Science",
        "Writing",
        "Health",
        "Dancing",
        "Pets",
        "Cars",
        "Gardening",
        "History",
        "Yoga"
    )
    Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                interestList.forEach { interest ->
                    FilterChip(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        selected = userState.value.onboardingInterestList.contains(interest.lowercase()),
                        enabled = true,
                        colors = ChipDefaults.filterChipColors(
                            backgroundColor = if(isSystemInDarkTheme()) md_theme_dark_surfaceContainer else md_theme_light_surfaceContainer,
                            selectedBackgroundColor = MaterialTheme.colorScheme.primary,
                        ),
                        onClick = {
                            if(userState.value.onboardingInterestList.contains(interest.lowercase())) userModel.removeInterestItem(interest)
                            else userModel.addInterestItem(interest.lowercase())
                        }) {
                        Text(
                            text = interest,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if(userState.value.onboardingInterestList.contains(interest.lowercase())) MaterialTheme.colorScheme.onPrimary
                                    else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.what_s_your_thing),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.onboarding_flow_5_subtext),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            OnboardingPagination(Screen.OnboardingFlow5)
            Spacer(modifier = Modifier.height(24.dp))
        }

}

/**
 * # Onboarding Flow 6
 * Used to tell the user that singing process is finished and the user can now enter the app.
 */
@Composable
fun OnboardingFlow6() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.flow6),
            contentDescription = null,
            modifier = Modifier.size(260.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.finished),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.setup_complete),
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.onboarding_flow_6_text),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        OnboardingPagination(Screen.OnboardingFlow6)
        Spacer(modifier = Modifier.height(24.dp))
    }

}
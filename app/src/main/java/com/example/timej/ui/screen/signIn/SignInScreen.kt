package com.example.timej.ui.screen.signIn

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.timej.R
import com.example.timej.data.repository.JwtRepository
import com.example.timej.data_classes.Status
import com.example.timej.ui.theme.*
import com.example.timej.view.CustomTextField
import kotlinx.coroutines.delay

@Composable
fun SignInScreen(
    navController: NavHostController,
    onSuccessSignIn: () -> Unit,
    onNoAccountClick: () -> Unit,
    signInViewModel: SignInViewModel,
    context: Context,
    jwtRepository: JwtRepository,

    ) {

    val signInScreenState by remember { signInViewModel.signInScreenState }
    val email by remember { signInViewModel.emailState }
    val password by remember { signInViewModel.passwordState }
    val emptinessFields by remember { signInViewModel.emptinessFieldsState }
    val rememberMe by remember { signInViewModel.rememberMeState }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(37.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 135.dp)
        ) {
            // logo
            Box(contentAlignment = Alignment.TopCenter) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(107.dp, 68.dp)

                )
            }

            // welcome
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.welcome),
                    style = welcome,
                    color = Shark
                )
                Text(
                    text = stringResource(id = R.string.sign_in),
                    style = sign,
                    color = Shark
                )
            }

            Column(
                modifier = Modifier
                    .size(300.dp, 251.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(23.dp),
                ) {
                    CustomTextField(
                        value = email,
                        onValueChange = { signInViewModel.onEmailChange(it) },
                        placeholderText = stringResource(id = R.string.email),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.email),
                                contentDescription = null,
                                tint = Shark,
                                modifier = Modifier
                                    .size(21.dp, 21.dp)
                            )
                        }
                    )

                    CustomTextField(
                        value = password,
                        onValueChange = { signInViewModel.onPasswordChange(it) },
                        placeholderText = stringResource(id = R.string.password),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.lock),
                                contentDescription = null,
                                tint = Shark,
                                modifier = Modifier
                                    .size(19.dp, 19.dp)
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                        .clickable { signInViewModel.onRememberMeChange() }
                        .padding(start = 4.dp)
                ) {
                    CircleCheckBox(
                        isChecked = rememberMe,
                        checkedBackgroundColor = PaleCornflowerBlue,
                        unCheckedBackgroundColor = White,
                        unCheckedBorderColor = BonJour,
                        checkedIconColor = White,
                        unCheckedIconColor = Color.Transparent
                    )
                    Text(
                        text = stringResource(id = R.string.remember_me),
                        style = Placeholder,
                        color = if (rememberMe) Shark else Bombay
                    )
                }

                Text(
                    text = stringResource(id = R.string.no_account),
                    style = Underline,
                    color = Shark,
                    modifier = Modifier
                        .clickable(onClick = onNoAccountClick)
                        .align(Alignment.End)
                )

                Spacer(modifier = Modifier.height(23.dp))

                androidx.compose.animation.AnimatedVisibility(
                    visible = signInScreenState.status == Status.ERROR,
                    enter = expandVertically(
                        expandFrom = Alignment.Bottom
                    ) + fadeIn(
                        initialAlpha = 0.3f
                    ),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 32.dp)
                    ) {
                        Text(
                            text = if (signInScreenState.status == Status.ERROR && signInScreenState.error != null) {
                                stringResource(id = signInScreenState.error!!)
                            } else {
                                ""
                            },
                            color = Nero,
                            style = Choice,
                            modifier = Modifier
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Button(
                    onClick = signInViewModel::onClickLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = !emptinessFields,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PaleCornflowerBlue,
                        contentColor = Black,
                        disabledContainerColor = PaleCornflowerBlue.copy(alpha = 0.65F),
                        disabledContentColor = Black.copy(alpha = 0.65F)
                    ),
                    shape = RoundedCornerShape(10.dp),

                    ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        style = Placeholder,
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = signInScreenState.status) {
        if (signInScreenState.status == Status.SUCCESS) {
            signInScreenState.data?.let { jwtRepository.saveAccessToken(context, it.accessToken) }
            signInScreenState.data?.let { jwtRepository.saveRefreshToken(context, it.refreshToken) }
            delay(1700)
            navController.navigate("launch")
            //onSuccessSignIn.invoke()
        }
    }
}

@Composable
fun CircleCheckBox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    checkedBackgroundColor: Color,
    unCheckedBackgroundColor: Color,
    unCheckedBorderColor: Color,
    checkedIconColor: Color,
    unCheckedIconColor: Color
) {
    Box(
        modifier = modifier
            .size(15.dp, 15.dp)
            .clip(CircleShape)
            .border(
                width = if (!isChecked) 1.dp else 0.dp,
                color = if (!isChecked) unCheckedBorderColor else checkedBackgroundColor,
                shape = CircleShape
            )
            .background(
                color = if (isChecked) checkedBackgroundColor else unCheckedBackgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.checked),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 3.49.dp, vertical = 4.49.dp),
            tint = if (isChecked) checkedIconColor else unCheckedIconColor
        )
    }
}
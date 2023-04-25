package com.mmj.validation.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mmj.validation.R
import com.mmj.validation.presentation.component.CustomTextFieldApp
import com.mmj.validation.ui.theme.ValidationTheme
import com.mmj.validation.ui.theme.colorSilver
import com.mmj.validation.ui.theme.colorWhite

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValidationTheme {
                LoginScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: MainViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = stringResource(id = R.string.app_name),
                color = colorWhite,
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 50.dp)
                    .padding(horizontal = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 16.dp)
                    .align(Alignment.Center)
            ) {
                CustomTextFieldApp(
                    placeholder = "Email",
                    onValueChange = {
                        viewModel.onEvent(MainEvent.EmailChanged(it))
                    },
                    text = viewModel.formState.email,
                    padding = 16.dp,
                    isEmail = true,
                    isDone = false,
                    singleLine = true,
                    isError = viewModel.formState.emailError != null,
                    errorMessage = viewModel.formState.emailError,
                )

                Spacer(modifier = Modifier.padding(8.dp))

                CustomTextFieldApp(
                    placeholder = "Password",
                    onValueChange = {
                        viewModel.onEvent(MainEvent.PasswordChanged(it))
                    },
                    text = viewModel.formState.password,
                    trailingIcon = {
                        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                            IconButton(
                                onClick =
                                {
                                    viewModel.onEvent(MainEvent.VisiblePassword(!(viewModel.formState.isVisiblePassword)))
                                }
                            ) {
                                Icon(
                                    painter = if (viewModel.formState.isVisiblePassword) painterResource(
                                        id = R.drawable.ic_visible
                                    ) else painterResource(
                                        id = R.drawable.ic_invisible
                                    ),
                                    contentDescription = "Visible",
                                    tint = colorSilver,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    },
                    isVisible = viewModel.formState.isVisiblePassword,
                    padding = 16.dp,
                    isPassword = true,
                    isDone = true,
                    singleLine = true,
                    isError = viewModel.formState.passwordError != null,
                    errorMessage = viewModel.formState.passwordError
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Button(
                    onClick = {
                        viewModel.onEvent(MainEvent.Submit)
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = "Login")
                }

            }
        }
    }
}
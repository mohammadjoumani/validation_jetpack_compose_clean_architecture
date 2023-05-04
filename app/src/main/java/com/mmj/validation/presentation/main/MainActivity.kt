package com.mmj.validation.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mmj.validation.R
import com.mmj.validation.presentation.component.CustomTextFieldApp
import com.mmj.validation.presentation.ui.theme.ValidationTheme
import com.mmj.validation.presentation.ui.theme.colorSilver
import com.mmj.validation.presentation.ui.theme.colorWhite

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_welcome),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
            )

            Text(
                text = stringResource(id = R.string.strAppName),
                color = colorWhite,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 50.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.strEmail),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 8.dp)
                )
                CustomTextFieldApp(
                    placeholder = stringResource(id = R.string.strEmail),
                    text = viewModel.formState.email,
                    onValueChange = {
                        viewModel.onEvent(MainEvent.EmailChanged(it))
                    },
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    singleLine = true,
                    isError = viewModel.formState.emailError != null,
                    errorMessage = viewModel.formState.emailError,
                )

                Text(
                    text = stringResource(id = R.string.strPassword),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp)
                )
                CustomTextFieldApp(
                    placeholder = stringResource(id = R.string.strPassword),
                    text = viewModel.formState.password,
                    onValueChange = {
                        viewModel.onEvent(MainEvent.PasswordChanged(it))
                    },
                    keyboardType = KeyboardType.Password,
                    ImeAction.Done,
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
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    singleLine = true,
                    isError = viewModel.formState.passwordError != null,
                    errorMessage = viewModel.formState.passwordError
                )

                Spacer(modifier = Modifier.padding(4.dp))

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
                    Text(text = stringResource(id = R.string.strLogin))
                }

            }

            Spacer(modifier = Modifier.weight(1.0f))
            TextButton(
                onClick = { },
            ) {
                Text(
                    text = stringResource(id = R.string.strDontHaveAccount),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

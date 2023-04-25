package com.mmj.validation.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mmj.validation.core.functions.isNumber
import com.mmj.validation.core.generic.UiText
import com.mmj.validation.ui.theme.colorRed
import com.mmj.validation.ui.theme.colorSilver


@Composable
fun CustomTextFieldApp(
    placeholder: String,
    onValueChange: (String) -> Unit = {},
    padding: Dp = 16.dp,
    errorMessage: UiText? = null,
    text: String = "",
    isError: Boolean = false,
    isPassword: Boolean = false,
    isEmail: Boolean = false,
    isText: Boolean = false,
    isPhone: Boolean = false,
    isNumber: Boolean = false,
    isDone: Boolean = false,
    isVisible: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    maxLine: Int = 1,
//    height: Dp = AppSize.size50,
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequester = remember {
        FocusRequester()
    }

    Column {
        BasicTextField(
            value = if (isPhone || isNumber) {
                if (isNumber(text)) text else ""
            } else text,
            onValueChange = {
                if (isPhone || isNumber) {
                    if (isNumber(it)) onValueChange(it)
                } else onValueChange(it)
            },
            textStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface),
            maxLines = maxLine,
            singleLine = singleLine,
            interactionSource = interactionSource,
            visualTransformation =
            if (isPassword) {
                if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(
                keyboardType =
                if (isPassword) KeyboardType.Password
                else if (isEmail) KeyboardType.Email
                else if (isText) KeyboardType.Text
                else if (isPhone) KeyboardType.Phone
                else if (isNumber) KeyboardType.Number
                else KeyboardType.Text,
                imeAction = if (isDone) ImeAction.Done else ImeAction.Next
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                Row(
                    modifier =
                    if (isFocused) {
                        Modifier
                            .padding(horizontal = padding)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
//                            .height(50.dp)
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                    } else if (isError) {
                        Modifier
                            .padding(horizontal = padding)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.error
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
//                            .height(height)
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                    } else {
                        Modifier
                            .padding(horizontal = padding)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                            )
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
//                            .height(height)
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                    },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) {
                        leadingIcon()
                    } else {
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                    Box(modifier = Modifier.weight(1.0f).padding(vertical = 16.dp)) {
                        if (text.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = MaterialTheme.typography.bodySmall,
                                color = colorSilver,
                            )
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            innerTextField()
                        }
                    }
                    if (trailingIcon != null) {
                        trailingIcon()
                    } else {
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            },
        )
        Text(
            text = if (isError) errorMessage!!.asString(context) else "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding)
        )
    }
}
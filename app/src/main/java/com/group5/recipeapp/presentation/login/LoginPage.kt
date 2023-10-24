package com.group5.recipeapp.presentation.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.group5.recipeapp.R
import com.group5.recipeapp.presentation.components.RoundedButton
import com.group5.recipeapp.presentation.components.TransparentTextField
import com.group5.recipeapp.ui.theme.Black
import com.group5.recipeapp.ui.theme.Blue
import com.group5.recipeapp.ui.theme.Red
import com.group5.recipeapp.ui.theme.Typography
import com.radusalagean.infobarcompose.InfoBar
import com.radusalagean.infobarcompose.InfoBarMessage

@Composable
fun LoginPage(
    navController: NavHostController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val configuration = LocalConfiguration.current
    val heightInDp = configuration.screenHeightDp.dp

    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    var snackbarMessage: InfoBarMessage? by remember { mutableStateOf(null) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)

        try {
            val account = task.getResult(ApiException::class.java)
            val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signInWithGoogle(
                credentials = credentials,
                home = {
                    navController.navigate("categories") {
                        popUpTo(0)
                    }
                },
                onError = { message ->
                    snackbarMessage = InfoBarMessage(text = message)
                }
            )
        } catch (e: Exception) {
            snackbarMessage = InfoBarMessage(text = "Login failed")
        }
    }

    fun login() = run {
        viewModel.signInWithEmailAndPassword(
            emailValue.value,
            passwordValue.value,
            home = {
                navController.navigate("categories") {
                    popUpTo(0)
                }
            },
            onError = { message ->
                snackbarMessage = InfoBarMessage(text = message)
            })
    }

    fun loginWithGoogle() = run {
        val options = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestIdToken("268075207641-9i2n01af59k89cp2h1tm22n6ukpo77pu.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val signInClient = GoogleSignIn.getClient(context, options)
        launcher.launch(signInClient.signInIntent)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.food2_bg),
            contentDescription = "Login Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(heightInDp - 370.dp)
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ConstraintLayout {
                val (surface, fab) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(300.dp)
                                .padding(top = 16.dp)
                        )
                        Text(
                            text = "Login to your Account",
                            style = Typography.titleSmall,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        TransparentTextField(
                            modifier = Modifier.padding(26.dp, 0.dp),
                            textFieldValue = emailValue,
                            textLabel = "Email",
                            keyboardType = KeyboardType.Email,
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),
                            imeAction = ImeAction.Next
                        )
                        TransparentTextField(
                            modifier = Modifier.padding(26.dp, 0.dp),
                            textFieldValue = passwordValue,
                            textLabel = "Password",
                            keyboardType = KeyboardType.Password,
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    login()
                                }
                            ),
                            imeAction = ImeAction.Done,
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        passwordVisibility = !passwordVisibility
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (passwordVisibility) {
                                            Icons.Filled.Visibility
                                        } else {
                                            Icons.Filled.VisibilityOff
                                        },
                                        contentDescription = "Toggle Password Icon"
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisibility) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            }
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                Modifier
                                    .padding(top = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                RoundedButton(
                                    text = "Login",
                                    displayProgressBar = false,
                                    onClick = {
                                        login()
                                    }
                                )
                                IconButton(
                                    onClick = {
                                        loginWithGoogle()
                                    },
                                    Modifier
                                        .background(color = Blue, shape = CircleShape)
                                        .size(50.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.google_icon),
                                        contentDescription = "Google",
                                        tint = Color.White,
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                            ClickableText(
                                onClick = {
                                    navController.navigate("register")
                                },
                                text = buildAnnotatedString {
                                    append("Do not have an Account?")

                                    withStyle(
                                        style = SpanStyle(
                                            color = Blue,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append(" Sign up")
                                    }
                                },
                            )
                        }
                    }
                }
                FloatingActionButton(
                    onClick = {
                        login()
                    },
                    modifier = Modifier
                        .size(75.dp)
                        .constrainAs(fab) {
                            top.linkTo(surface.top, margin = (-36).dp)
                            end.linkTo(surface.end, margin = 36.dp)
                        },
                    containerColor = Black,
                    shape = CircleShape
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Forward Icon",
                        tint = Color.White
                    )
                }
            }
        }
        InfoBar(offeredMessage = snackbarMessage, shape = RectangleShape, backgroundColor = Red) {
            snackbarMessage = null
        }
    }
}
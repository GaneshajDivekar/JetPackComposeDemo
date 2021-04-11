package compose.demo

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.core.ContextAmbient
import androidx.ui.core.currentTextStyle
import androidx.ui.layout.constraintlayout.ConstraintLayout
import androidx.ui.layout.constraintlayout.ConstraintSet
import compose.demo.ui.theme.ComposeDemoMVVMTheme

var strUserName: String = ""
var strPassword: String = ""

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoMVVMTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldUserName()
                TextFieldPassword()
                LoginButton()

            }
        }
    }

}


@Composable
fun LoginButton() {
    val context = LocalContext.current
    Button(
        onClick = {
            if (strUserName.equals("")) {
                Toast.makeText(context, "Please enter username", Toast.LENGTH_LONG).show()
            } else if (strPassword.equals("")) {
                Toast.makeText(context, "Please enter password", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    context,
                    "Username$strUserName,Password$strPassword",
                    Toast.LENGTH_LONG
                ).show()
            }
        },
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Red)
    ) {
        Text(
            "Login",
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun TextFieldPassword() {
    val password = remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = password.value,
        onValueChange = {
            password.value = it
            strPassword = password.value.text.toString().trim()
        },
        label = { Text("Please enter password") },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun TextFieldUserName() {
    val userName = remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = userName.value,
        onValueChange = {
            userName.value = it
            strUserName = userName.value.text.toString().trim()
        },
        label = { Text("Please enter user name") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoMVVMTheme {
        Greeting()
    }
}
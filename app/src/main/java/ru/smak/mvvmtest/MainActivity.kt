package ru.smak.mvvmtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.smak.mvvmtest.ui.theme.MVVMTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTestTheme {
                // A surface container using the 'background' color from the theme
                val mvm = viewModel(MainViewModel::class.java)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        Text(text = stringResource(id = R.string.enter_time_label))
                        Input(mvm.hours.value.toString(), Modifier.fillMaxWidth())
                        Input(mvm.minutes.value.toString(), Modifier.fillMaxWidth())
                        ElevatedButton(onClick = {}) {
                            Text(text = stringResource(id = R.string.calc))
                        }
                        ru.smak.mvvmtest.Result(value = mvm.angel.value)
                    }
                }
            }
        }
    }
}

@Composable
fun Input(
    value: String,
    modifier: Modifier = Modifier,
){
    OutlinedTextField(
        value = value,
        onValueChange = { },
        modifier = modifier,
    )
}

@Composable
fun Result(
    value: Double,
    modifier: Modifier = Modifier,
){
    Text(
        text = stringResource(id = R.string.result_value, value),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun InputPreview() {
    MVVMTestTheme {
        Input("12")
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    MVVMTestTheme {
        Result(0.0)
    }
}
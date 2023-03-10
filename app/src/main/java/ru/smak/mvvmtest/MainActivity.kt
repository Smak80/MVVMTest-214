package ru.smak.mvvmtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                        Text(
                            modifier = Modifier.padding(8.dp, 12.dp),
                            text = stringResource(id = R.string.enter_time_label)
                        )
                        Input(
                            mvm.hours,
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp, 12.dp),
                            maxValue = 23U)
                        Input(
                            mvm.minutes,
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp, 12.dp)
                        )
                        ElevatedButton(onClick = {
                                mvm.calc()
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = stringResource(id = R.string.calc))
                        }
                        Result(
                            value = mvm.angel.value,
                            modifier = Modifier.padding(8.dp, 12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Input(
    value: MutableState<UInt>,
    modifier: Modifier = Modifier,
    minValue: UInt = 0U,
    maxValue: UInt = 59U,
){
    OutlinedTextField(
        value = value.value.toString(),
        onValueChange = { value.value = (it.toUIntOrNull() ?: 0U).coerceIn(minValue..maxValue) },
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

//@Preview(showBackground = true)
//@Composable
//fun InputPreview() {
//    MVVMTestTheme {
//        Input("12")
//    }
//}

@Preview(showBackground = true)
@Composable
fun ResultPreview() {
    MVVMTestTheme {
        Result(0.0)
    }
}
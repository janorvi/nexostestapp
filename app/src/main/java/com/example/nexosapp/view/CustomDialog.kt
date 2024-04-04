package com.example.nexosapp.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nexosapp.ui.theme.orange
import com.example.nexosapp.ui.theme.white
import com.example.nexosapp.viewmodel.MainViewModel

@Composable
fun CustomDialog(
    mainViewModel: MainViewModel
){
    val commerceCode: String by mainViewModel.commerceCode.observeAsState(initial = "000123")
    val terminalCode: String by mainViewModel.terminalCode.observeAsState(initial = "000ABC")
    val amount: String by mainViewModel.amount.observeAsState(initial = "12345")
    val cardNumber: String by mainViewModel.cardNumber.observeAsState(initial = "1234567890123456")
    val message: String by mainViewModel.message.observeAsState(initial = "")

    Dialog(
        onDismissRequest = {
            mainViewModel.onDismissDialog()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(0.95f).border(1.dp, color = orange,
                shape = RoundedCornerShape(15.dp)),
        ){
            Column(
                modifier = Modifier.fillMaxWidth().padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Text(
                    text = "Please, insert the new authorizantion information.",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                commerceCodeTextField(commerceCode = commerceCode) {
                    mainViewModel.onAuthorizationChanged(
                        it,
                        terminalCode,
                        amount,
                        cardNumber
                    )
                }
                terminalCodeTextField(terminalCode = terminalCode) {
                    mainViewModel.onAuthorizationChanged(
                        commerceCode,
                        it,
                        amount,
                        cardNumber
                    )
                }
                amountTextField(amount = amount) {
                    mainViewModel.onAuthorizationChanged(
                        commerceCode,
                        terminalCode,
                        it,
                        cardNumber
                    )
                }
                cardNumberTextField(cardNumber =  cardNumber) {
                    mainViewModel.onAuthorizationChanged(
                        commerceCode,
                        terminalCode,
                        amount,
                        it
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(
                        onClick = {
                            mainViewModel.onDismissDialog()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = orange,
                            contentColor = white
                        ),
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Button(
                        onClick = {
                            mainViewModel.sendAuthorization()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = orange,
                            contentColor = white
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Text(
                    text = message,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun commerceCodeTextField(commerceCode: String, onAuthorizationChanged:(String) -> Unit){
    OutlinedTextField(
        value = commerceCode,
        onValueChange = {
            onAuthorizationChanged(it)
        },
        label = {
            Text(
                text = "Commerce Code"
            )
        }
    )
}
@Composable
fun terminalCodeTextField(terminalCode: String, onAuthorizationChanged:(String) -> Unit){
    OutlinedTextField(
        value = terminalCode,
        onValueChange = {
            onAuthorizationChanged(it)
        },
        label = {
            Text(
                text = "Commerce Terminal"
            )
        }
    )
}
@Composable
fun amountTextField(amount: String, onAuthorizationChanged:(String) -> Unit){
    OutlinedTextField(
        value = amount,
        onValueChange = {
            onAuthorizationChanged(it)
        },
        label = {
            Text(
                text = "Amount"
            )
        }
    )
}
@Composable
fun cardNumberTextField(cardNumber: String, onAuthorizationChanged:(String) -> Unit){
    OutlinedTextField(
        value = cardNumber,
        onValueChange = {
            onAuthorizationChanged(it)
        },
        label = {
            Text(
                text = "Card Number"
            )
        }
    )
}
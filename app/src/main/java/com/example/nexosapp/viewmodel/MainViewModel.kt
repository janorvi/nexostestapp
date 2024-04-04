package com.example.nexosapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexosapp.database.DatabaseRepository
import com.example.nexosapp.model.AnnulmentRequest
import com.example.nexosapp.model.Authorization
import com.example.nexosapp.model.AuthorizationRequest
import com.example.nexosapp.service.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(serviceRepository: ServiceRepository, databaseRepository: DatabaseRepository): ViewModel(){

    private var serviceRepository: ServiceRepository? = null
    private var databaseRepository: DatabaseRepository? = null

    private val _commerceCode = MutableLiveData<String>("000123")
    val commerceCode: LiveData<String> get() = _commerceCode
    private val _terminalCode = MutableLiveData<String>("000ABC")
    val terminalCode: LiveData<String> get() = _terminalCode
    private val _amount = MutableLiveData<String>("12345")
    val amount: LiveData<String> get() = _amount
    private val _cardNumber = MutableLiveData<String>("1234567890123456")
    val cardNumber: LiveData<String> get() = _cardNumber
    private val _message = MutableLiveData<String>("")
    val message: LiveData<String> get() = _message


    private val _authorizationId = MutableLiveData<String>()
    val authorizationId: LiveData<String> get() = _authorizationId


    private var _authorizationsList = MutableLiveData<List<Authorization>>()
    val authorizationList: LiveData<List<Authorization>> get() = _authorizationsList


    var isDialogShown by mutableStateOf(false)
    private set

    init{
        this.serviceRepository = serviceRepository
        this.databaseRepository = databaseRepository
    }

    fun onAuthorizationChanged(commerceCode: String, terminalCode: String, amount: String, cardNumber: String){
        _commerceCode.value = commerceCode
        _terminalCode.value = terminalCode
        _amount.value = amount
        _cardNumber.value = cardNumber
    }

    fun onAuthorizationIDChanged(authorizationId: String){
        _authorizationId.value = authorizationId
    }

    fun onBuyClick(){
        isDialogShown = true
    }

    fun onDismissDialog(){
        isDialogShown = false
    }

    fun getAuthorizationsByStatus(status: String){
        viewModelScope.launch {
            try{
                val authorizationsList = databaseRepository?.getAuthorizationsByStatus(status)
                authorizationsList?.let {
                    _authorizationsList.postValue(it)
                }
            }catch (e: Exception){
                Log.i("getAllAuthorizationsByStateError", e.message.toString())
            }
        }
    }

    fun getAuthorizationsByNumber(){
        viewModelScope.launch {
            try{
                val authorizationsList = databaseRepository?.getAuthorizationsByNumber(authorizationId.value!!)
                authorizationsList?.let {
                    _authorizationsList.postValue(it)
                }
            }catch (e: Exception){
                Log.i("getAllAuthorizationsByStateError", e.message.toString())
            }
        }
    }

    fun sendAuthorization(){
        viewModelScope.launch {
            try{
                val authorizationRequest = AuthorizationRequest("1", commerceCode.value!!, terminalCode.value!!, amount.value!!, cardNumber.value!!)
                serviceRepository?.sendAuthorization(authorizationRequest)?.let{ authorizationResponse ->
                    if (authorizationResponse.statusCode == "00"){
                        val authorization = Authorization(
                            0,
                            commerceCode.value!!,
                            terminalCode.value!!,
                            amount.value!!,
                            cardNumber.value!!,
                            authorizationResponse.receiptId,
                            authorizationResponse.rnn,
                            authorizationResponse.statusCode,
                            authorizationResponse.statusDescription
                        )
                        databaseRepository?.insertAuthorization(authorization)?.let {
                            _message.value = ""
                            onDismissDialog()
                        }
                    }
                }
            }catch(e: Exception){
                Log.i("sendAuthorizationError", e.message.toString())
                _message.value = e.message.toString()
            }
        }
    }

    fun cancelAuthorization(annulmentRequest: AnnulmentRequest){
        viewModelScope.launch {
            try{
                serviceRepository?.cancelAuthorization(annulmentRequest)?.let{ annulmentResponse ->
                    if(annulmentResponse.statusCode == "00"){
                        databaseRepository?.updateAuthorizationStatusByReceiptId(annulmentRequest.receiptId, "Anulada")
                        delay(500)
                        databaseRepository?.getAuthorizationsByStatus("Aprobada")
                    }
                }
            }catch(e: Exception){
                Log.i("cancelAuthorizationError", e.message.toString())
            }
        }
    }
}

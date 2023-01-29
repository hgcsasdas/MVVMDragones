package com.example.primeraconexionfirebase.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ViewModel: ViewModel() {

    private val _nombre_dragon  = MutableLiveData<String>()
    val nombre_dragon : LiveData<String> = _nombre_dragon

    private val _raza_dragon  = MutableLiveData<String>()
    val raza_dragon : LiveData<String> = _raza_dragon

    private val _color_dragon  = MutableLiveData<String>()
    val color_dragon : LiveData<String> = _color_dragon

    private val _peso_dragon  = MutableLiveData<String>()
    val peso_dragon : LiveData<String> = _peso_dragon

    private val _genero_dragon  = MutableLiveData<String>()
    val genero_dragon : LiveData<String> = _genero_dragon

    private val _isButtonEnable =MutableLiveData<Boolean>()
    val isButtonEnable: LiveData<Boolean> = _isButtonEnable

    fun onCompletedFields(nombre_dragon:String, raza_dragon:String, color_dragon:String, peso_dragon:String, genero_dragon:String) {
        _nombre_dragon.value = nombre_dragon
        _raza_dragon.value = raza_dragon
        _color_dragon.value = color_dragon
        _peso_dragon.value = peso_dragon
        _genero_dragon.value = genero_dragon
        _isButtonEnable.value = enableButton(nombre_dragon, raza_dragon, color_dragon, peso_dragon, genero_dragon)
    }

    fun onCompleteNombre(nombre_dragon:String){
        _nombre_dragon.value = nombre_dragon
    }

    fun limpiarCampos() {
        _nombre_dragon.value = ""
        _raza_dragon.value = ""
        _color_dragon.value = ""
        _peso_dragon.value = ""
        _genero_dragon.value = ""
    }

    fun enableButton(nombre_dragon:String, raza_dragon:String, color_dragon:String, peso_dragon:String, genero_dragon:String) =
        //Patterns.EMAIL_ADDRESS.matcher(mail).matches()
        nombre_dragon.length >0 && raza_dragon.length >0 && color_dragon.length >0 && genero_dragon.length >0
}
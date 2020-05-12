package com.example.thesisapp.Recycling

interface IFirebaseLoadDone {
    fun onFirebaseLoadSuccess(wasteList:List<Waste>)
    fun onFirebaseLoadFailed(message:String)
}
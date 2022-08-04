package com.example.testmovi.util

import android.location.Location
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject


class RxBus {
    companion object {
        val locationPermissionObservable: Subject<Any> = PublishSubject.create()

        val liveDataLocation: MutableLiveData<Location> = MutableLiveData()
    }
}
package com.example.testmovi.presentation.base

import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    var compositeSubscription: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription.clear()
    }
}
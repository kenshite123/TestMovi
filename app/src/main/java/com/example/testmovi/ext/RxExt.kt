package com.example.testmovi.ext

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


fun <T : Any> Observable<T>.onDefault(): Observable<T> =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

inline fun <reified T : Any> T.delay(crossinline block: () -> Unit): Disposable {
    return delay(500, block)
}

inline fun <reified T : Any> T.delay(
    durationInMillis: Long = 500,
    crossinline block: () -> Unit
): Disposable {
    return Observable.interval(durationInMillis, TimeUnit.MILLISECONDS)
        .onDefault()
        .subscribe { block() }
}

fun <T> Observable<T>.onComputationDefault(): Observable<T> =
    this.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.delayEach(interval: Long, timeUnit: TimeUnit): Observable<T> =
    Observable.zip(
        this,
        Observable.interval(interval, timeUnit)
    ) { item, _ -> item }

fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable =
    apply { compositeDisposable.add(this) }
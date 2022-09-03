package ru.geekbrains.popularlibraries

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit


class Creation {


    fun exec() {
        Consumer(Producer()).exec()
    }

    inner class Producer {


        fun randomResultOperation(): Boolean {
            Thread.sleep(1000)
            return listOf(true, false, true)[Random().nextInt(2)]
        }

        fun just(): Observable<String> {
            return Observable.just("1", "2", "3", "4")
        }

        fun fromIterable(): Observable<String> {
            return Observable.fromIterable(listOf("1", "2", "3"))
        }

        //Timer выдает один раз
        fun interval() = Observable.interval(1, TimeUnit.SECONDS)
        fun range() = Observable.range(1, 10)

        fun fromCallable() = Observable.fromCallable {
            val result = randomResultOperation()
            return@fromCallable result
        }

        fun create() = Observable.create<String> { emitter ->
            try {
                for (i in 0..10) {
                    randomResultOperation().let {
                        if (it) {
                            emitter.onNext("Success$i")
                        } else {
                            emitter.onError(RuntimeException("Error"))
                            return@create
                        }
                    }
                }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(RuntimeException("Error"))
            }
        }
    }

    inner class Consumer(private val producer: Producer) {

        private fun execLambda() {
            val disposable = producer.just().subscribe({ s ->
                println("onNext:$s")
            }, { e ->
                println("onError ${e.message}")

            }, { println("onComplete") })
        }

        private fun execJust() {
            producer.just().subscribe(stringObserver)
        }

        private fun execFromIterable() {
            producer.fromIterable().subscribe(stringObserver)
        }

        private fun execInterval() {
            producer.interval().subscribe({ s ->
                println("onNext:$s")
            }, { e ->
                println("onError ${e.message}")

            }, { println("onComplete") })
        }

        private fun execRange() = producer.range().subscribe({ s ->
            println("onNext:$s")
        }, { e ->
            println("onError ${e.message}")

        }, { println("onComplete") })

        private fun execFromCallable() {
            producer.fromCallable().subscribe({ s ->
                println("onNext:$s")
            }, { e ->
                println("onError ${e.message}")

            }, { println("onComplete") })
        }

        fun exec() {
            execJust()
            //execLambda()
            //execFromIterable()
            //execInterval()
            //execRange()
            //execFromCallable()
//            producer.create().subscribe({ s ->
//                println("onNext:$s")
//            }, { e ->
//                println("onError ${e.message}")
//
//            }, { println("onComplete") })
        }
    }

    val stringObserver = object : Observer<String> {
        var disposable: Disposable? = null
        override fun onSubscribe(d: Disposable) {
            disposable = d
            println("onSubscribe")
        }

        override fun onNext(t: String) {
            println("onNext:$t")
        }

        override fun onError(e: Throwable) {
            println("onError ${e.message}")
        }

        override fun onComplete() {
            println("onComplete")
        }
    }
}
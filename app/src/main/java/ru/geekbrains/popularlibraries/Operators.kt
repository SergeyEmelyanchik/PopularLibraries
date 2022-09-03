package ru.geekbrains.popularlibraries

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Operators {
    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun createJust() = Observable.just("1", "2", "3", "3")
        fun createJust2() = Observable.just("4", "5", "6")
        fun fromIterable(): Observable<Int> =
            Observable.fromIterable(listOf(2, 1, 3, 4, 5, 1, 23, 13, 3))

    }

    class Consumer(val producer: Producer) {

        fun exec() {
            //execTake()
            //execFlatMap()
            //execMap()
            execZip()
        }

        fun execZip() {
            val observable1 = Observable.just("1", "2", "3")
            val observable2 = Observable.just("Arnold", "Sylvester", "Bruce")
            Observable.zip(observable1, observable2) { num, name ->
                num + name
            }.subscribe({
                println(it)
            }, {}, {})
        }


        fun execFlatMap() {
            producer.createJust()
                .flatMap {
                    val delay = Random.nextInt(1000).toLong()
                    return@flatMap Observable.just(it + "x").delay(
                        delay,
                        TimeUnit.MILLISECONDS
                    )
                }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        private fun mapToListAdd(value: Int): MutableList<Int> {
            return mutableListOf(value, 11)
        }

        fun execMap() {
            producer.fromIterable().map(::mapToListAdd).map {
                it[0] + 1
            }.subscribe {
                println(it)
            }
        }


        fun execTake() {
            //flatMap возвращает observable
            producer.createJust()
                //.take(2)// первые 2 элемента
                //.skip(2)//
                .map { it + it + "2" }
                .distinct()
                .filter { it.toInt() > 200 }
                .mergeWith(producer.createJust2())//Может быть не последовательно
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}
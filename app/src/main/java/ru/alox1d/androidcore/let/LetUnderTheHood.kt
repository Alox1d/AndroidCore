package ru.alox1d.androidcore.let

fun main() {
    LetUnderTheHood().doSomeAwesomePrint()
    println()
    LetUnderTheHood().doSomeAwesomePrintSameResult()
    println()
    LetUnderTheHood().doSomeAwesomePrintSameSolution()
}

class LetUnderTheHood {
    var awesomeVar1: String? = "some string"
    var awesomeVar2: String? = null

    // must print nothing, but actually print...?
    fun doSomeAwesomePrint() {
        awesomeVar1?.let {
            awesomeVar2?.let {
                println("awesome print 1")
            } // this "let" returns null
        } ?: run {
            println("awesome print 2")
        }
    }

    fun doSomeAwesomePrintSameResult() {
        awesomeVar1?.let {
            val a = awesomeVar2?.let {
                println("awesome print 1")
            } // this "let" returns null
            println("awesomeVar2 = $awesomeVar2")
            a
        } ?: run {
            println("awesome print 2")
        }
    }

    fun doSomeAwesomePrintSameSolution() {
        awesomeVar1?.apply {
            val a = awesomeVar2?.let {
                println("awesome print 1")
            } // this "let" returns null
            println("awesomeVar2 = $awesomeVar2")
            a
        } ?: run {
            println("awesome print 2")
        }
    }
}
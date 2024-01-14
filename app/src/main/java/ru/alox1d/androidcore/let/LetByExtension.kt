package ru.alox1d.androidcore.let

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <T, R> T.myLet(block: (T) -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block(this)
}

@OptIn(ExperimentalContracts::class)
inline fun <T, R> T.myRun(block: () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

// R.() - для замены this текущего скоупа
@OptIn(ExperimentalContracts::class)
inline fun <R> R.myApplyIf(condition: Boolean, block: R.() -> Unit): R {
    contract {
        callsInPlace(
            block,
            InvocationKind.AT_MOST_ONCE
        ) // will be invoked one time or not invoked at all.
    }

    if (condition) block()
    return this
}
@file:Suppress("unused")

package com.github.submob.logmob

import co.touchlab.kermit.Logger

fun Logger.e(throwable: Throwable) = e(throwable) { throwable.message.toString() }
fun Logger.w(throwable: Throwable) = w(throwable) { throwable.message.toString() }
fun Logger.a(throwable: Throwable) = a(throwable) { throwable.message.toString() }
fun Logger.d(throwable: Throwable) = d(throwable) { throwable.message.toString() }
fun Logger.v(throwable: Throwable) = v(throwable) { throwable.message.toString() }
fun Logger.i(throwable: Throwable) = i(throwable) { throwable.message.toString() }

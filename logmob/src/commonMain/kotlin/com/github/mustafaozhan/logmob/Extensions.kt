package com.github.mustafaozhan.logmob

import co.touchlab.kermit.Logger

fun Logger.e(throwable: Throwable) = e(throwable) { throwable.message.toString() }

fun Logger.w(throwable: Throwable) = w(throwable) { throwable.message.toString() }

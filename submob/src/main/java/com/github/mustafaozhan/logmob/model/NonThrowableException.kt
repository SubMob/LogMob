/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.logmob.model

class NonThrowableException(message: String) :
    Exception(message, Throwable(message))

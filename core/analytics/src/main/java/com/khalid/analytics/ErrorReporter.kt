package com.khalid.analytics

import javax.inject.Inject
import javax.inject.Singleton

//TODO: To an Interface to be able to swap between multiple reporters
@Singleton
class ErrorReporter @Inject constructor() {
    fun log(throwable: Throwable) {
        //TODO adding crash logging library
    }
}
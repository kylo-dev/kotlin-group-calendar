package com.gc.api.customer.framework.exception

import common.exception.CustomNotFoundException

fun <T: Any> T?.orNotFound(message: String): T =
    this ?: throw CustomNotFoundException(message)
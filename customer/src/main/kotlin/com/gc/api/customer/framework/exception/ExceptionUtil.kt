package com.gc.api.customer.framework.exception

import com.gc.common.exception.CustomException
import common.exception.CustomNotFoundException
import kotlinx.coroutines.TimeoutCancellationException
import java.io.IOException

fun <T: Any> T?.orNotFound(message: String): T =
    this ?: throw CustomNotFoundException(message)

fun toCoroutineException(e: Throwable): CustomException {
    return when(e) {
        is IllegalArgumentException -> CustomException(
            httpStatusCode = 400,
            code = "COMMON400",
            message = e.message!!,
        )

        is TimeoutCancellationException -> CustomException(
            httpStatusCode = 504,
            code = "COMMON504",
            message = "요청 시간이 초과되었습니다.",
        )

        is IOException -> CustomException(
            httpStatusCode = 502,
            code = "COMMON502",
            message = "외부 시스템과 통신에 실패했습니다.",
        )

        else -> CustomException(
            httpStatusCode = 500,
            code = "COMMON500",
            message = "서버 오류가 발생했습니다.",
        )
    }
}
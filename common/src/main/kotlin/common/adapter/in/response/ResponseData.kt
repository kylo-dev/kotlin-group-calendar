package common.adapter.`in`.response

data class ResponseData<T>(
  val code: String,
  val message: String? = null,
  val data: T? = null,
) {

  companion object {

    // SUCCESS METHOD
    fun <T> success(): ResponseData<T> =
      ResponseData("COMMON200")

    fun <T> success(data: T): ResponseData<T> =
      ResponseData("COMMON200", data = data)

    fun <T> success(message: String, data: T): ResponseData<T> =
      ResponseData("COMMON200", message, data)

    // FAIL METHOD
    fun <T> error(code: String, message: String): ResponseData<T> =
      ResponseData(code, message)
  }
}
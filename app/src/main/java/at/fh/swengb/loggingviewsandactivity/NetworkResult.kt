package at.fh.swengb.loggingviewsandactivity

    sealed class NetworkResult<R> {
        data class NetworkSuccess<T>(
                val value: T
        ): NetworkResult<T>()
        data class NetworkError<Nothing>(val errorMessage: String): NetworkResult<Nothing>()
    }
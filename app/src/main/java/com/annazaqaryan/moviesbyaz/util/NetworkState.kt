package com.annazaqaryan.moviesbyaz.util

class NetworkState(val status: Status,
                   val message: String) {

    companion object {
        val LOADING: NetworkState =
            NetworkState(
                Status.LOADING,
                "Loading..."
            )
        val FINISHED: NetworkState =
            NetworkState(
                Status.SUCCESS,
                "Success!"
            )
        val ERROR: NetworkState =
            NetworkState(
                Status.FAILED,
                "Error occurred"
            )
        val ENDOFLIST: NetworkState =
            NetworkState(
                Status.FAILED,
                "End of list"
            )
    }

    enum class Status {
        LOADING,
        SUCCESS,
        FAILED
    }
}
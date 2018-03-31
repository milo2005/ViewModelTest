package org.test.testutest.util

import io.reactivex.Single
import org.test.testutest.data.net.api.ResponseBody

fun<T> Single<ResponseBody<T>>.validateResponse():Single<T> = flatMap{
    if(it.success) Single.just(it.data)
    else throw Throwable(it.error)
}
package org.test.testutest.util

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

annotation class ExcludeField

class FieldExclusionStrategy : ExclusionStrategy {

    override fun shouldSkipClass(clazz: Class<*>?): Boolean = false

    override fun shouldSkipField(f: FieldAttributes): Boolean = f.getAnnotation(ExcludeField::class.java) != null

}
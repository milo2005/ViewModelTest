package org.test.testutest.util

import android.content.Context
import android.content.SharedPreferences
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when` as mWhen
import org.mockito.MockitoAnnotations
import org.test.testutest.data.db.AppDataBase

class ViewModelRule:TestRule{

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var preferences: SharedPreferences

    @Mock
    lateinit var editor: SharedPreferences.Editor

    @Mock
    lateinit var appDataBase: AppDataBase

    override fun apply(base: Statement, description: Description?): Statement {
        MockitoAnnotations.initMocks(this)
        return object:Statement(){
            override fun evaluate() {
                mWhen(context.getSharedPreferences(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt()))
                        .thenReturn(preferences)
                mWhen(preferences.edit()).thenReturn(editor)
                mWhen(editor.putString(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                        .thenReturn(editor)

                base.evaluate()
            }

        }



    }

}
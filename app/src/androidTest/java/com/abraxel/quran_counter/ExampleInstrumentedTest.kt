package com.abraxel.quran_counter

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @org.junit.Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext: android.content.Context =
            InstrumentationRegistry.getInstrumentation().getTargetContext()
        org.junit.Assert.assertEquals("com.abraxel.quran_counter", appContext.packageName)
    }
}
package com.dmc.sw.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class SwViewModel(application: Application, protected val interactors: Interactors): AndroidViewModel(application) {

    protected val application: SwApplication = getApplication()
}
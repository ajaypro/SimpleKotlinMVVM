package com.ajay.kotlinmvvm.view.callback

import android.view.View
import com.ajay.kotlinmvvm.service.model.Project



interface ProjectClickCallback {
    fun onClick(view: View, project: Project)
}
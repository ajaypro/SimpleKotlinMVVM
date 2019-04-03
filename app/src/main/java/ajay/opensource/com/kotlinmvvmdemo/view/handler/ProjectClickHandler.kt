package ajay.opensource.com.kotlinmvvmdemo.view.handler

import ajay.opensource.com.kotlinmvvmdemo.data.model.Project
import android.view.View



interface ProjectClickHandler {
    fun onClick(view: View, project: Project)
}
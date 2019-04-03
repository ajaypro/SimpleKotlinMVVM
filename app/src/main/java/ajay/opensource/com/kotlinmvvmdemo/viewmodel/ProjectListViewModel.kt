package ajay.opensource.com.kotlinmvvmdemo.viewmodel

import ajay.opensource.com.kotlinmvvmdemo.data.model.Project
import ajay.opensource.com.kotlinmvvmdemo.data.repository.ProjectRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class ProjectListViewModel(application: Application): AndroidViewModel(application) {
    private val projectListObservable= ProjectRepository.getInstance().getProjectList("Google")

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getProjectListObservable(): LiveData<List<Project>> {
        return projectListObservable
    }
}
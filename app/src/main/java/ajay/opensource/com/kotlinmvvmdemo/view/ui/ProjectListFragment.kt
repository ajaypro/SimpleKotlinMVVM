package ajay.opensource.com.kotlinmvvmdemo.view.ui

import ajay.opensource.com.kotlinmvvmdemo.R
import ajay.opensource.com.kotlinmvvmdemo.data.model.Project
import ajay.opensource.com.kotlinmvvmdemo.databinding.FragmentProjectListBinding
import ajay.opensource.com.kotlinmvvmdemo.view.adapter.ProjectAdapter
import ajay.opensource.com.kotlinmvvmdemo.view.handler.ProjectClickHandler
import ajay.opensource.com.kotlinmvvmdemo.viewmodel.ProjectListViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Ajay Deepak on 28-03-2019.
 */


class ProjectListFragment: androidx.fragment.app.Fragment() {
    private var projectAdapter: ProjectAdapter? = null
    private var binding: FragmentProjectListBinding? = null
    private var projectList= mutableListOf<Project>()
    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)

        projectAdapter = ProjectAdapter(projectList, projectClickCallback)
        binding?.projectList?.adapter = projectAdapter
        binding?.isLoading = true

        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        // Update the list when the data changes
        viewModel.getProjectListObservable().observe(this, Observer<List<Project>> { projects ->
            if (projects != null) {
                binding?.isLoading=false
                projectList.clear()
                projectList.addAll(projects.toMutableList())
                projectAdapter?.notifyDataSetChanged()
            }
        })
    }

    private val projectClickCallback = object : ProjectClickHandler {
        override fun onClick(view: View, project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }
    companion object {
        val TAG = "ProjectListFragment"
    }
}
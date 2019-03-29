package com.ajay.kotlinmvvm.view.ui

import android.support.v4.app.Fragment
import com.ajay.kotlinmvvm.view.adapter.ProjectAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.ajay.kotlinmvvm.R
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import com.ajay.kotlinmvvm.view.callback.ProjectClickCallback
import android.arch.lifecycle.ViewModelProviders
import com.ajay.kotlinmvvm.databinding.FragmentProjectListBinding
import com.ajay.kotlinmvvm.service.model.Project
import com.ajay.kotlinmvvm.viewmodel.ProjectListViewModel

class ProjectListFragment: Fragment() {
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

    private val projectClickCallback = object : ProjectClickCallback {
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
package ajay.opensource.com.kotlinmvvmdemo.view.adapter

import ajay.opensource.com.kotlinmvvmdemo.R
import ajay.opensource.com.kotlinmvvmdemo.data.model.Project
import ajay.opensource.com.kotlinmvvmdemo.databinding.ProjectListItemBinding
import ajay.opensource.com.kotlinmvvmdemo.view.handler.ProjectClickHandler

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Ajay Deepak on 28-03-2019.
 */


class ProjectAdapter(private val projectList: MutableList<Project>, private val projectClickHandler: ProjectClickHandler): androidx.recyclerview.widget.RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil.inflate<ajay.opensource.com.kotlinmvvmdemo.databinding.ProjectListItemBinding>(
            LayoutInflater.from(parent.context), R.layout.project_list_item,
            parent, false)

        binding.handler = projectClickHandler

        return ProjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return projectList!!.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project=projectList?.get(position)
        holder.binding.executePendingBindings()
    }

    class ProjectViewHolder(val binding: ProjectListItemBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)
}
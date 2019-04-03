package ajay.opensource.com.kotlinmvvmdemo.view.ui

import ajay.opensource.com.kotlinmvvmdemo.R
import ajay.opensource.com.kotlinmvvmdemo.data.model.Project
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    // Add project list fragment if this is first creation
    if (savedInstanceState == null) {
        val fragment = ProjectListFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, fragment, ProjectListFragment.TAG).commit()
    }
}

   fun show(project: Project) {
    val projectFragment = ProjectFragment.forProject(project.name)

    supportFragmentManager
        .beginTransaction()
        .addToBackStack("project")
        .replace(R.id.main_container,
            projectFragment, null).commit()
}
}

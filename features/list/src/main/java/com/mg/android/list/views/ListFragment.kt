package com.mg.android.list.views


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mg.android.common.base.BaseFragment
import com.mg.android.common.base.BaseViewModel
import com.mg.android.list.R
import com.mg.android.list.databinding.FragmentListBinding
import com.mg.android.list.viewmodel.ListViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class ListFragment : BaseFragment() {


    private val viewModel: ListViewModel by viewModel()
    private lateinit var binding: FragmentListBinding

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListBinding.inflate(inflater,container,false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        (activity as AppCompatActivity?)?.supportActionBar?.show()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.fragmentListRv.adapter = ListAdapter(viewModel)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.map) { viewModel.openMapFragment()
            true
        }  else super.onOptionsItemSelected(item)
    }
}

package com.rockstreamer.koinandroidexample

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rockstreamer.koinandroidexample.databinding.FragmentFirstBinding
import com.rockstreamer.koinandroidexample.ui.main.MainAdapter
import com.rockstreamer.koinandroidexample.ui.main.MainViewModel
import com.rockstreamer.koinandroidexample.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */


class FirstFragment : Fragment(), MainAdapter.OnClickCallback {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: MainAdapter

    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Loading ...")


        binding.recycleview.layoutManager = LinearLayoutManager(requireActivity())
        adapter = MainAdapter(this)
        binding.recycleview.adapter = adapter

        //mainViewModel.fetchMovie("e4c41ae3e8578a454aa7575f144a0f14" , 1)
        mainViewModel.movieListResponse.observe(viewLifecycleOwner){
            when(it.status){
                Status.LOADING ->{
                    progressDialog.show()
                }

                Status.SUCCESS -> {
                    progressDialog.dismiss()
                    adapter.addAll(it.data!!.results)
                }

                Status.ERROR ->{
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickItem() {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}


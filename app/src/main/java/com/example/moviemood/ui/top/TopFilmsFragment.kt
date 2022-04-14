package com.example.moviemood.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviemood.ui.top.adapter.KinopoiskDataSourceAdapter
import com.example.moviemood.databinding.FragmentTopFilmsBinding
import com.example.moviemood.model.Film
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TopFilmsFragment(private val countOfFragment: Int) : Fragment() {

    private lateinit var binding: FragmentTopFilmsBinding
    private lateinit var navController: NavController
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopFilmsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.topFragmentRecyclerview.layoutManager = linearLayoutManager
        navController = view.findNavController()
        getMyData()
    }

    private fun getMyData() {
        binding.topFragmentRecyclerview.adapter = KinopoiskDataSourceAdapter(requireContext()) { //здесь вписать обработку по клику на элемент ViewHolder
            Toast.makeText(requireContext(), "Film: " + it.nameRu, Toast.LENGTH_SHORT)
                .show()
        }
        val viewModel = TopViewModelFactory().create(TopViewModel::class.java)
        viewModel.setCountOfFragment(countOfFragment)
        lifecycleScope.launch {
            viewModel.result.collectLatest {
                setFilm(it)
            }
        }
    }

    private fun setFilm(list: PagingData<Film>) {
        (binding.topFragmentRecyclerview.adapter as KinopoiskDataSourceAdapter).submitData(
            lifecycle,
            list
        )
    }
}
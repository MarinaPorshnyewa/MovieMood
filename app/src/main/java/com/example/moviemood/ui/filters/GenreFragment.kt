package com.example.moviemood.ui.filters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviemood.databinding.FragmentGenreBinding
import com.example.moviemood.model.GenreFilters
import com.example.moviemood.ui.MyApplication.Companion.appComponent
import com.example.moviemood.ui.filters.adapter.GenreAdapter
import javax.inject.Inject

class GenreFragment : Fragment() {

    private lateinit var binding: FragmentGenreBinding


    private lateinit var viewModel: FiltersViewModel
    private lateinit var navController: NavController

    @Inject
    lateinit var filtersViewModelFactory: FiltersViewModelFactory

    private val arguments: GenreFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenreBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        appComponent.injectGenreFragment(this)
        viewModel = filtersViewModelFactory.createViewModel()
        binding.recyclerView.adapter = GenreAdapter(requireContext()) {
            navController.navigate(
                GenreFragmentDirections.actionGenreFragmentToFiltersFragment(
                    it.genre,
                    it.id,
                    arguments.country,
                    arguments.countryId.toInt()
                )
            )
        }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        viewModel.listFilters.observe(viewLifecycleOwner) {
            it?.genres?.let { it1 -> setList(it1) }
        }
        viewModel.getFilters()
    }

    private fun setList(list: List<GenreFilters>) {
        (binding.recyclerView.adapter as? GenreAdapter)?.setList(list)
    }
}
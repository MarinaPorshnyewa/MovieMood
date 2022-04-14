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
import com.example.moviemood.databinding.FragmentCountryBinding
import com.example.moviemood.model.CountryFilters
import com.example.moviemood.ui.MyApplication.Companion.appComponent
import com.example.moviemood.ui.filters.adapter.CountryAdapter
import javax.inject.Inject

class CountryFragment : Fragment() {

    private lateinit var binding: FragmentCountryBinding


    private lateinit var viewModel: FiltersViewModel
    private lateinit var navController: NavController

    @Inject
    lateinit var filtersViewModelFactory: FiltersViewModelFactory

    private val arguments: CountryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()
        appComponent.injectCountryFragment(this)
        viewModel = filtersViewModelFactory.createViewModel()
        binding.recyclerView.adapter = CountryAdapter(requireContext()) {
            navController.navigate(
                CountryFragmentDirections.actionCountryFragmentToFiltersFragment(
                    arguments.genre,
                    arguments.genreId.toInt(),
                    it.country,
                    it.id
                )
            )
        }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        viewModel.listFilters.observe(viewLifecycleOwner) {
            it?.countries?.let { it1 -> setList(it1) }
        }
        viewModel.getFilters()
    }

    private fun setList(list: List<CountryFilters>) {
        (binding.recyclerView.adapter as? CountryAdapter)?.setList(list)
    }
}
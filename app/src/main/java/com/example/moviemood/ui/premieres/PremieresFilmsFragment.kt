package com.example.moviemood.ui.premieres

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviemood.databinding.FragmentPremieresFilmsBinding
import com.example.moviemood.model.CountryFilters
import com.example.moviemood.model.FilmPremieres
import com.example.moviemood.ui.MyApplication
import com.example.moviemood.ui.MyApplication.Companion.appComponent
import com.example.moviemood.ui.filters.FiltersViewModelFactory
import com.example.moviemood.ui.filters.adapter.CountryAdapter
import com.example.moviemood.ui.home.HomeFragmentDirections
import com.example.moviemood.ui.home.adapter.FilmsAdapter
import com.example.moviemood.ui.premieres.adapter.PremieresAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PremieresFilmsFragment : Fragment() {

    private lateinit var binding: FragmentPremieresFilmsBinding
    private lateinit var navController: NavController

    private lateinit var viewModel: PremieresViewModel

    @Inject
    lateinit var premieresViewModelFactory: PremieresViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPremieresFilmsBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()
        appComponent.injectPremieres(this)
        viewModel = premieresViewModelFactory.createViewModel()
        binding.recyclerView1.adapter = PremieresAdapter(requireContext()) {
            navController.navigate(
                PremieresFilmsFragmentDirections.actionPremieresFilmsFragmentToInfoFilmsFragment4(
                    it.kinopoiskId
                )
            )
        }
        binding.recyclerView1.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.listPremieres.observe(viewLifecycleOwner) {
            it?.items?.let { it1 -> setList(it1) }
        }
        viewModel.getPremieres()
    }

    private fun setList(list: List<FilmPremieres>) {
        (binding.recyclerView1.adapter as? PremieresAdapter)?.setList(list)
    }
}
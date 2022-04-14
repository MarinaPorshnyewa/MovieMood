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
import com.example.moviemood.R
import com.example.moviemood.databinding.FragmentFiltersBinding
import com.example.moviemood.ui.home.ParametersQuery
import com.example.moviemood.utils.*
import com.google.android.material.tabs.TabLayout

class FiltersFragment : Fragment() {

    private lateinit var binding: FragmentFiltersBinding

    private lateinit var navController: NavController

    private val arguments: FiltersFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiltersBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        val genreId = arguments.genreId.toString()
        val countryId = arguments.countryId.toString()
        binding.filtersFragmentButtonGenre.setOnClickListener {
            navController.navigate(
                FiltersFragmentDirections.actionFiltersFragmentToGenreFragment(
                    binding.filtersFragmentButtonCountry.text.toString(),
                    countryId
                )
            )
        }
        binding.filtersFragmentButtonCountry.setOnClickListener {
            navController.navigate(
                FiltersFragmentDirections.actionFiltersFragmentToCountryFragment(
                    binding.filtersFragmentButtonGenre.text.toString(),
                    genreId
                )
            )
        }

        binding.filtersFragmentButtonGenre.text = arguments.genre
        binding.filtersFragmentButtonCountry.text = arguments.country
        binding.filtersFragmentTabLayoutType.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 1) {
                    ParametersQuery.type = ALL_TYPE_FILTER
                }
                if (tab?.position == 2) {
                    ParametersQuery.type = TV_SHOW_TYPE_FILTER
                }
                if (tab?.position == 0) {
                    ParametersQuery.type = FILM_TYPE_FILTER
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.filtersFragmentTabLayoutOrder.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 1) {
                    ParametersQuery.order = RATING_ORDER_FILTER
                }
                if (tab?.position == 2) {
                    ParametersQuery.order = YEAR_ORDER_FILTER
                }
                if (tab?.position == 0) {
                    ParametersQuery.order = NUM_VOTE_ORDER_FILTER
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.filtersFragmentButtonFinish.setOnClickListener {
            ParametersQuery.ratingFrom = binding.filtersFragmentEditTextRatingFrom.text.toString()
            ParametersQuery.ratingTo = binding.filtersFragmentEditTextRatingTo.text.toString()
            ParametersQuery.yearFrom = binding.filtersFragmentEditTextYearFrom.text.toString()
            ParametersQuery.yearTo = binding.filtersFragmentEditTextYearTo.text.toString()
            ParametersQuery.country = arguments.countryId.toString()
            ParametersQuery.genre = arguments.genreId.toString()
            navController.navigate(R.id.homeFragment)
        }
    }
}
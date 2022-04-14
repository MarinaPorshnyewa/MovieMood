package com.example.moviemood.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviemood.databinding.FragmentHomeBinding
import com.example.moviemood.model.Film
import com.example.moviemood.model.Movie
import com.example.moviemood.ui.MyApplication.Companion.appComponent
import com.example.moviemood.ui.home.adapter.FilmsAdapter
import com.example.moviemood.ui.home.adapter.SearchFilmsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()
        appComponent.injectHomeFragment(this)
        viewModel = homeViewModelFactory.createViewModel()

        binding.recyclerView.adapter = FilmsAdapter(requireContext()) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToInfoFilmsFragment(it.kinopoiskId))
        }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        lifecycleScope.launch {
            viewModel.result.collectLatest {
                setList(it)
            }
        }
        binding.homeFragmentEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                SearchFilmsAdapter(requireContext()) {
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToInfoFilmsFragment(it.filmId))
                }.also { binding.recyclerView.adapter = it }
                ParametersQuery.keyboard =
                    binding.homeFragmentTextFieldSearch.editText?.text.toString()
                lifecycleScope.launch {
                    viewModel.resultTwo.collectLatest {
                        setSearchList(it)
                    }
                }
                return@OnEditorActionListener true
            }
            false
        })
        binding.floatingActionButton.setOnClickListener {
            navController.navigate(
                HomeFragmentDirections.actionHomeFragmentToFiltersFragment(
                    "Жанр",
                    2,
                    "Страна",
                    1
                )
            )
        }
    }

    private fun setList(list: PagingData<Movie>) {
        (binding.recyclerView.adapter as? FilmsAdapter)?.submitData(lifecycle, list)
    }

    private fun setSearchList(list: PagingData<Film>) {
        (binding.recyclerView.adapter as? SearchFilmsAdapter)?.submitData(lifecycle, list)
    }

}
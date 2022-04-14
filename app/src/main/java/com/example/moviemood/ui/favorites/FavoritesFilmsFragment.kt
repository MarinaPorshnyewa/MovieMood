package com.example.moviemood.ui.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.databinding.FragmentFavoritesFilmsBinding
import com.example.moviemood.repository.FirestoreRepository
import com.example.moviemood.repository.SharedPreferencesRepositoriy
import com.example.moviemood.ui.MyApplication
import com.example.moviemood.ui.favorites.adapter.FavoriteAdapter
import com.example.moviemood.ui.info.InfoFilmsViewModel
import com.example.moviemood.ui.info.InfoFilmsViewModelFactory
import com.example.moviemood.ui.info.adapter.FactAdapter
import javax.inject.Inject

class FavoritesFilmsFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesFilmsBinding
    private lateinit var navController: NavController

    private lateinit var viewModel: InfoFilmsViewModel

    @Inject
    lateinit var viewModelFactory: InfoFilmsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesFilmsBinding.inflate(inflater)
        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.appComponent.injectFavoriteFilm(this)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(InfoFilmsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        requireActivity().runOnUiThread {
            binding.favoriteRecyclerView.adapter = FavoriteAdapter(requireContext()) {
                navController.navigate(
                    FavoritesFilmsFragmentDirections.actionFavoritesFilmsFragmentToInfoFilmsFragment5(
                        it.kinopoiskId
                    )
                )
            }
            binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context)

            SharedPreferencesRepositoriy(requireContext()).favoriteJsonToArray()?.let {
                (binding.favoriteRecyclerView.adapter as FavoriteAdapter).setDataList(
                    it
                )
            }
        }
    }
}
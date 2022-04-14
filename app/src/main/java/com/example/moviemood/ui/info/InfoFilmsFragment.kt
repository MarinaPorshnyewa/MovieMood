package com.example.moviemood.ui.info

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviemood.databinding.FragmentInfoFilmsBinding
import com.example.moviemood.ui.MyApplication
import javax.inject.Inject
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import android.content.Intent
import android.os.Build

import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemood.R
import com.example.moviemood.model.Movie
import com.example.moviemood.repository.FirestoreRepository
import com.example.moviemood.repository.SharedPreferencesRepositoriy
import com.example.moviemood.ui.info.adapter.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class InfoFilmsFragment : Fragment() {

    private lateinit var binding: FragmentInfoFilmsBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: InfoFilmsViewModel
    val value = 255

    private var isFact = false
    private var isReview = false

    private var list = arrayListOf<Movie>()

    @Inject
    lateinit var viewModelProvider: InfoFilmsViewModelFactory

    private val arguments: InfoFilmsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoFilmsBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.appComponent.injectInfoFilm(this)
        viewModel = ViewModelProvider(this, viewModelProvider)
            .get(InfoFilmsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        if (viewModel.idData.value == null) {
            viewModel.idData.value = arguments.idFilms
        }


        binding.infoFilmsFragmentFavoriteImageView.setOnClickListener {
            if (SharedPreferencesRepositoriy(requireContext()).favoriteJsonToArray() != null) {
                if (SharedPreferencesRepositoriy(requireContext()).favoriteJsonToArray()!!
                        .contains(viewModel.movie.value)
                ) {

                    list = SharedPreferencesRepositoriy(requireContext()).favoriteJsonToArray()!!
                    list.remove(viewModel.movie.value)
                    SharedPreferencesRepositoriy(requireContext()).saveListFilms(list)

                    binding.infoFilmsFragmentFavoriteImageView.setImageResource(R.drawable.ic_star_black)
                } else {
                    list = SharedPreferencesRepositoriy(requireContext()).favoriteJsonToArray()!!
                    viewModel.movie.value?.let { it1 -> list.add(it1) }
                    SharedPreferencesRepositoriy(requireContext()).saveListFilms(list)

                    binding.infoFilmsFragmentFavoriteImageView.setImageResource(R.drawable.ic_star)
                }
            }else{

                SharedPreferencesRepositoriy(requireContext()).saveListFilms(list)

                binding.infoFilmsFragmentFavoriteImageView.setImageResource(R.drawable.ic_star)
            }
        }

//        binding.favoriteToFavorite.setOnClickListener {
//            navController.graph = navController.navInflater.inflate(R.navigation.favorites_graph)
//        }

        viewModel.getInfoFilm(viewModel.idData.value!!)


        viewModel.loadInfoFilm = {


            requireActivity().runOnUiThread {

                list.add(it)

                if (SharedPreferencesRepositoriy(requireContext()).favoriteJsonToArray()!=null &&
                    SharedPreferencesRepositoriy(requireContext()).favoriteJsonToArray()!!
                        .contains(it)
                ) {
                    binding.infoFilmsFragmentFavoriteImageView.setImageResource(R.drawable.ic_star)
                } else {
                    binding.infoFilmsFragmentFavoriteImageView.setImageResource(R.drawable.ic_star_black)
                }
                Glide.with(requireContext()).load(it.posterUrl)
                    .into(binding.infoFilmsFragmentPosterImage)

                binding.infoFilmsFragmentFilmLengthTextView.text = it.filmLength.toString()
                binding.infoFilmsFragmentNameFilmText.text = it.nameRu
                binding.infoFilmsFragmentRatingImdbTextView.text = it.ratingImdb.toString()
                binding.infoFilmsFragmentRatingKinopoiskTextView.text =
                    it.ratingKinopoisk.toString()
                binding.infoFilmsFragmentYearTextView.text = it.year.toString()
                binding.infoFilmsFragmentShortDescriptionTextView.text = it.shortDescription

                binding.infoFilmsFragmentCountriesRecyclerView.run {
                    adapter = CountryAdapter(context)
                    layoutManager = LinearLayoutManager(context)
                }
                (binding.infoFilmsFragmentCountriesRecyclerView.adapter as CountryAdapter).setDataList(
                    it.countries
                )

                binding.infoFilmsFragmentGenresRecyclerView.run {
                    adapter = GenreAdapter(context)
                    layoutManager = LinearLayoutManager(context)
                }
                (binding.infoFilmsFragmentGenresRecyclerView.adapter as GenreAdapter).getDataList(it.genres)

                viewModel.movie.value = it
            }


        }

        /*viewModel.getReviewsFilm(viewModel.idData.value!!)

        viewModel.loadReviesFilm = {
            requireActivity().runOnUiThread {

                binding.infoFilmsFragmentReviewsRecyclerView.run {
                    adapter = ReviewsAdapter(context)
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
                    isNestedScrollingEnabled = false
                }
                (binding.infoFilmsFragmentReviewsRecyclerView.adapter as ReviewsAdapter).setDataList(
                    it
                )
            }
        }

        2binding.infoFilmsFragmentReviewTextView.setOnClickListener {
            if (isReview) {
                binding.infoFilmsFragmentReviewsRecyclerView.visibility = View.GONE
                isReview = false
            } else {
                binding.infoFilmsFragmentReviewsRecyclerView.visibility = View.VISIBLE
                isReview = true
            }

        }*/

        binding.infoFilmsFragmentStartVideoButton.setOnClickListener {

            viewModel.getVideoFilm(viewModel.idData.value!!)

            viewModel.loadVideo = {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(it[0].url)
                    )
                )
            }

            viewModel.loadAnswer = {
                requireActivity().runOnUiThread {
                    Toast.makeText(context, "Sorry, this video is not", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        binding.infoFilmsFragmentFactTextView.setOnClickListener {
            if (isFact) {
                binding.infoFilmsFragmentFactsRecyclerView.visibility = View.GONE
                isFact = false
            } else {
                binding.infoFilmsFragmentFactsRecyclerView.visibility = View.VISIBLE
                isFact = true
            }

        }
        viewModel.getFactsFilm(viewModel.idData.value!!)
        viewModel.loadFacts = {
            requireActivity().runOnUiThread {

                binding.infoFilmsFragmentFactsRecyclerView.run {
                    adapter = FactAdapter(context)
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
                    isNestedScrollingEnabled = false
                }
                (binding.infoFilmsFragmentFactsRecyclerView.adapter as FactAdapter).setDataList(
                    it
                )
            }
        }

        viewModel.getImagesFilm(viewModel.idData.value!!)
        viewModel.loadImages = {
            requireActivity().runOnUiThread {
                binding.infoFilmsFragmentImageRecyclerView.run {
                    adapter = ImageAdapter(context)
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
                (binding.infoFilmsFragmentImageRecyclerView.adapter as ImageAdapter).setDataList(
                    it
                )
            }
        }

        viewModel.getSimilarFilm(viewModel.idData.value!!)
        viewModel.loadSimilar = {
            requireActivity().runOnUiThread {
                binding.infoFilmsFragmentSimilarsRecyclerView.adapter =
                    SimilarAdapter(requireContext()) {

                        viewModel.idData.value = it.filmId
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            fragmentManager?.beginTransaction()?.detach(this)?.commitNow();
                            fragmentManager?.beginTransaction()?.attach(this)?.commitNow();
                        } else {
                            fragmentManager?.beginTransaction()?.detach(this)?.attach(this)
                                ?.commit();
                        }
                    }

                binding.infoFilmsFragmentSimilarsRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                (binding.infoFilmsFragmentSimilarsRecyclerView.adapter as SimilarAdapter).setDataList(
                    it
                )
            }
        }


        binding.infoFilmsFragmentShareImageView.setOnClickListener {

            val intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            val shareSub = "https://kinopoisk.com/moviemood?id=${viewModel.idData.value}"
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(intent, "Share your app"))
        }


    }

}
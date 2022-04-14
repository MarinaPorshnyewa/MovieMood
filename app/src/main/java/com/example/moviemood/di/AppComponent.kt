package com.example.moviemood.di


import com.example.moviemood.model.Favorite
import com.example.moviemood.ui.MainActivity
import com.example.moviemood.ui.filters.CountryFragment
import com.example.moviemood.ui.filters.FiltersFragment
import com.example.moviemood.ui.filters.GenreFragment
import com.example.moviemood.ui.home.HomeFragment
import com.example.moviemood.ui.authorization.AuthorizationFragment
import com.example.moviemood.ui.favorites.FavoritesFilmsFragment
import com.example.moviemood.ui.info.InfoFilmsFragment
import com.example.moviemood.ui.premieres.PremieresFilmsFragment
import com.example.moviemood.ui.registration.RegistrationFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [FireBaseModule::class, NetworkManagerModule::class])
@Singleton
interface AppComponent {

    fun injectGoogleSignIn(activity: MainActivity)

    fun injectRegistration(fragment: RegistrationFragment)


    fun injectHomeFragment(fragment: HomeFragment)

    fun injectGenreFragment(fragment: GenreFragment)

    fun injectCountryFragment(fragment: CountryFragment)
    fun injectLogin(fragment: AuthorizationFragment)

    fun injectInfoFilm(fragment: InfoFilmsFragment)

    fun injectFavoriteFilm(fragment: FavoritesFilmsFragment)

    fun injectPremieres(fragment: PremieresFilmsFragment)

}
package com.android.desafiofinalstarwars.koin

import com.android.desafiofinalstarwars.retrofit.webclient.RetrofitInitializer
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.Repository
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.ui.home.viewmodels.SpeciesViewModel
import com.android.desafiofinalstarwars.ui.left.viewmodels.StarshipsViewModel
import com.android.desafiofinalstarwars.ui.home.viewmodels.CharactersViewModel
import com.android.desafiofinalstarwars.ui.left.viewmodels.VehiclesViewModel
import com.android.desafiofinalstarwars.ui.right.viewmodel.MoviesViewModel
import com.android.desafiofinalstarwars.ui.right.viewmodel.PlanetsViewModel
import com.android.desafiofinalstarwars.ui.search.viewmodels.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val modelModule : Module = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { CharactersSearchViewModel(get()) }
    viewModel { SpeciesViewModel(get()) }
    viewModel { SpeciesSearchViewModel(get()) }
    viewModel { StarshipsViewModel(get()) }
    viewModel { StarshipsSearchViewModel(get()) }
    viewModel { VehiclesViewModel(get()) }
    viewModel { VehiclesSearchViewModel(get()) }
    viewModel { PlanetsViewModel(get()) }
    viewModel { PlanetsSearchViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
}

val retrofitModule : Module = module {
    single<RepositoryInterface> {Repository(get())}
}

val dataModule = module {
    single { RetrofitInitializer.create(androidContext()) }
}


val appModules : List<Module> = listOf(modelModule, retrofitModule, dataModule)
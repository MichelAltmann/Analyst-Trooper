package com.android.desafiofinalstarwars.koin

import com.android.desafiofinalstarwars.retrofit.webclient.RetrofitInicializador
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.Repository
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.ui.home.viewmodels.SpeciesViewModel
import com.android.desafiofinalstarwars.ui.left.viewmodels.StarshipsViewModel
import com.android.desafiofinalstarwars.ui.home.viewmodels.CharactersViewModel
import com.android.desafiofinalstarwars.ui.left.viewmodels.VehiclesViewModel
import com.android.desafiofinalstarwars.ui.right.viewmodel.MoviesViewModel
import com.android.desafiofinalstarwars.ui.right.viewmodel.PlanetsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val modelModule : Module = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { StarshipsViewModel(get()) }
    viewModel { PlanetsViewModel(get()) }
    viewModel { SpeciesViewModel(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { VehiclesViewModel(get()) }
}

val retrofitModule : Module = module {
    single<RepositoryInterface> {Repository(get())}
}

val dataModule = module {
    single { RetrofitInicializador.create(androidContext()) }
}


val appModules : List<Module> = listOf(modelModule, retrofitModule, dataModule)
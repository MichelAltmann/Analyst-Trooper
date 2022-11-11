package com.android.desafiofinalstarwars.koin

import com.android.desafiofinalstarwars.retrofit.webclient.RetrofitInicializador
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.Repository
import com.android.desafiofinalstarwars.retrofit.webclient.personagens.RepositoryInterface
import com.android.desafiofinalstarwars.ui.home.EspeciesViewModel
import com.android.desafiofinalstarwars.ui.left.NavesViewModel
import com.android.desafiofinalstarwars.ui.home.PersonagensViewModel
import com.android.desafiofinalstarwars.ui.left.VeiculosViewModel
import com.android.desafiofinalstarwars.ui.right.FilmesViewModel
import com.android.desafiofinalstarwars.ui.right.PlanetasViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val modelModule : Module = module {
    viewModel { PersonagensViewModel(get()) }
    viewModel { NavesViewModel(get()) }
    viewModel { PlanetasViewModel(get()) }
    viewModel { EspeciesViewModel(get()) }
    viewModel { FilmesViewModel(get()) }
    viewModel { VeiculosViewModel(get()) }
}

val retrofitModule : Module = module {
    single<RepositoryInterface> {Repository(get())}
}

val dataModule = module {
    single { RetrofitInicializador.create(androidContext()) }
}


val appModules : List<Module> = listOf(modelModule, retrofitModule, dataModule)
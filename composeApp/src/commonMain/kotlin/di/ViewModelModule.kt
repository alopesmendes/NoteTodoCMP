package di

import features.categories.presentation.viewModels.CategoryViewModel
import features.settings.presentation.viewModels.SettingsViewModel
import features.tasks.presentation.viewModels.TasksViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::TasksViewModel)

    viewModelOf(::CategoryViewModel)

    viewModelOf(::SettingsViewModel)
}
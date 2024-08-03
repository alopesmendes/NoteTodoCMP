package di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single<CoroutineDispatcher>(named("IO_DISPATCHER")) {
        Dispatchers.IO
    }
}

val modules = listOf(
    platformModule(),
    datasourceModule,
    repositoryModule,
    useCaseModule,
    reducerModule,
    viewModelModule,
    appModule,
)

fun initializeKoin(
    appDeclaration: KoinAppDeclaration = {}
) {
    startKoin {
        appDeclaration()
        modules(modules)
    }
}
package rusmammals.domain.di

import org.koin.dsl.module
import rusmammals.domain.usecases.AuthUseCase
import rusmammals.domain.usecases.AuthUseCaseImpl
import rusmammals.domain.usecases.SendFeedbackUseCase
import rusmammals.domain.usecases.SendFeedbackUseCaseImpl
import rusmammals.domain.usecases.SpeciesUseCase
import rusmammals.domain.usecases.SpeciesUseCaseImpl

val domainModule = module {
    factory<AuthUseCase> { AuthUseCaseImpl(get()) }
    factory<SpeciesUseCase> { SpeciesUseCaseImpl(get()) }
    factory<SendFeedbackUseCase> { SendFeedbackUseCaseImpl(get()) }
}
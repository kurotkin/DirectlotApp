package com.kurotkin.network

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface NetworkComponent {
}
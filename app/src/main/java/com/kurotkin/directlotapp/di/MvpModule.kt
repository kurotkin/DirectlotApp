package com.kurotkin.directlotapp.di

import com.kurotkin.core.di.FeatureScope
import com.kurotkin.directlotapp.presentation.presenter.LotListPresenter
import com.kurotkin.directlotapp.presentation.presenter.LotListPresenterImpl
import com.kurotkin.directlotapp.presentation.view.ViewList
import com.kurotkin.directlotapp.presentation.view.ViewListImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @FeatureScope
    fun lotListPresenter() : LotListPresenter = LotListPresenterImpl()


    @Provides
    @FeatureScope
    fun lotListView() : ViewList = ViewListImpl()

}
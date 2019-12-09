package com.kurotkin.directlotapp.di

import com.kurotkin.core.di.FeatureScope
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenter
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenterImpl
import com.kurotkin.directlotapp.presentation.one_lot.view.ViewInfo
import com.kurotkin.directlotapp.presentation.one_lot.view.ViewInfoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @FeatureScope
    fun lotInfoPresenter() : LotInfoPresenter = LotInfoPresenterImpl()

    @Provides
    @FeatureScope
    fun lotInfoView() : ViewInfo = ViewInfoImpl()
}
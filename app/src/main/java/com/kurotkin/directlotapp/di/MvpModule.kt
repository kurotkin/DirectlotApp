package com.kurotkin.directlotapp.di

import com.kurotkin.directlotapp.presenter.LotListPresenter
import com.kurotkin.directlotapp.presenter.LotListPresenterImpl
import com.kurotkin.directlotapp.view.ViewList
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun lotListPresenter(view: ViewList,
                         litener: LotListPresenter.OnClickGetInfoListener) : LotListPresenter =
        LotListPresenterImpl(view, litener)

}
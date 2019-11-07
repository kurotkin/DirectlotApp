package com.kurotkin.directlotapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.kurotkin.directlotapp.presenter.LotListPresenter
import com.kurotkin.directlotapp.presenter.LotListPresenterImpl
import com.kurotkin.directlotapp.presenter.OnClickGetInfoListener
import com.kurotkin.directlotapp.view.ViewList
import com.kurotkin.directlotapp.view.ViewListImpl
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnClickGetInfoListener {

    @Inject
    lateinit var presenter: LotListPresenter

    @Inject
    lateinit var view: ViewList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_main, null)
        setContentView(contentView)

        App.appComponent.inject(this)

        view.setContentView(contentView)
        presenter.attachView(view)
        presenter.attachListener(this)
        view.onInflate(presenter)
        presenter.onMakeList()
    }

    override fun onClickGetInfoListener(id: Long) {
        val intent = InfoActivity.getInstance(this, id)
        startActivity(intent)
    }
}

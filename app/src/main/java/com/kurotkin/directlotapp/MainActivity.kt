package com.kurotkin.directlotapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.kurotkin.directlotapp.model.LotsRepositoryImpl
import com.kurotkin.directlotapp.presenter.LotListPresenter
import com.kurotkin.directlotapp.presenter.LotListPresenterImpl
import com.kurotkin.directlotapp.view.ViewListImpl

class MainActivity : AppCompatActivity(), LotListPresenter.OnClickGetInfoListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_main, null)
        setContentView(contentView)

        val view = ViewListImpl(contentView)
        val repository = LotsRepositoryImpl()
        val presenter = LotListPresenterImpl(view, repository, this)
        view.onFinishInflate(presenter)
        presenter.onMakeList()
    }

    override fun onClickGetInfoListener(id: Long) {
        val intent = InfoActivity.getInstance(this, id)
        startActivity(intent)
    }
}

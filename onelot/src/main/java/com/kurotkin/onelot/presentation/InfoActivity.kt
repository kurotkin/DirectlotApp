package com.kurotkin.onelot.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.kurotkin.core.di.CoreInjectHelper
import com.kurotkin.directlotapp.di.DaggerOnelotComponent
import com.kurotkin.directlotapp.di.OnelotComponent
import com.kurotkin.directlotapp.presentation.one_lot.presenter.LotInfoPresenter
import com.kurotkin.directlotapp.presentation.one_lot.view.ViewInfo
import com.kurotkin.onelot.R
import javax.inject.Inject

class InfoActivity : AppCompatActivity(), LotInfoPresenter.OnGoToWeb {
    @Inject
    lateinit var presenter: LotInfoPresenter

    @Inject
    lateinit var view: ViewInfo

    companion object{
        const val LOG_ID = "log id"
        fun getInstance(context: Context, id: Long): Intent {
            val intent = Intent(context, InfoActivity::class.java)
            intent.putExtra(LOG_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_info, null)
        setContentView(contentView)
        val id = intent.getLongExtra(LOG_ID, 0)

        DaggerOnelotComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .build()
            .inject(this)

        view.setContentView(contentView)
        presenter.attachView(view)
        presenter.attachListener(this)
        view.onInflate(presenter)
        presenter.onViewCreated(id)
    }

    override fun onGoToWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

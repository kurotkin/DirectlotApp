package com.kurotkin.directlotapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.kurotkin.directlotapp.presenter.LotInfoPresenter
import com.kurotkin.directlotapp.view.ViewInfo
import javax.inject.Inject

class InfoActivity : AppCompatActivity(), LotInfoPresenter.OnGoToWeb {

    @Inject
    lateinit var presenter: LotInfoPresenter

    @Inject
    lateinit var view: ViewInfo

    companion object{
        const val LOG_ID = "log id"
        fun getInstance(context: Context, id: Long): Intent{
            val intent = Intent(context, InfoActivity::class.java)
            intent.putExtra(LOG_ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_info, null)
        setContentView(contentView)
        val id = intent.getSerializableExtra(LOG_ID) as Long

        App.appComponent.inject(this)

        view.setContentView(contentView)
        presenter.flashId(id)
        presenter.attachView(view)
        presenter.attachListener(this)
        view.onInflate(presenter)
        presenter.onViewCreated()
    }

    override fun onGoToWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}

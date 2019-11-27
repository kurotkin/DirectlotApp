package com.kurotkin.directlotapp.presentation.list_of_lots

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.kurotkin.directlotapp.App
import com.kurotkin.directlotapp.R
import com.kurotkin.directlotapp.presentation.about.AboutActivity
import com.kurotkin.directlotapp.presentation.list_of_lots.presenter.LotListPresenter
import com.kurotkin.directlotapp.presentation.list_of_lots.presenter.OnClickGetInfoListener
import com.kurotkin.directlotapp.presentation.list_of_lots.view.ViewList
import com.kurotkin.directlotapp.presentation.one_lot.InfoActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    OnClickGetInfoListener {

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
        val intent =
            InfoActivity.getInstance(
                this,
                id
            )
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_about -> {
                showInfo()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showInfo(){
        var bundle: Bundle? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            val v = findViewById<View>(R.id.action_about)
            if (v != null) {
                val options = ActivityOptions.makeSceneTransitionAnimation(this, v, "transition")
                bundle = options.toBundle()
            }
        }

        val intent = Intent(this, AboutActivity::class.java)
        if (bundle == null) {
            startActivity(intent)
        } else {
            startActivity(intent, bundle)
        }
    }
}

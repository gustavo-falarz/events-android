package com.pinecone.events.ui.events

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.pinecone.events.R
import com.pinecone.events.model.Event
import com.pinecone.events.ui.BaseView
import com.pinecone.events.ui.eventDetails.EventDetailsView
import com.pinecone.events.ui.events.Contract
import com.pinecone.events.ui.events.EventAdapter
import com.pinecone.events.ui.events.EventsPresenter
import com.pinecone.events.ui.newEvent.NewEventView
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_events_view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity

class EventsView : BaseView(), Contract.View, NavigationView.OnNavigationItemSelectedListener {

    private val presenter = EventsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        rvEvents.layoutManager = LinearLayoutManager(this)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        getEvents()
    }

    override fun getEvents() {
        presenter.getEvents()
    }

    override fun onEventsLoaded(observable: Observable<List<Event>>) {
        showProgress()
        observable.applySchedulers().subscribeBy(
                onNext = {
                    createAdapter(it)
                },
                onError = {
                    handleException(it)
                },
                onComplete = {
                    closeProgress()
                }
        )
    }

    override fun createAdapter(events: List<Event>) {
        val adapter = EventAdapter(events) {
            onClickEvent(it)
        }
        rvEvents.adapter = adapter
    }

    override fun onClickEvent(event: Event) {
        presenter.showEvent(event)
    }

    override fun showEvent(event: Event) {
        startActivity<EventDetailsView>("event" to event)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_add_event -> {
                startActivity<NewEventView>()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return false
    }
}

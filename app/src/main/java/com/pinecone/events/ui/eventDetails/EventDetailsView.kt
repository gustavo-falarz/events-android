package com.pinecone.events.ui.eventDetails

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import com.pinecone.events.R
import com.pinecone.events.model.Attendee
import com.pinecone.events.model.Event
import com.pinecone.events.ui.BaseView
import com.pinecone.events.ui.eventDetails.Contract.View
import com.pinecone.events.util.DateUtil
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import com.pinecone.events.util.DateUtil.formatToString
import kotlinx.android.synthetic.main.activity_event_details_view.*

class EventDetailsView : BaseView(), View {
    private val presenter = EventDetailsPresenter(this)
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details_view)
        setupActionBar()
        rvAttendees.layoutManager = LinearLayoutManager(this)
        event = intent.extras!!["event"] as Event
    }

    override fun onUserAttending() {
        btAttend.setText(R.string.label_leave_event)
        ViewCompat.setBackgroundTintList(btAttend, ContextCompat.getColorStateList(this, R.color.red_400))
        btAttend.setOnClickListener { leaveEvent() }
    }

    override fun onUserNotAttending() {
        btAttend.setText(R.string.label_attend_event)
        ViewCompat.setBackgroundTintList(btAttend, ContextCompat.getColorStateList(this, R.color.green_400))
        btAttend.setOnClickListener { registerToAttend() }
    }

    override fun onRegisterUser(observable: Observable<Event>) {
        showProgress()
        observable.applySchedulers().subscribeBy(
                onError = {
                    handleException(it)
                },
                onNext = {
                    onUserRegistered(it)
                },
                onComplete = {
                    closeProgress()
                }
        )
    }

    override fun onRemoveUser(observable: Observable<Event>) {
        showProgress()
        observable.applySchedulers().subscribeBy(
                onError = {
                    handleException(it)
                },
                onNext = {
                    onUserLeft(it)
                },
                onComplete = {
                    closeProgress()
                }
        )
    }

    override fun onUserRegistered(event: Event) {
        presenter.loadEvent(event)
    }

    override fun onUserLeft(event: Event) {
        presenter.loadEvent(event)
    }

    override fun registerToAttend() {
        event.id?.let { presenter.registerToAttend(it) }
    }

    override fun leaveEvent() {
        event.id?.let { presenter.leaveEvent(it) }
    }

    override fun onStart() {
        super.onStart()
        presenter.loadEvent(event)
    }

    override fun onLoadEvent(event: Event) {
        tvPlace.text = event.place.name
        tvDate.text = event.start.formatToString(DateUtil.Pattern.EVENT_DATE)
        tvTime.text = event.start.formatToString(DateUtil.Pattern.EVENT_TIME)
    }

    private lateinit var adapter: AttendeeAdapter

    override fun onLoadAttendees(attendees: List<Attendee>) {
        adapter = AttendeeAdapter(attendees)
        rvAttendees.adapter = adapter
    }
}

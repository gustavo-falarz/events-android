package com.pinecone.events.ui.newEvent

import android.os.Bundle
import com.pinecone.events.R
import com.pinecone.events.model.Place
import com.pinecone.events.ui.BaseView
import com.pinecone.events.ui.events.EventsView
import com.pinecone.events.widget.DatePickerFragment
import com.pinecone.events.widget.ListDialog
import com.pinecone.events.widget.TimePickerFragment
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_new_event_view.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

class NewEventView : BaseView(), Contract.View {
    private lateinit var dialog: ListDialog
    private val presenter = NewEventPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_event_view)
        setupActionBar()
        tvTime.setOnClickListener { onClickTime() }
        tvDate.setOnClickListener { onClickDate() }
        tvPlace.setOnClickListener { onClickPlaces() }
        btAddEvent.setOnClickListener { onClickAddEvent() }
    }

    private fun onClickAddEvent() {
        addEvent()
    }

    override fun onPlacesLoaded(observable: Observable<List<Place>>) {
        showProgress()
        observable.applySchedulers().subscribeBy(
                onNext = {
                    showPlaces(it)
                },
                onError = {
                    handleException(it)
                },
                onComplete = {
                    closeProgress()
                }
        )
    }

    override fun getPlaces() {
        presenter.getPlaces()
    }

    override fun addEvent() {
        presenter.addEvent()
    }

    override fun onAddEvent(completable: Completable) {
        showProgress()
        completable.applySchedulers().subscribeBy(
                onError = {
                    handleException(it)
                },
                onComplete = {
                    presenter.onEventAdded()
                    closeProgress()
                }
        )
    }

    override fun onEventAdded() {
        startActivity(intentFor<EventsView>().clearTask().newTask())
    }

    override fun onMissingInfo(message: String) {
        showWarning(message)
    }

    override fun showPlaces(places: List<Place>) {
        val adapter = PlaceAdapter(places) {
            dialog.dismiss()
            presenter.placeId = it.id
            tvPlace.text = it.name
        }
        dialog = ListDialog.newInstance(this, adapter)
        dialog.show(supportFragmentManager, "listDialog")
    }

    private fun onClickPlaces() {
        presenter.getPlaces()
    }

    private fun onClickDate() {
        val picker = DatePickerFragment.newInstance(this)
        { year, month, day ->
            presenter.date = formatDateFromPicker(year, month, day)
            tvDate.text = formatDateFromPicker(year, month, day)
        }
        picker.show(supportFragmentManager, "datePicker")
    }

    private fun onClickTime() {
        val picker = TimePickerFragment.newInstance(this)
        { hourOfDay, minutes ->
            presenter.time = formatTimeFromPicker(hourOfDay, minutes)
            tvTime.text = formatTimeFromPicker(hourOfDay, minutes)
        }
        picker.show(supportFragmentManager, "timePicker")
    }

    private fun formatTimeFromPicker(hour: Int, minutes: Int): String {
        val hourStr = hour.toString().padStart(2, '0')
        val minutesStr = minutes.toString().padStart(2, '0')
        return "$hourStr${getString(R.string.time_separator)}$minutesStr"
    }

    private fun formatDateFromPicker(year: Int, month: Int, day: Int): String {
        val dayStr = day.toString().padStart(2, '0')
        val monthStr = month.plus(1).toString().padStart(2, '0')
        return "$dayStr${getString(R.string.date_separator)}$monthStr${getString(R.string.date_separator)}$year"
    }
}

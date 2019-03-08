package com.pinecone.events.ui


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.pinecone.events.R
import com.pinecone.events.widget.Progress
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import retrofit2.HttpException
import com.pinecone.events.util.ExceptionHandler.parse

/**
 * Created by Gustavo on 12/4/2017.
 */


@SuppressLint("Registered")
open class BaseView : AppCompatActivity() {

    private val tag = "BaseView"
    private var progress: Progress? = null


    fun <T> Observable<T>.applySchedulers(): Observable<T> {
        return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
    }

    fun Completable.applySchedulers(): Completable {
        return subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
    }

    fun setupActionBar() {
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun handleException(exception: Throwable) {
        closeProgress()
        val message: String = when (exception) {
            is HttpException -> exception.parse().message
            else -> exception.message!!
        }

        alert(message, getString(R.string.error_title))
        { yesButton { /*if (getActivity() !is MainActivity )finish()*/ } }.show()

    }

    fun showWarning(message: Int) {
        showWarning(getString(message))
    }

    fun showWarning(message: String) {
        alert(message, getString(R.string.error_title)) { yesButton { } }.show()
    }

    fun showMessage(message: String) {
        alert(message, getString(R.string.title_success)) { yesButton { } }.show()
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showProgress() {
        if (progress == null) {
            progress = Progress(this)
        }
        progress!!.show()
    }

    protected fun closeProgress() {
        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
        }
    }

    fun getActivity() = this

}
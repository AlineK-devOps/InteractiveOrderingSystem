package ru.nstu.ordsys.features.dishes.menu.ui.adapter.viewholder

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel

abstract class BaseView(context: Context) : MaterialCardView(context), LifecycleOwner {

    init {
        val params = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        layoutParams = params
    }

    private var registry: LifecycleRegistry? = null

    internal var viewModel: BaseViewModel? = null

    fun setViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
    }

    override fun getLifecycle(): Lifecycle = registry ?: throw IllegalStateException("Lifecycle registry has not been initialized yet.")

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        registry = LifecycleRegistry(this)

        registry?.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        registry?.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}
package mobi.akesson.jdm.presenter

import java.lang.ref.WeakReference

abstract class BasePresenter<M, V> {

    protected var model: M? = null
        set(value) {
            resetState()
            field = value
            if (setupDone()) {
                updateView()
            }
        }

    protected fun resetState() {
    }

    private var view: WeakReference<V>? = null

    open fun bind(view: V) {
        this.view = WeakReference(view)
        if (setupDone()) {
            updateView()
        }
    }

    open fun unbind() {
        this.view = null
    }

    protected fun view(): V? = if (view == null) null else view!!.get()

    protected abstract fun updateView()

    protected fun setupDone(): Boolean = view() != null && model != null
}

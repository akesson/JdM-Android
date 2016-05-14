package mobi.akesson.jdm.presenter

import java.lang.ref.WeakReference

abstract class BasePresenter<M, V> {

    /**
     * The model in MVP.
     *
     * The method [updateView] is called any time a new value is assigned to this property if the presenter is [set up][setupDone].
     */
    protected var model: M? = null
        set(value) {
            resetState()
            field = value
            if (setupDone()) {
                updateView()
            }
        }

    /**
     * The view in MVP.
     */
    protected var view: V? = null
        get() = if (viewRef == null) null else viewRef!!.get()
        private set

    private var viewRef: WeakReference<V>? = null

    protected fun resetState() {
    }

    /**
     * Bind this presenter to the [view].
     *
     * The method [updateView] is called if the presenter is [set up][setupDone].
     */
    open fun bind(view: V) {
        this.viewRef = WeakReference(view)
        if (setupDone()) {
            updateView()
        }
    }

    /**
     * Unbind this presenter from the [view].
     *
     * Override this method to perform any cleanup. Such as closing a database connection.
     */
    open fun unbind() {
        this.view = null
    }

    protected fun setupDone(): Boolean = view != null && model != null

    /**
     * Implement this method to update the view.
     */
    protected abstract fun updateView()
}

package mobi.akesson.jdm.ui.core.view

import mobi.akesson.jdm.domain.model.Table

interface TableView {

    fun showTables(tables: List<Table>?)

    fun showLoading()

    fun showEmpty()
}

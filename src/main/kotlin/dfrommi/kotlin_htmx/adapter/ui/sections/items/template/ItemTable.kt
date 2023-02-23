package dfrommi.kotlin_htmx.adapter.ui.sections.items.template

import dfrommi.kotlin_htmx.adapter.ui.components.core.fullWidth
import dfrommi.kotlin_htmx.adapter.ui.components.core.selected
import dfrommi.kotlin_htmx.adapter.ui.components.core.selectedClass
import dfrommi.kotlin_htmx.adapter.ui.components.core.table
import dfrommi.kotlin_htmx.adapter.ui.components.core.tbody
import dfrommi.kotlin_htmx.adapter.ui.components.core.td
import dfrommi.kotlin_htmx.adapter.ui.components.core.thead
import dfrommi.kotlin_htmx.adapter.ui.components.core.tr
import dfrommi.kotlin_htmx.adapter.ui.lib.htmlFragment
import dfrommi.kotlin_htmx.adapter.ui.lib.hx
import dfrommi.kotlin_htmx.adapter.ui.lib.on
import dfrommi.kotlin_htmx.domain.model.Item
import dfrommi.kotlin_htmx.domain.service.ItemService
import io.jooby.Kooby
import kotlinx.html.FlowContent

class ItemTable(itemService: ItemService) {
    val routes = Kooby {
        path("/items/table") {
            get("/body") {
                htmlFragment {
                    body(itemService.getAll(), ctx.query("selected").singleOrNull()?.intValue())
                }
            }
        }
    }

    context(FlowContent)
    fun table(items: Iterable<Item>, selected: Int? = null) {
        table {
            fullWidth()

            thead {
                tr {
                    td { +"ID" }
                    td { +"Name" }
                }
            }

            body(items, selected)
        }
    }

    context(FlowContent)
    private fun body(items: Iterable<Item>, selected: Int? = null) {
        tbody {
            hx {
                trigger("itemAdded from:body,itemDeleted from:body")
                swap("outerHTML")
                get("/items/table/body?selected={event.id}")
            }

            items.forEach {
                row(it, selected = selected != null && it.id == selected)
            }
        }
    }

    context(FlowContent)
    private fun row(item: Item, selected: Boolean = false) {
        tr {
            if (selected) {
                selected()
            }

            on("click take .${selectedClass} then trigger itemSelected(id:${item.id})")

            td { +"${item.id}" }
            td { +item.name }
        }
    }
}

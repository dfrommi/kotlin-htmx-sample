package dfrommi.kotlin_htmx.adapter.ui.sections.items

import dfrommi.kotlin_htmx.adapter.ui.components.content
import dfrommi.kotlin_htmx.adapter.ui.components.core.column
import dfrommi.kotlin_htmx.adapter.ui.components.core.columns
import dfrommi.kotlin_htmx.adapter.ui.lib.html
import dfrommi.kotlin_htmx.adapter.ui.sections.items.template.ItemForm
import dfrommi.kotlin_htmx.adapter.ui.sections.items.template.ItemTable
import dfrommi.kotlin_htmx.adapter.ui.sections.layout
import dfrommi.kotlin_htmx.domain.model.Item
import dfrommi.kotlin_htmx.domain.service.ItemService
import io.jooby.Kooby
import kotlinx.html.TagConsumer

class ItemPage(itemService: ItemService) {
    private val itemForm = ItemForm(itemService)
    private val itemTable = ItemTable(itemService)

    val routes = Kooby {
        path("/items") {
            get("/") {
                html {
                    show(itemService.getAll())
                }
            }

            get("/{id}") {
                html {
                    show(itemService.getAll(), ctx.path("id").intValue())
                }
            }
        }

        mount(itemTable.routes)
        mount(itemForm.routes)
    }

    context(TagConsumer<String>)
    private fun show(items: Iterable<Item>, selected: Int? = null) = layout {
        content("Items", "Arbitrary items with a name") {
            columns {
                column {
                    itemTable.table(items, selected)
                }

                column {
                    itemForm.container()
                }
            }
        }
    }
}

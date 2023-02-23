package dfrommi.kotlin_htmx.adapter.ui.sections.items.template

import dfrommi.kotlin_htmx.adapter.ui.components.core.button
import dfrommi.kotlin_htmx.adapter.ui.components.core.buttonGroup
import dfrommi.kotlin_htmx.adapter.ui.components.core.div
import dfrommi.kotlin_htmx.adapter.ui.components.core.form
import dfrommi.kotlin_htmx.adapter.ui.components.core.submitButton
import dfrommi.kotlin_htmx.adapter.ui.components.core.textInput
import dfrommi.kotlin_htmx.adapter.ui.components.panel
import dfrommi.kotlin_htmx.adapter.ui.components.showSuccess
import dfrommi.kotlin_htmx.adapter.ui.components.showWarn
import dfrommi.kotlin_htmx.adapter.ui.lib.htmlFragment
import dfrommi.kotlin_htmx.adapter.ui.lib.hx
import dfrommi.kotlin_htmx.adapter.ui.lib.raiseHxEvent
import dfrommi.kotlin_htmx.domain.model.Item
import dfrommi.kotlin_htmx.domain.service.ItemService
import io.jooby.Kooby
import io.jooby.StatusCode
import kotlinx.html.FlowContent
import kotlinx.html.id

class ItemForm(private val itemService: ItemService) {
    val routes = Kooby {
        path("/items/form") {
            get("/") {
                htmlFragment {
                    show()
                }
            }

            get("/{id}") {
                itemService.getById(ctx.path("id").intValue())?.let { item ->
                    htmlFragment {
                        show(item)
                    }
                } ?: StatusCode.NOT_FOUND
            }

            post("/") {
                val savedItem = itemService.add(ctx.form("name").value())

                raiseHxEvent("itemAdded", "id" to savedItem.id)
                htmlFragment {
                    showSuccess("Item ${savedItem.id} added")
                    show(savedItem)
                }
            }

            delete("/{id}") {
                val id = ctx.path("id").intValue()
                itemService.delete(id)

                raiseHxEvent("itemDeleted", "id" to id)
                htmlFragment {
                    showWarn("Item $id deleted")
                }
            }
        }
    }

    context(FlowContent)
    fun container() {
        div {
            buttonGroup {
                right()

                button {
                    success()
                    small()

                    hx {
                        onClick()
                        get("/items/form")
                        target("#item-panel-wrapper")
                    }

                    +"Add new item"
                }
            }

            div {
                id = "item-panel-wrapper"

                hx {
                    target("this")
                    trigger("itemSelected from:body")
                    get("/items/form/{event.id}")
                }
            }
        }
    }

    context(FlowContent)
    private fun show(item: Item? = null) {
        panel {
            header {
                if (item == null) +"Create Item" else +"Item ${item.id}"
            }

            block {
                form {
                    item?.let {
                        textInput(
                            label = "ID",
                            name = "id",
                            value = "${item.id}",
                            readonly = true
                        )
                    }

                    textInput(
                        label = "Name",
                        name = "name",
                        value = item?.name,
                        readonly = item != null,
                        placeholder = "Item name"
                    )

                    buttonGroup {
                        right()

                        if (item == null) {
                            submitButton {
                                hx { post("/items/form") }

                                primary()
                                +"Save"
                            }
                        } else {
                            button {
                                hx { delete("/items/form/${item.id}") }

                                danger()
                                +"Delete"
                            }
                        }
                    }
                }
            }
        }
    }
}


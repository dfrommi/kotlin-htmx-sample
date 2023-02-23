package dfrommi.kotlin_htmx.adapter.ui.components

import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.div
import kotlinx.html.visit

context(FlowContent)
fun panel(body: Panel.() -> Unit) = Panel(consumer).visit(body)

class Panel(consumer : TagConsumer<*>): DIV(attributesMapOf("class", "panel"), consumer) {
    context(FlowContent)
    fun header(body: Panel.() -> Unit) {
        this.div(classes = "panel-heading") {
            this@Panel.body()
        }
    }

    context(FlowContent)
    fun block(body: Panel.() -> Unit) {
        this.div(classes = "panel-block") {
            this@Panel.body()
        }
    }
}

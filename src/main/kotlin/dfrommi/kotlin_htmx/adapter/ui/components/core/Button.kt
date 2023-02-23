package dfrommi.kotlin_htmx.adapter.ui.components.core

import kotlinx.html.BUTTON
import kotlinx.html.ButtonType
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.TagConsumer
import kotlinx.html.attributesMapOf
import kotlinx.html.classes
import kotlinx.html.visit

context(FlowContent)
fun buttonGroup(body: ButtonGroup.() -> Unit) = ButtonGroup(consumer).visit(body)

context(FlowContent)
fun button(body: Button.() -> Unit) = Button(consumer).visit(body)

context(FlowContent)
fun submitButton(body: Button.() -> Unit) = Button(consumer).visit {
    type = ButtonType.submit
    body()
}

class Button(consumer: TagConsumer<*>): BUTTON(attributesMapOf("class", "button"), consumer) {
    fun primary() {
        classes += "is-primary"
    }

    fun success() {
        classes += "is-success"
    }

    fun danger() {
        classes += "is-danger"
    }

    fun small() {
        classes += "is-small"
    }
}

class ButtonGroup(consumer: TagConsumer<*>): DIV(attributesMapOf("class", "buttons is-grouped"), consumer) {
    fun right() {
        classes += "is-right"
    }
}
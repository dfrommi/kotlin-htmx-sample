package dfrommi.kotlin_htmx.adapter.ui.components.core

import kotlinx.html.FORM
import kotlinx.html.FlowContent
import kotlinx.html.InputType
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.input
import kotlinx.html.label

context(FlowContent)
fun form(body: FORM.()-> Unit) = form(classes = "control", block = body)

//not working with context. Failing with ClassCastException
fun FORM.textInput(
    label: String,
    name: String,
    value: String? = null,
    placeholder: String? = null,
    readonly: Boolean = false
) = div("field") {
        label("label") { +label }
        div("control") {
            input(classes = "input") {
                this.name = name
                this.type = InputType.text
                placeholder?.let { this.placeholder = it }
                value?.let { this.value = value }
                this.readonly = readonly
            }
        }
    }

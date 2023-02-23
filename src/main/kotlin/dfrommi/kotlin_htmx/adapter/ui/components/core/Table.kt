package dfrommi.kotlin_htmx.adapter.ui.components.core

import kotlinx.html.FlowContent
import kotlinx.html.HtmlTagMarker
import kotlinx.html.TABLE
import kotlinx.html.TBODY
import kotlinx.html.TD
import kotlinx.html.THEAD
import kotlinx.html.TR
import kotlinx.html.attributesMapOf
import kotlinx.html.classes
import kotlinx.html.table
import kotlinx.html.visit

context(FlowContent) fun table(body: TABLE.() -> Unit) = table("table", body)
context(FlowContent) @HtmlTagMarker fun thead(body: THEAD.() -> Unit) = THEAD(attributesMapOf(), consumer).visit(body)
context(FlowContent) @HtmlTagMarker fun tr(body: TR.() -> Unit) = TR(attributesMapOf(), consumer).visit(body)
context(FlowContent) @HtmlTagMarker fun tbody(body: TBODY.() -> Unit) = TBODY(attributesMapOf(), consumer).visit(body)
context(FlowContent) @HtmlTagMarker fun td(body: TD.() -> Unit) = TD(attributesMapOf(), consumer).visit(body)

context(TABLE) fun fullWidth() {
    classes += "is-fullwidth"
}

context(TR) val selectedClass get() = "is-selected"
context(TR) fun selected() {
    classes += selectedClass
}

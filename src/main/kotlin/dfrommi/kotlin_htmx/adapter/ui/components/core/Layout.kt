package dfrommi.kotlin_htmx.adapter.ui.components.core

import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.HtmlTagMarker
import kotlinx.html.attributesMapOf
import kotlinx.html.classes
import kotlinx.html.visit

context(FlowContent) @HtmlTagMarker fun div(id: String? = null, body: DIV.() -> Unit) = DIV(attributesMapOf("id", id), consumer).visit(body)

context(FlowContent) fun section(body: DIV.() -> Unit) = div {
    classes += "section"
    body()
}

context(FlowContent) fun container(body: DIV.() -> Unit) = div {
    classes += "container"
    body()
}

context(FlowContent) fun columns(body: DIV.() -> Unit) = div {
    classes += "columns"
    body()
}

context(FlowContent) fun column(body: DIV.() -> Unit) = div {
    classes += "column"
    body()
}

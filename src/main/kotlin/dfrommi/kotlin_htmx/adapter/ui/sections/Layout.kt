package dfrommi.kotlin_htmx.adapter.ui.sections

import dfrommi.kotlin_htmx.adapter.ui.components.NavBarItem
import dfrommi.kotlin_htmx.adapter.ui.components.core.container
import dfrommi.kotlin_htmx.adapter.ui.components.core.section
import dfrommi.kotlin_htmx.adapter.ui.components.navBar
import dfrommi.kotlin_htmx.adapter.ui.components.page
import kotlinx.html.FlowContent
import kotlinx.html.TagConsumer

context(TagConsumer<String>)
fun layout(bodyContent: FlowContent.() -> Unit) = page {
    navBar(
        NavBarItem(label = "Home", href = "/home"),
        NavBarItem(label = "Items", href = "/items")
    )

    section {
        container {
            bodyContent()
        }
    }
}

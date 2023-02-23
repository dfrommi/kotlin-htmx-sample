package dfrommi.kotlin_htmx.adapter.ui.components

import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.img
import kotlinx.html.nav
import kotlinx.html.role

data class NavBarItem(
    val label: String,
    val href: String,
    val isActive: Boolean = false
)

fun FlowContent.navBar(vararg items: NavBarItem) = nav("navbar is-dark") {
    role = "navigation"

    div("navbar-brand") {
        a(classes = "navbar-item") {
            href = "https://bulma.io"
            img { src = "https://bulma.io/images/bulma-logo-white.png" }
        }
    }

    div("navbar-menu") {
        div("navbar-start") {
            items.forEach {
                a(href = it.href, classes = "navbar-item is-tab") {
                    if (it.isActive) classes += "is-active"

                    +it.label
                }
            }
        }
    }
}

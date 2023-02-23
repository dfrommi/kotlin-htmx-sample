package dfrommi.kotlin_htmx.adapter.ui.sections.home

import dfrommi.kotlin_htmx.adapter.ui.components.content
import dfrommi.kotlin_htmx.adapter.ui.lib.html
import dfrommi.kotlin_htmx.adapter.ui.sections.layout
import io.jooby.Kooby
import kotlinx.html.TagConsumer
import kotlinx.html.div
import kotlinx.html.id
import kotlinx.html.p

class HomeRoutes() {
    val routes = Kooby {
        path("/home") {
            get("/") {
                html {
                    homePage()
                }
            }
        }
    }

    context(TagConsumer<String>)
    fun homePage() = layout {
        content("Hello World", "My first HTMX test") {
            div {
                id = "content-container"

                p {
                    +"""
               This is a test application to try out HTMX, HyperScript, Bulma in combination with Kotlin. 
            """.trimIndent()
                }
            }
        }
    }

}
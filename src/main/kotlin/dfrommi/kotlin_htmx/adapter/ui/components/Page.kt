package dfrommi.kotlin_htmx.adapter.ui.components

import kotlinx.html.BODY
import kotlinx.html.FlowContent
import kotlinx.html.TagConsumer
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.p
import kotlinx.html.script
import kotlinx.html.title
import kotlinx.html.unsafe

fun TagConsumer<String>.page(bodyContent: BODY.() -> Unit) = html {
    head {
        meta { charset = "utf-8" }
        meta {
            name = "viewport"
            content = "width=device-width, initial-scale=1"
        }
        title { +"My HTMX Test" }
        link {
            rel = "stylesheet"
            href = "https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css"
        }
        script {
            src = "https://unpkg.com/htmx.org@1.8.5"
        }
        script {
            src = "https://unpkg.com/hyperscript.org@0.9.7"
        }
        script {
            src = "https://unpkg.com/htmx.org/dist/ext/debug.js"
        }
        meta {
            name = "htmx-config"
            content = """{"useTemplateFragments" : "true" }"""
        }
        script {
            unsafe {
                +"""
                    htmx.defineExtension('path-vars', {
                        onEvent: function (name, evt) {
                            if (name === "htmx:configRequest") {
                               let sourceEventData = evt.detail?.triggeringEvent?.detail;
                               if (sourceEventData) {
                                   evt.detail.path = evt.detail.path.replace(/{event\.(\w+)}/g, function(_,k) {
                                         return sourceEventData[k];
                                   });
                               }
                            }
                        }
                    });
            """.trimIndent()
            }
        }
    }

    body {
        bodyContent()
        notificationContainer()
    }
}

fun FlowContent.content(title: String, subtitle: String? = null, content: FlowContent.() -> Unit) {
    h1("title") { +title }

    if (subtitle != null) {
        p("subtitle") { +subtitle }
    }

    content(this)
}
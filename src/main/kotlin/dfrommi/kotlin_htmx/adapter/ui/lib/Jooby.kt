package dfrommi.kotlin_htmx.adapter.ui.lib

import io.jooby.HandlerContext
import io.jooby.MediaType
import kotlinx.html.FlowContent
import kotlinx.html.TagConsumer
import kotlinx.html.body
import kotlinx.html.consumers.filter
import kotlinx.html.html
import kotlinx.html.stream.createHTML

fun HandlerContext.html(builder: TagConsumer<String>.() -> String): String {
    this.ctx.responseType = MediaType.html

    return createHTML().builder()
}

fun HandlerContext.htmlFragment(builder: FlowContent.() -> Unit): String {
    this.ctx.responseType = MediaType.html

    return createHTML()
        .filter { if (it.tagName in listOf("html", "body")) SKIP else PASS }
        .html {
            body {
                builder()
            }
        }
}

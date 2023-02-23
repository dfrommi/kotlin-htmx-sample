package dfrommi.kotlin_htmx

import dfrommi.kotlin_htmx.adapter.rest.ApiRouter
import dfrommi.kotlin_htmx.adapter.ui.UiApp
import dfrommi.kotlin_htmx.domain.service.ItemService
import io.jooby.Kooby
import io.jooby.ServerOptions
import io.jooby.utow.Utow

fun main() {
    val itemService = ItemService()

    val kooby = Kooby {
        get("/") {
            ctx.sendRedirect("/home")
        }

        mount(UiApp(itemService).routes)

        //Just to verify that UI and API can live side-by-side in the same app
        mount(ApiRouter(itemService))
    }

    val server = Utow().setOptions(ServerOptions().setPort(8081))
    server.start(kooby)
}

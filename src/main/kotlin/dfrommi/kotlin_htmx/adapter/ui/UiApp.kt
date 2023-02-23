package dfrommi.kotlin_htmx.adapter.ui

import dfrommi.kotlin_htmx.adapter.ui.sections.home.HomeRoutes
import dfrommi.kotlin_htmx.adapter.ui.sections.items.ItemPage
import dfrommi.kotlin_htmx.domain.service.ItemService
import io.jooby.Kooby
import io.jooby.MediaType

class UiApp(itemService: ItemService) {
    val routes = Kooby {
        before {
            this.ctx.responseType = MediaType.html
        }

        mount(HomeRoutes().routes)
        mount(ItemPage(itemService).routes)
    }
}
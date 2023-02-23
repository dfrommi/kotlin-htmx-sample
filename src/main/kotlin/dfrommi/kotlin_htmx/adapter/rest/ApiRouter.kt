package dfrommi.kotlin_htmx.adapter.rest

import dfrommi.kotlin_htmx.domain.service.ItemService
import io.jooby.Kooby
import io.jooby.MediaType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ApiRouter(itemService: ItemService): Kooby({
    before {
        ctx.responseType = MediaType.json
    }

    path("/api") {
        get("/items") {
            val dtos = itemService.getAll().map {
                ItemDTO(
                    id = it.id,
                    name = it.name
                )
            }.sortedBy { it.id }

            Json.encodeToString(dtos)
        }
    }
})

@Serializable
private data class ItemDTO(
    val id: Int,
    val name: String
)
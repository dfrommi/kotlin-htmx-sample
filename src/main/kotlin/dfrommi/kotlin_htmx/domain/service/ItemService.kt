package dfrommi.kotlin_htmx.domain.service

import dfrommi.kotlin_htmx.domain.model.Item
import java.util.concurrent.atomic.AtomicInteger

class ItemService {
    private val nextId = AtomicInteger(0)
    private val items = mutableSetOf(
        Item(id = nextId.incrementAndGet(), name = "The first item"),
        Item(id = nextId.incrementAndGet(), name = "The second item"),
    )

    fun getAll() = items

    fun getById(id: Int): Item? {
        return items.firstOrNull { it.id == id }
    }

    fun add(name: String): Item {
        val item = Item(
            id = nextId.incrementAndGet(),
            name = name
        )
        items.add(item)
        return item
    }

    fun delete(id: Int) {
        items.removeIf { it.id == id }
    }
}

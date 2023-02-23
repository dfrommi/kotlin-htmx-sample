package dfrommi.kotlin_htmx.adapter.ui.lib

import io.jooby.Context
import io.jooby.HandlerContext
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlTagMarker
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

fun HTMLTag.on(script: String) {
    if(attributes.containsKey("_"))
        attributes["_"] += "\n  on $script"
    else
        attributes["_"] = "on $script"
}

fun HTMLTag.hx(config: HxContext.() -> Unit) {
    val context = HxContext(this)
    config(context)
}

@HtmlTagMarker
class HxContext(parent: HTMLTag) {
    private val attr = parent.attributes

    fun get(location: String) {
        attr["hx-get"] = location
        pathVarsExtensionIfRequired(location)
    }

    fun post(location: String) {
        attr["hx-post"] = location
        pathVarsExtensionIfRequired(location)
    }

    fun put(location: String) {
        attr["hx-put"] = location
        pathVarsExtensionIfRequired(location)
    }

    fun delete(location: String) {
        attr["hx-delete"] = location
        pathVarsExtensionIfRequired(location)
    }

    fun trigger(vararg triggers: String) {
        val triggerValue = triggers.joinToString(", ")

        if (attr.containsKey("hx-trigger")) {
            attr["hx-trigger"] = attr["hx-trigger"] + ", $triggerValue"
        } else {
            attr["hx-trigger"] = triggerValue
        }
    }

    fun target(node: String) {
        attr["hx-target"] = node
    }

    fun swap(target: String) {
        attr["hx-swap"] = target
    }

    fun swapOOB(target: String) {
        attr["hx-swap-oob"] = target
    }

    fun onLoad() = trigger("load")
    fun onClick() = trigger("click")
    fun onEvent(event: String) = trigger(event)

    fun swapContentOf(target: String) {
        target(target)
        swap("innerHTML")
    }

    private fun pathVarsExtensionIfRequired(location: String) {
        if (location.contains("{")) {
            attr["hx-ext"] = "path-vars"
        }
    }
}

fun HandlerContext.raiseHxEvent(name: String) {
    raiseHxEvent(ctx, name, JsonNull)
}

fun HandlerContext.raiseHxEvent(name: String, value: String) {
    raiseHxEvent(ctx, name, JsonPrimitive(value))
}

fun HandlerContext.raiseHxEvent(name: String, value: Number) {
    raiseHxEvent(ctx, name, JsonPrimitive(value))
}

fun HandlerContext.raiseHxEvent(name: String, keyValue: Pair<String, Number>) {
    raiseHxEvent(ctx, name, JsonObject(mapOf(keyValue.first to JsonPrimitive(keyValue.second))))
}

private fun raiseHxEvent(ctx: Context, name: String, value: JsonElement) {
    val existingTrigger = ctx.getResponseHeader("HX-Trigger")

    val events = if (existingTrigger != null) {
        Json.decodeFromString<JsonObject>(existingTrigger).toMutableMap()
    } else {
        mutableMapOf()
    }

    events[name] = value

    ctx.setResponseHeader("HX-Trigger", Json.encodeToString(events))
}

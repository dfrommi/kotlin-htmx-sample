package dfrommi.kotlin_htmx.adapter.ui.components

import dfrommi.kotlin_htmx.adapter.ui.lib.hx
import dfrommi.kotlin_htmx.adapter.ui.lib.on
import kotlinx.html.FlowContent
import kotlinx.html.button
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.id

context(FlowContent)
fun notificationContainer() {
    div {
        id = "notification-container"
        classes += "ml-4 mb-4"
        attributes["style"] = """
            position: absolute;
            bottom: 0;
            left: 0;
        """
    }
}

context(FlowContent) fun showInfo(message: String) = showNotification(message)
context(FlowContent) fun showSuccess(message: String) = showNotification(message, "is-success")
context(FlowContent) fun showWarn(message: String) = showNotification(message, "is-warning")
context(FlowContent) fun showError(message: String) = showNotification(message, "is-danger")

context(FlowContent)
private fun showNotification(message: String, notificationClass: String? = null) {
    div {
        hx {
            swapOOB("afterbegin:#notification-container")
        }

        div("notification") {
            on("load wait 3s then transition opacity to 0 then remove me")

            notificationClass?.let { classes += it }

            button(classes = "delete") {
                on(
                    """click
                     set ntf to the closest parent <div/>
                     then transition the ntf's opacity to 0 
                     then remove the ntf
                """
                )
            }

            +message
        }
    }
}



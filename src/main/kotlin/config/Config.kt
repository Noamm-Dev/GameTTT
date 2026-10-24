package org.noamm.config

import org.noamm.logic.ai.GameMode
import org.noamm.models.CellState
import org.noamm.render.color.Theme
import java.util.*
import kotlin.enums.enumEntries
import kotlin.io.path.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Config: IConfig {
    private val file = Path("config.properties")
    private val properties = Properties().apply { if (file.exists()) file.inputStream().use(::load) }

    override var theme by enumProperty("theme", Theme.DEFAULT)
    override var mode by enumProperty("mode", GameMode.PVP)
    override var aiSide by enumProperty("aiSide", CellState.O)

    private inline fun <reified T: Enum<T>> enumProperty(key: String, default: T) = object: ReadWriteProperty<Any?, T> {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = properties.getProperty(key)?.let { name ->
            enumEntries<T>().find { it.name == name }
        } ?: default

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            properties.setProperty(key, value.name)
            save()
        }
    }

    private fun save() {
        file.createParentDirectories()
        file.outputStream().use { properties.store(it, "GameTTT Config") }
    }
}
package org.noamm.config

import org.noamm.render.color.Theme
import java.nio.file.Path
import java.util.*
import kotlin.io.path.*

class PropertiesConfig(private val file: Path = DEFAULT_FILE): IConfig {
    private val properties = Properties().apply {
        if (file.exists()) file.inputStream().use(::load)
    }

    override var theme: Theme
        get() = properties.getProperty(THEME_KEY)?.let { name -> Theme.entries.find { it.name == name } } ?: Theme.DEFAULT
        set(value) {
            properties.setProperty(THEME_KEY, value.name)
            save()
        }

    private fun save() {
        file.createParentDirectories()
        file.outputStream().use { properties.store(it, "GameTTT Config") }
    }

    companion object {
        private const val THEME_KEY = "theme"
        private val DEFAULT_FILE = Path("config.properties")
    }
}
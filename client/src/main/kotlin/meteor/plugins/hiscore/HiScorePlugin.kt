package meteor.plugins.hiscore

import compose.icons.Octicons
import compose.icons.octicons.Graph16
import eventbus.events.MenuOptionClicked
import meteor.config.ConfigManager
import meteor.menus.MenuManager
import meteor.plugins.Plugin
import meteor.plugins.PluginDescriptor
import meteor.ui.composables.PluginPanel
import meteor.ui.composables.preferences.pluginPanel
import meteor.ui.composables.preferences.uiColor
import meteor.ui.composables.toolbar.ToolbarButton
import meteor.ui.composables.toolbar.addButton
import meteor.ui.composables.toolbar.removeButton
import net.runelite.api.IconID
import net.runelite.api.MenuAction
import net.runelite.http.api.hiscore.HiscoreClient
import net.runelite.http.api.hiscore.HiscoreEndpoint
import net.runelite.http.api.hiscore.HiscoreResult
import okhttp3.OkHttpClient


@PluginDescriptor(name = "Hiscore", enabledByDefault = true, disabledOnStartup = false)
class HiScorePlugin : Plugin() {
    private val hiscoreClient = HiscoreClient(OkHttpClient())
    var panel: HiscorePanel? = null
    var config = configuration<HiscoreConfig>()


    private var notesButton = ToolbarButton(
        "HiScore",
        Octicons.Graph16,
        iconColor = uiColor,
        description = "Notes",
        onClick = {
            onClick()
        },
        bottom = false
    )



    override fun onMenuOptionClicked(event: MenuOptionClicked) {
        if (event.getMenuAction() == MenuAction.RUNELITE_PLAYER && event.getMenuOption()
                .equals("Lookup")
        ) {
            val player = event.menuEntry.player?.name
            ConfigManager.setConfiguration(
                "hiscore",
                "username",
                player!!
            )
        }
    }

    fun onClick() {
        if (panel!!.username != config.username()) {
            panel!!.username = config.username()
            HiscorePanel.result = hiscoreClient.lookup(config.username())
        }
        pluginPanel.value = panel
        togglePluginPanel(notesButton)
    }

    override fun onStart() {
        MenuManager.addPlayerMenuItem("Lookup")
        panel = HiscorePanel()
        addButton(notesButton)
    }

    override fun onStop() {
        removeButton(notesButton)
        panel = null
    }
}
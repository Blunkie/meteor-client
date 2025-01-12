package meteor.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24
import meteor.config.ConfigManager
import meteor.plugins.PluginDescriptor
import meteor.ui.composables.items.configItems
import meteor.ui.composables.items.sectionItems
import meteor.ui.composables.items.titleItems
import meteor.ui.composables.preferences.*
import meteor.ui.composables.ui.onPluginToggled


@Composable
fun configPanel() {
    if (lastPlugin.configuration != null) descriptor = ConfigManager.getConfigDescriptor(lastPlugin.configuration!!)!!
    var mod = Modifier.background(if (darkLightMode.value) darkThemeColors.background else lightThemeColors.background
    ).fillMaxHeight().width(375.dp)
    Column {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top,
                modifier = mod
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    configPanelHeader()
                    configs()
                }

            }
        }

    }
}
@Composable
fun configs(){
    LazyColumn(
        horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top,
        modifier = Modifier.width(375.dp).fillMaxHeight().background(background )
    ) {
        titleItems()
        sectionItems()
        configItems()
    }
}

@Composable
fun configPanelHeader() {


        Row(
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth().height(42.dp).background(surface,
                RoundedCornerShape(10.dp))
        ) {
            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start, modifier = Modifier.width(75.dp)) {
                IconButton( onClick = {
                    when {
                        configOpen.value -> configOpen.value = false
                        !pluginsOpen.value -> pluginsOpen.value = true
                    }
                }) {
                    Icon(
                        imageVector = Octicons.ChevronLeft24,
                        contentDescription = "Back to plugin page",
                        tint = uiColor,
                    )
                }
            }
            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start, modifier = Modifier.width(250.dp)) {
                MaterialTheme(colors = darkThemeColors) {
                    Text(
                        lastPlugin.javaClass.getDeclaredAnnotation(PluginDescriptor::class.java).name,
                        style = TextStyle(color = uiColor, fontSize = 24.sp),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.width(50.dp)
            ) {
                val switchState = remember { mutableStateOf(lastPlugin.shouldEnable()) }

                Switch(
                    switchState.value,
                    onPluginToggled(switchState, lastPlugin),
                    enabled = true,
                    modifier = Modifier.scale(0.75f),
                    colors = SwitchDefaults.colors(checkedThumbColor = uiColor, uncheckedThumbColor = Color.DarkGray, uncheckedTrackColor = Color.LightGray)
                )

            }



    }

}











package fhnw.emoba.freezerapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.emoba.EmobaApp
import fhnw.emoba.freezerapp.data.service.FreezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.FreezerUI

object FreezerApp : EmobaApp {
    private lateinit var model: FreezerModel

    override fun initialize(activity: ComponentActivity) {
        val service = FreezerService()
        model = FreezerModel(service)

        //remove in production; Only for demo: loads search "Eminem" and adds five first tracks to favoriteTracks
        model.searchString = "Eminem"
        model.getSearchAsync(forDemo = true)
    }

    @Composable
    override fun CreateUI() {
        FreezerUI(model)
    }
}

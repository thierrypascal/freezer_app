package fhnw.emoba.freezerapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.emoba.EmobaApp
import fhnw.emoba.freezerapp.data.service.FeezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.FreezerUI


object FreezerApp : EmobaApp {
    private lateinit var model: FreezerModel

    override fun initialize(activity: ComponentActivity) {
        val service = FeezerService()
        model = FreezerModel(service)
    }

    @Composable
    override fun CreateUI() {
        FreezerUI(model)
    }

}


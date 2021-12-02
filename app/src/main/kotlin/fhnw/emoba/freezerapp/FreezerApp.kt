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

        //TODO: remove; Only for testing
        model.searchString = "eminem"
        model.getSearchAsync()
    }

    @Composable
    override fun CreateUI() {
        FreezerUI(model)
    }

}


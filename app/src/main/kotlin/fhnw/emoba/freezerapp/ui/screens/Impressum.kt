package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.model.FreezerModel

@Composable
fun ImpressumScreen(model: FreezerModel) {
    val scaffoldState = rememberScaffoldState()

    with(model) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(model = model) },
            bottomBar = { BottomBar(model = model) },
            content = { Body(model = model) },
        )
    }
}

@Composable
private fun TopBar(model: FreezerModel) {
    StandardTopAppBar(model)
}

@Composable
private fun Body(model: FreezerModel) {
    val scrollState = rememberScrollState()

    with(model) {
        Column(
            content = {
                Text("== Emoba Assignment 1 - FreezerApp\n" +
                        "\n" +
                        "=== Bearbeitet von\n" +
                        "\n" +
                        "* Thierry Odermatt\n" +
                        "\n" +
                        "=== Anmerkung\n" +
                        "\n" +
                        "Dem AndroidManifest wurde zusätzlich folgende Regel hinzugefügt: android:configChanges=\"orientation|screenSize\". Ohne dieser Regel kann die Bildschirm Orientierung nicht sinnvoll verwendet werden.\n" +
                        "\n" +
                        "=== Abgabe\n" +
                        "\n" +
                        "* _Klasse 5iCa_, Montag, 6.12.2021, 8:00 Uhr\n" +
                        "* _Klasse 5iCb_, Mittwoch, 8.12.2021, 12:00 Uhr\n" +
                        "\n" +
                        "Die Abgabe erfolgt durch ein \"Push\" auf den Master-Branch Ihres GitHub-Repositories.\n" +
                        "\n" +
                        "=== Initiale Schritte\n" +
                        "\n" +
                        "* Tragen Sie ihren Namen unter \"Bearbeitet von\" ein.\n" +
                        "* Pushen Sie diese Änderung am besten sofort ins Git-Repository (z.B. via \"VCS -> Commit… -> Commit&Push\")\n" +
                        "\n" +
                        "=== Die Aufgabe: MusicPlayer für Deezer\n" +
                        "\n" +
                        "Entwerfen und implementieren Sie einen MusicPlayer für Deezer als native Android App.\n" +
                        "\n" +
                        "Es handelt sich um eine Einzelarbeit. Sich gegenseitig zu unterstützen ist durchaus erwünscht (am besten via Posting in Teams). Falls einzelne Funktionen gemeinsam entwickelt werden, ist dies im Code entsprechend zu markieren. Ansonsten wird es Plagiat gewertet.\n" +
                        "\n" +
                        "Die im Unterricht gezeigte App ist nur eine mögliche Umsetzung des gewünschten Funktionsumfangs. Sie können dieses UI übernehmen oder aber einen eigenen Entwurf, beispielsweise wie im Modul uidC erarbeitet, umsetzen.\n" +
                        "\n" +
                        "Anforderungen:\n" +
                        "\n" +
                        "* Gewünschte Funktionalität\n" +
                        "** Suche nach Album, nach Song, Anzeige der Radio-Stations und verwalten von Lieblings-Songs.\n" +
                        "*** Anzeige der Liste von gefundenen Songs\n" +
                        "*** Anzeige der Liste der gefundenen Alben und der Liste der Songs eines Albums\n" +
                        "*** Anzeige der Liste der Songs einer Radio-Station\n" +
                        "*** Anzeige der Liste von Lieblings-Songs\n" +
                        "** Abspielen, Pausieren von Songs einer Song-Liste. Nochmaliges Spielen eines Songs von Beginn an. Sprung zum nächsten Song.\n" +
                        "* Verwendung des Deezer-API\n" +
                        "** https://developers.deezer.com/api\n" +
                        "* Das UI ist komplett mit Jetpack Compose zu implementieren.\n" +
                        "** https://developer.android.com/jetpack/compose\n" +
                        "* Implementierungssprache für die gesamte Applikation ist Kotlin.\n" +
                        "* Die im Unterricht erarbeitete Grundstruktur einer Android-App ist anzuwenden.\n" +
                        "* Das Deezer-Logo muss an prominenter Stelle sichtbar sein (Vorgabe aus den Lizenzbedingungen)\n" +
                        "* Weitere Libraries als die, die im Unterricht verwendet wurden, sind nicht erlaubt.\n" +
                        "\n" +
                        "\n" +
                        "=== Bewertung\n" +
                        "\n" +
                        "Es können in diesem Assignment maximal 4 Punkte erreicht werden. Der Fokus liegt dabei, neben der Umsetzung der gewünschten Funktionalität, auf der Code-Qualität. Der Coolness-Faktor der Applikation wird über den Coolest App Award berücksichtigt.\n" +
                        "\n" +
                        "* 4 Punkte\n" +
                        "** Umsetzung der gesamten gewünschten Funktionalität und der oben genannten Grundanforderungen\n" +
                        "** Strukturierung der App in 3 Layer\n" +
                        "** UI Code ist klar strukturiert in unabhängige Screens und via Composable Functions.\n" +
                        "** TestCases für ViewModels, Data Klassen und Services\n" +
                        "* 1 Punkt\n" +
                        "** Ein Song wird via Deezer-API bezogen, der Titel des Songs und des Interpreten werden angezeigt. Der Song kann abgespielt und pausiert werden.\n" +
                        "* 0 Punkte\n" +
                        "** falls die Kriterien für einen Punkt nicht erfüllt sind ;-)\n" +
                        "** falls das Deezer-API nicht verwendet wird\n" +
                        "*** die Song-Informationen wie Titel und Preview-URL müssen via API (unter `https://api.deezer.com/`) bezogen werden\n" +
                        "** falls das Projekt nicht kompilierfähig ist\n" +
                        "** falls die App direkt beim Aufstarten abstürzt\n" +
                        "** für Plagiate")
            },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 60.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
        )
    }
}

@Composable
private fun BottomBar(model: FreezerModel) {
    StandardAppBottomBar(model)
}

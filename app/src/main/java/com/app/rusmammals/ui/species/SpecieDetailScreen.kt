package com.app.rusmammals.ui.species

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.rusmammals.R
import com.app.rusmammals.ui.components.MammalBackButton
import com.app.rusmammals.ui.components.MammalTopBar
import rusmammals.domain.model.SpecieModel

data class SpecieDetailScreen(val item: SpecieModel) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        SpecieDetailLayout(
            onBack = { navigator?.pop() },
            item = item
        )
    }
}

@Composable
private fun SpecieDetailLayout(
    onBack: () -> Unit,
    item: SpecieModel
) {
    val author: String = if (item.author != null) ", ${item.author}" else ""
    val year: String = if (item.descYear != null) ", ${item.descYear}" else ""
    val euRes = if (item.wEmmaFrame == true) R.string.is_eu_true else R.string.is_eu_false

    Scaffold(
        topBar = {
            MammalTopBar(
                title = item.rusname,
                navigationIcon = {
                    MammalBackButton { onBack.invoke() }
                }
            )
        }
    ) { paddings ->
        Column(
            modifier = Modifier
                .padding(top = paddings.calculateTopPadding())
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "${item.latName}${author}${year}",
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(text = stringResource(id = euRes))
            DetailRow(title = R.string.squad, value = item.animalOrder)
            DetailRow(title = R.string.family, value = item.animalFamily)
            DetailRow(title = R.string.genus, value = item.animalGenera)
            DetailRow(title = R.string.text_description, value = item.description)
            DetailRow(title = R.string.text_nomenclatur, value = item.nomen)
            DetailRow(title = R.string.text_agriculture, value = item.agricultural)
            DetailRow(title = R.string.text_epid, value = item.epid)
            DetailRow(title = R.string.text_rf_status, value = item.rfStatus)
            Spacer(modifier = Modifier.padding(bottom = paddings.calculateBottomPadding()))
        }
    }
}

@Composable
private fun DetailRow(
    title: Int,
    value: String?
) {
    if (value == null) return
    Column {
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = stringResource(title), fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}
package com.ziyiz.codapizza.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ziyiz.codapizza.model.Topping
import com.ziyiz.codapizza.model.ToppingPlacement

@Preview
@Composable
private fun ToppingCellPlacementNull() {
    ToppingCell(
        topping = Topping.Pepperroni,
        placement = null,
        onClickTopping = {}
    )
}

@Preview
@Composable
private fun ToppingCellPlacementLeft() {
    ToppingCell(
        topping = Topping.Pepperroni,
        placement = ToppingPlacement.Left,
        onClickTopping = {}
    )
}

@Composable
fun ToppingCell(
    topping: Topping,
    placement: ToppingPlacement?,
    modifier: Modifier = Modifier,
    onClickTopping: () -> Unit
) {
    Log.d("ToppingCell", "Called ToppingCell for $topping")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClickTopping() }
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Checkbox(
            checked = (placement != null),
            onCheckedChange = { onClickTopping() }
        )
        Column(
            modifier = Modifier.weight(1f, fill = true)
                .padding(start = 4.dp)
        ) {
            Text(
                text = stringResource(topping.toppingName),
                style = MaterialTheme.typography.body1
            )
            if (placement != null) {
                Text(
                    text = stringResource(placement.label),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
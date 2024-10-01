package com.lerp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lerp.domain.entities.UserUi


@Composable
fun ItemPersonSelection(
    item: UserUi,
    onClickReview: (UserUi) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()

            .padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            elevation = 8.dp,
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = item.name)
               //--- Text(text = item.statusCliente.toString())
                Spacer(modifier = Modifier.height(2.dp))

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onClickReview(item)
                                }
                                .height(32.dp)
                                .background(color = Color(0xFFF1EEF4))
                                .padding(8.dp)
                                .clip(RoundedCornerShape(24.dp)) // Ajusta el radio según sea necesario
                        ) {


                            Text(
                                text = "Editar",
                                color = Color.Blue
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                    }
                }
            }

        }


    }

}


@Preview
@Composable
fun ItemPersonSelectionPreview() {
    ItemPersonSelection(
        item = UserUi(
            statusCliente = false,
            statusEmpleado = false,
            name = "Luis Enrique",
            image = "Rojas Palomino"
        ),
        onClickReview = {}
    )

}
package com.lotto24.qacase.ui
import androidx.compose.ui.platform.testTag
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lotto24.qacase.domain.model.LottoDomain
import com.lotto24.qacase.ui.theme.LottoViewModel



object LottoTags {
    const val ITEM = "lotto_item"
    const val LOTTERY = "lotto_lottery"
    const val LAST_DRAW = "lotto_last_draw"
    const val NEXT_DRAW = "lotto_next_draw"
    const val NUMBERS_ROW = "lotto_numbers_row"
    const val NUMBER = "lotto_number"
    const val SUPER_NUMBER = "lotto_super_number"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LottoScreen(
    viewModel: LottoViewModel = hiltViewModel()
) {
    val lottoResults by viewModel.lottoResults.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(lottoResults) { lotto ->
            LottoResultItem(lotto = lotto)
        }
    }
}

@Composable
fun LottoResultItem(lotto: LottoDomain) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(LottoTags.ITEM),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = lotto.lottery.uppercase(),
                modifier = Modifier.testTag(LottoTags.LOTTERY),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Last Draw: ${DateFormatter.format(lotto.lastDrawDate)}",
                modifier = Modifier.testTag(LottoTags.LAST_DRAW),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .testTag(LottoTags.NUMBERS_ROW),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                lotto.numbers.forEach { number ->
                    NumberCircle(
                        number = number.toString(),
                        modifier = Modifier.testTag(LottoTags.NUMBER)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                lotto.superNumber.forEach { number ->
                    if (lotto.isEuroJackpot) {
                        NumberCircle(
                            number = number.toString(),
                            modifier = Modifier.testTag(LottoTags.NUMBER)
                        )
                    } else {
                        SuperNumberCircle(
                            number = number.toString(),
                            modifier = Modifier.testTag(LottoTags.SUPER_NUMBER)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Next Draw: ${DateFormatter.format(lotto.nextDrawDate)}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.End)
                    .testTag(LottoTags.NEXT_DRAW)
            )
        }
    }
}

@Composable
fun NumberCircle(number: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SuperNumberCircle(number: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
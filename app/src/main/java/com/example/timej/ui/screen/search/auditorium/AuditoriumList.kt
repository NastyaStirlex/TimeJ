package com.example.timej.ui.screen.search.auditorium

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.timej.R
import com.example.timej.calendar
import com.example.timej.data.dto.AuditoriumDto
import com.example.timej.utils.getCurrentWeekEnd
import com.example.timej.utils.getCurrentWeekStart
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.theme.Choice
import com.example.timej.ui.theme.SearchVariant
import com.example.timej.ui.theme.Shark
import java.util.*

@Composable
fun AuditoriumList(
    navController: NavController,
    mainViewModel: MainViewModel,
    state: State<String>,
    building: String,
    auditoriums: List<AuditoriumDto>
) {
    val auditoriumsTitles = auditoriums.map { it.title }

    var filteredAuditorium: List<String?>

    if (auditoriums.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 53.dp)
        ) {
            Text(
                text = stringResource(id = R.string.there_is_nothing),
                style = Choice,
                color = Shark
            )
            Text(
                text = stringResource(id = R.string.we_can_look_something),
                style = SearchVariant,
                color = Shark
            )
            Spacer(modifier = Modifier.height(12.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_not_found),
                contentDescription = null
            )
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(top = 10.dp, bottom = 20.dp, end = 5.dp, start = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val searchedText = state.value

            filteredAuditorium = if (searchedText.isEmpty()) {
                auditoriumsTitles
            } else {
                val resultList = mutableListOf<String>()
                for (auditorium in auditoriumsTitles) {
                    if (auditorium.lowercase(Locale.getDefault())
                            .startsWith(searchedText.lowercase(Locale.getDefault()))
                    ) {
                        resultList.add(auditorium)
                    }
                }
                resultList
            }
            items(filteredAuditorium) { filteredAuditorium ->
                if (filteredAuditorium != null) {
                    AuditoriumItem(
                        auditorium = filteredAuditorium,
                        onItemClick = { selectedAuditorium ->
                            (mainViewModel::getSchedule)(
                                getCurrentWeekStart(mCalendar = calendar),
                                getCurrentWeekEnd(mCalendar = calendar),
                                null,
                                null,
                                auditoriums.find { it.title == selectedAuditorium }?.id,
                                null
                            )

                            navController.navigate("schedule?building=$building&auditorium=$selectedAuditorium")
                        }
                    )
                }
            }
        }
    }
}
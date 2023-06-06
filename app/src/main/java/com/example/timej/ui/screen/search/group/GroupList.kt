package com.example.timej.ui.screen.search.group

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
import com.example.timej.data.dto.GroupDto
import com.example.timej.utils.getCurrentWeekEnd
import com.example.timej.utils.getCurrentWeekStart
import com.example.timej.ui.screen.MainViewModel
import com.example.timej.ui.theme.Choice
import com.example.timej.ui.theme.SearchVariant
import com.example.timej.ui.theme.Shark
import java.util.*

@Composable
fun GroupList(
    navController: NavController,
    mainViewModel: MainViewModel,
    state: State<String>,
    groups: List<GroupDto>
) {

    val groupsNumbers = groups.map { it.groupNumber.toString() }
    var filteredGroups: List<String>

    if (groups.isEmpty()) {
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val searchedText = state.value
            filteredGroups = if (searchedText.isEmpty()) {
                groupsNumbers
            } else {
                val resultList = mutableListOf<String>()
                for (group in groupsNumbers) {
                    if (group.lowercase(Locale.getDefault())
                            .startsWith(searchedText.lowercase(Locale.getDefault()))
                    ) {
                        resultList.add(group)
                    }
                }
                resultList
            }
            items(filteredGroups) { filteredGroup ->
                GroupItem(
                    group = filteredGroup,
                    onItemClick = { selectedGroup ->
                        (mainViewModel::getSchedule)(
                            getCurrentWeekStart(mCalendar = calendar),
                            getCurrentWeekEnd(mCalendar = calendar),
                            groups.find { it.groupNumber.toString() == selectedGroup }?.id,
                            null,
                            null,
                            null
                        )

                        navController.navigate("schedule?group=$selectedGroup")
                    }
                )
            }
        }
    }
}
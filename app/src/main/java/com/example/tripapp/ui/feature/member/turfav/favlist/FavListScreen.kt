package com.example.tripapp.ui.feature.member.turfav.favlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tripapp.R
import com.example.tripapp.ui.theme.white100
import com.example.tripapp.ui.theme.white400

@Composable
fun FavListRoute() {
//    val viewModel = FavListViewModel()
//    FavListScreen(
//        favList = viewModel,
//        onFavListClick = { fetchFav ->
//            navController.navigate("fav_list/${fetchFav.poiNo}")
//        }
//    )
}

@Preview
@Composable
fun PreviewFavListRoute() {
    FavListScreen(
        favList = FavListViewModel(),
    )
}

@Composable
fun FavListScreen(
    favList: FavListViewModel,
    onFavListClick: (fetchFav) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
//        val fetchFav by fetchFav.favListState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(white100),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
//                FavList(
//                    favList = favList,
//                    onFavListClick = onFavListClick
//                )
            }
        }
    }
}

@Composable
fun FavList(
    favList: List<fetchFav>,
    onFavListClick: (fetchFav) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        items(favList) { fetchFav -> // 根據資料清單生成項目
//            ListItem(
//                modifier = Modifier
//                    .clickable { onFavListClick(fetchFav) }, // 點擊傳遞資料
//                headlineContent = { Text(fetchFav.poiName) }, // 顯示收藏夾名稱
//                leadingContent = {
//                    Image(
//                        painter = painterResource(R.drawable.lets_icons__suitcase_light),
//                        contentDescription = fetchFav.poiName,
//                        modifier = Modifier
//                            .size(100.dp)
//                    )
//                }
//            )
//            HorizontalDivider(
//                modifier = Modifier,
//                color = white400
//            )
//        }
    }
}

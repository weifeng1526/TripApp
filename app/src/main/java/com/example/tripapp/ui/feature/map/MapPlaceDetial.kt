package com.example.tripapp.ui.feature.map


import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchByTextRequest


@Composable
fun PlaceTest() {
    var search by remember { mutableStateOf("") }
    // Specify the list of fields to return.
    val placeFields = listOf(
        Place.Field.ID,
        Place.Field.DISPLAY_NAME,
        Place.Field.FORMATTED_ADDRESS,
//        Place.Field.LOCATION,

    )
    var context = LocalContext.current
    var placesClient = Places.createClient(context)
    var placelist by remember { mutableStateOf(listOf<Place>()) }
    var tripPlace: List<PlaceSearch> by remember { mutableStateOf(listOf()) }
// // 設定可搜尋的範圍
    val southWest = LatLng(22.045858, 119.426224)
    val northEast = LatLng(25.161124, 122.343094)

    LaunchedEffect(tripPlace) {
        if (tripPlace.isNotEmpty()){
            fetchPlaceDetail(placesClient,tripPlace.first().id)
        }
    }


// Use the builder to create a SearchByTextRequest object.
    val searchByTextRequest = SearchByTextRequest.builder(search, placeFields)
        .setMaxResultCount(1)
        .setLocationRestriction(RectangularBounds.newInstance(southWest, northEast)).build()



    placesClient.searchByText(searchByTextRequest)
        .addOnSuccessListener { response ->
            placelist = response.getPlaces()
//            Log.d("tag_", "placelist: $placelist")
            tripPlace = placelist.map {
                PlaceSearch(
                    it.id.toString(),
                    it.displayName.toString(),
                    it.formattedAddress.toString(),
//                    it.location.toString(),

                )
            }

//            Log.d("tag_", "tripPlace: $tripPlace")
        }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            label = { Text(text = "Search") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray
            ),
            singleLine = true

        )


    }

}


fun fetchPlaceDetail(placesClient: PlacesClient, placeId: String) {


// Specify the fields to return.
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME,Place.Field.FORMATTED_ADDRESS,Place.Field.LOCATION)

// Construct a request object, passing the place ID and fields array.
    val request = FetchPlaceRequest.newInstance(placeId, placeFields)

    placesClient.fetchPlace(request)
        .addOnSuccessListener { response: FetchPlaceResponse ->
            val place = response.place
            Log.i("TAG", "Place found: ${place.name}")
            place.location.latitude
            place.location.longitude
        }.addOnFailureListener { exception: Exception ->
            if (exception is ApiException) {
                Log.e("TAG", "Place not found: ${exception.message}")
                val statusCode = exception.statusCode
            }
        }
}




//taipei station
//台北車站 朴子當歸鴨
//桃園車站

@Preview
@Composable
fun PlaceTestPreview() {
    PlaceTest()
}
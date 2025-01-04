package com.example.tripapp.ui.feature.map

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchByTextRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MapViewModel : ViewModel() {
    var placesClient: PlacesClient? = null

    private val _placeList = MutableStateFlow<List<Place>>(emptyList())
    var placeList = _placeList.asStateFlow()

    private val _tripPlaceList = MutableStateFlow<List<PlaceSearch>>(emptyList())
    var tripPlaceList = _tripPlaceList.asStateFlow()

    private val _selectedTripPlace = MutableStateFlow<SelectPlace?>(null)
    var selectedTripPlace = _selectedTripPlace.asStateFlow()

    fun initClient(context: Context) {
        placesClient = Places.createClient(context)
    }

    fun getPlaces(search: String, southWest: LatLng, northEast: LatLng) {

        val placeFields = listOf(
            Place.Field.ID,
            Place.Field.DISPLAY_NAME,
            Place.Field.FORMATTED_ADDRESS
        )
        // Use the builder to create a SearchByTextRequest object.
        val searchByTextRequest = SearchByTextRequest.builder(search, placeFields)
            .setMaxResultCount(1)
            .setLocationRestriction(RectangularBounds.newInstance(southWest, northEast)).build()
        placesClient?.searchByText(searchByTextRequest)
            ?.addOnSuccessListener { response ->
                _placeList.update {  response.places }
                val place = response.places.map {
                    PlaceSearch(
                        it.id.toString(),
                        it.displayName.toString(),
                        it.formattedAddress.toString(),
                    )
                }
                _tripPlaceList.update { place }
            }
    }

    fun fetchPlaceDetail( placeId: String) {
// Specify the fields to return.
        val placeFields = listOf(
            Place.Field.ID,
            Place.Field.DISPLAY_NAME,
            Place.Field.FORMATTED_ADDRESS,
            Place.Field.LOCATION,
            Place.Field.TYPES
        )

// Construct a request object, passing the place ID and fields array.
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)

        placesClient?.fetchPlace(request)
            ?.addOnSuccessListener { response: FetchPlaceResponse ->
                val place = response.place
                _selectedTripPlace.update {
                    SelectPlace(place.id,place.displayName,place.formattedAddress,place.location,place.types.toString())
                }
            }?.addOnFailureListener { exception: Exception ->
                if (exception is ApiException) {
                    Log.e("TAG", "Place not found: ${exception.message}")
                    val statusCode = exception.statusCode
                }
            }

    }
}
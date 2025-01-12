package com.example.tripapp.ui.feature.map

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.PhotoMetadata
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPhotoResponse
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.FetchResolvedPhotoUriRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchByTextRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.Locale

class MapViewModel : ViewModel() {
    private val tag = "tag_MapVM"
    private val _search = MutableStateFlow("")
    var search = _search.asStateFlow()

    var placesClient: PlacesClient? = null

    private val _checkSearch = MutableStateFlow<List<PlaceDetail>>(emptyList())
    var checkSearch = _checkSearch.asStateFlow()

    private val _placeList = MutableStateFlow<List<Place>>(emptyList())
    var placeList = _placeList.asStateFlow()

    private val _tripPlaceList = MutableStateFlow<List<PlaceSearch>>(emptyList())
    var tripPlaceList = _tripPlaceList.asStateFlow()

    private val _selectedTripPlace = MutableStateFlow<SelectPlace?>(null)
    var selectedTripPlace = _selectedTripPlace.asStateFlow()
//提示吐司
    private val _toastRequest = MutableStateFlow<String?>(null)
    var toastRequest = _toastRequest.asStateFlow()

    //照片相關
    private val _selectedTripPlaceImage = MutableStateFlow<Uri?>(null)
    val selectedTripPlaceImage = _selectedTripPlaceImage.asStateFlow()
    private val _selectedTripPlaceBit = MutableStateFlow<Bitmap?>(null)
    val selectedTripPlaceBit = _selectedTripPlaceBit.asStateFlow()

    fun onSearchChange(search: String) {
        _search.update { search }

    }


    fun initClient(context: Context) {
        placesClient = Places.createClient(context)
    }


    fun getPlaces(search: String = "朴子當歸鴨") {

        val placeFields = listOf(
            Place.Field.ID,
        )
        // Use the builder to create a SearchByTextRequest object.
        val searchByTextRequest = SearchByTextRequest.builder(search, placeFields)
            .setMaxResultCount(1)
            .setLocationRestriction(
                RectangularBounds.newInstance(
                    LatLng(22.045858, 119.426224),
                    LatLng(25.161124, 122.343094)
                )
            ).build()
        placesClient?.searchByText(searchByTextRequest)
            ?.addOnSuccessListener { response ->
                _placeList.update { response.places }
                val place = response.places.map { PlaceSearch(it.id.toString()) }
                _tripPlaceList.update { place }
                if (_tripPlaceList.value.isNotEmpty()) {
                    val placeId = placeList.value.getOrNull(0)?.id ?: return@addOnSuccessListener
                    fetchPlaceDetail(placeId = placeId)
                }
            }
    }

    fun fetchPlaceDetail(placeId: String) {
        //當重新搜尋會清除舊的圖
        _selectedTripPlaceImage.update { null }

        val locale = Locale("zh", "TW")
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale

        // Specify the fields to return.
        val placeFields = listOf(
            Place.Field.DISPLAY_NAME,
            Place.Field.FORMATTED_ADDRESS,
            Place.Field.LOCATION,
            Place.Field.TYPES,
            Place.Field.PHOTO_METADATAS
        )

// Construct a request object, passing the place ID and fields array.
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)

        placesClient?.fetchPlace(request)
            ?.addOnSuccessListener { response: FetchPlaceResponse ->
                val place = response.place
                if (place.types != null && place.displayName != null && place.formattedAddress != null && place.location != null && place.types != null && place.photoMetadatas != emptyList<PhotoMetadata?>()) {
                    getPhoto(place.photoMetadatas)

                    _selectedTripPlace.update {
                        SelectPlace(
                            place.displayName,
                            place.formattedAddress,
                            place.location,
                            place.types.toString(),
                            place.photoMetadatas
                        )
                    }
                } else {
                    _toastRequest.update { "無此資料" }
                }

            }?.addOnFailureListener { exception: Exception ->
                if (exception is ApiException) {
                    Log.e("TAG", "Place not found: ${exception.message}")
                    val statusCode = exception.statusCode
                }
            }

    }

    fun addPlace(
        schNo: Int = 0,               //跟行程拿編號
        poiAdd: String = "",           // 景點地址
        poiName: String = "",          // 景點名稱
        poiLng: BigDecimal = BigDecimal("0.0"),  // 經度
        poiLat: BigDecimal = BigDecimal("0.0"),  // 緯度
        poiLab: String = "",           // 景點標籤
        poiPic: String = "",           // 景點圖片路徑
        poiLike: Int = 1,
        dstDate: String = "",
        dstStart: String = "00:00:00",
        dstEnd: String = "00:00:00",
        dstInr: String = "00:00:00",

    ) {

        viewModelScope.launch {
            try {
                var response = MapRetrofit.api.selectPlace(
                    SelectPlaceDetail(
                        schNo = schNo,
                        poiName = poiName,
                        poiAdd = poiAdd,
                        poiLat = poiLat,
                        poiLng = poiLng,
                        poiLab = poiLab,
                        poiPic = poiPic,
                        poiLike = poiLike,
                        dstDate = dstDate,
                        dstStart = dstStart,
                        dstEnd = dstEnd,
                        dstInr = dstInr
                    )
                )
                Log.d(tag, "地點${poiName},地址${poiAdd},經緯度${poiLng},${poiLat},行程時間${dstDate},${dstStart},${dstEnd},${dstInr}")
                _checkSearch.update { response }
            } catch (e: Exception) {
                Log.e(tag, "error: ${e.message}")

            }


        }
    }

    fun getPhoto(photoMetadatas: List<PhotoMetadata?>) {

// 取得地點物件（此範例使用 fetchPlace()，但您也可以使用 findCurrentPlace()）


        val photoMetadata = photoMetadatas[0]

        // 取得歸屬文字和作者歸屬。


        // 建立 FetchResolvedPhotoUriRequest。
        if (photoMetadata != null) {
            val photoRequest = FetchResolvedPhotoUriRequest.newInstance(photoMetadata)
//                .setMaxWidth(500)
//                .setMaxHeight(300)
//                .build()

            // 請求相片 URI
            placesClient?.fetchResolvedPhotoUri(photoRequest)
                ?.addOnSuccessListener { fetchResolvedPhotoUriResponse ->
                    val uri = fetchResolvedPhotoUriResponse.uri
                    _selectedTripPlaceImage.update { uri }
                }
                ?.addOnFailureListener { exception ->
                    if (exception is ApiException) {
                        val apiException = exception as ApiException
                        Log.e("TAG", "找不到地點：" + exception.message)
                        val statusCode = apiException.statusCode
                    }
                }
        }


        // Get the photo metadata.


        // Create a FetchPhotoRequest.
        val photoRequest = FetchPhotoRequest.builder(photoMetadata)
            .setMaxWidth(500) // Optional.
            .setMaxHeight(300) // Optional.
            .build()
        placesClient?.fetchPhoto(photoRequest)
            ?.addOnSuccessListener { fetchPhotoResponse: FetchPhotoResponse ->
                val bitmap = fetchPhotoResponse.bitmap
                _selectedTripPlaceBit.update { bitmap }

            }?.addOnFailureListener { exception: Exception ->
                if (exception is ApiException) {
                    Log.e("TAG", "Place not found: " + exception.message)
                    val statusCode = exception.statusCode
                    TODO("Handle error with given status code.")
                }
            }
    }

    fun consumeToastRequest() {
        _toastRequest.update { null }
    }
}




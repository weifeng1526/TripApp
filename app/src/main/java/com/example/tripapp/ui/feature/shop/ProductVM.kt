package com.example.tripapp.ui.feature.shop

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.feature.shop.ShopApiService.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductVM : ViewModel() {
    private val tag = "tag_ProductVM"
    private val _productDetailState = MutableStateFlow(Product())
    val productDetailState: StateFlow<Product> = _productDetailState.asStateFlow()
    fun setDetailProduct(product: Product) {
        _productDetailState.value = product
    }

    // MutableStateFlow用來監控指定資料狀態，當資料一改變即可通知對應畫面更新
    // MutableStateFlow常與ViewModel搭配，可以讓UI元件在生命週期期間作出適當更新
    private val _productsState = MutableStateFlow(emptyList<Product>())
    val productsState: StateFlow<List<Product>> = _productsState.asStateFlow()

    init {
        viewModelScope.launch {
            _productsState.value = fetchProducts()
        }
    }

    /**
     * 載入測試需要資料
     * @return 產品資訊
     */
    private suspend fun fetchProducts(): List<Product> {
        try {
            val products = RetrofitInstance.api.fetchProducts()
            Log.d(tag, "products: $products")
            return products
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.addProduct(product)
                if (response.isSuccessful) {
                    // 取得後端回傳的包含 prodNo 的新商品
                    val newProduct = response.body()
                    Log.d("AddProduct", "新增成功: $newProduct")

                    newProduct?.let {
                        // 更新 productsState
                        _productsState.update { currentList ->
                            currentList + it
                        }
                    }
                } else {
                    Log.e("AddProduct", "新增失敗：${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("AddProduct", "異常錯誤：${e.message}")
            }
        }
    }
}

//        return listOf(
//            Product(
//                "0001",
//                "冬戀北海道",
//                3888.0,
//                R.drawable.aaa,
//                longDescription = "\n" + "*東京河津櫻花祭+熱海梅園梅花祭\n" +
//                        "*伊東溫泉區(兩晚)+富士五湖溫泉區(乙晚)\n" +
//                        "*十國峠軌道纜車+伊豆之國全景纜車(碧露台)"
//                 ),
//            Product("0002", "東京河津櫻花", 6888.0, R.drawable.bbb),
//        )
//    }

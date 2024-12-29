package com.example.tripapp.ui.feature.trip.plan.edit

import androidx.lifecycle.ViewModel
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel
import com.example.tripapp.ui.feature.trip.plan.restful.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


object PlanEditViewModel: ViewModel() {
    private var _dstState = MutableStateFlow(Destination())
    val dstState = _dstState.asStateFlow()

    private val _dstsState = MutableStateFlow(emptyList<Destination>())
    val dstsState: StateFlow<List<Destination>> = _dstsState.asStateFlow()

    fun addDst(dst: Destination) {
        _dstsState.update {
            val dsts = it.toMutableList()
            dsts.add(dst)
            dsts
        }
    }
    fun removeDst(dst: Destination) {
        _dstsState.update {
            val dsts = it.toMutableList()
            dsts.remove(dst)
            dsts
        }
    }

//    private fun fetchDestinations(): List<Destination> {
//        return listOf(
//            // 第一筆台北資料
//            Destination(
//                dstNo = 1,
//                schNo = 1,
//                poiNo = 1001,
//                dstName = "台北101",
//                dstAddr = "台北市信義區信義路五段7號",
//                dstPic = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A),
//                dstDep = "捷運台北101/世貿站步行約5分鐘",
//                dstDate = "2024-12-01", // 第一筆台北
//                dstStart = "09:00",
//                dstEnd = "12:00"
//            ),
//            // 第二筆台北資料
//            Destination(
//                dstNo = 2,
//                schNo = 1,
//                poiNo = 1002,
//                dstName = "士林夜市",
//                dstAddr = "台北市士林區大東路與文林路交叉口",
//                dstPic = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A),
//                dstDep = "捷運劍潭站步行約5分鐘",
//                dstDate = "2024-12-02", // 第二筆台北
//                dstStart = "18:00",
//                dstEnd = "21:00"
//            ),
//            // 第三筆台北資料
//            Destination(
//                dstNo = 5,
//                schNo = 1,
//                poiNo = 1005,
//                dstName = "龍山寺",
//                dstAddr = "台北市萬華區艋舺龍山寺",
//                dstPic = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A),
//                dstDep = "捷運龍山寺站步行約2分鐘",
//                dstDate = "2024-12-03", // 第三筆台北
//                dstStart = "10:00",
//                dstEnd = "13:00"
//            ),
//            // 第一筆台中資料
//            Destination(
//                dstNo = 3,
//                schNo = 2,
//                poiNo = 1003,
//                dstName = "高美濕地",
//                dstAddr = "台中市清水區高美路",
//                dstPic = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A),
//                dstDep = "台中高鐵站搭乘公車約1小時",
//                dstDate = "2024-12-04", // 第一筆台中
//                dstStart = "14:00",
//                dstEnd = "17:00"
//            ),
//            // 第二筆台中資料
//            Destination(
//                dstNo = 4,
//                schNo = 2,
//                poiNo = 1004,
//                dstName = "逢甲夜市",
//                dstAddr = "台中市西屯區文華路",
//                dstPic = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A),
//                dstDep = "搭乘公車至逢甲大學站",
//                dstDate = "2024-12-05", // 第二筆台中
//                dstStart = "19:00",
//                dstEnd = "22:00"
//            ),
//            // 第三筆台中資料
//            Destination(
//                dstNo = 6,
//                schNo = 2,
//                poiNo = 1006,
//                dstName = "慈濟大學",
//                dstAddr = "台中市北區學士路",
//                dstPic = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A),
//                dstDep = "搭乘公車至慈濟大學站",
//                dstDate = "2024-12-06", // 第三筆台中
//                dstStart = "15:00",
//                dstEnd = "18:00"
//            ),
//            // 第四筆台中資料
//            Destination(
//                dstNo = 7,
//                schNo = 2,
//                poiNo = 1007,
//                dstName = "草悟道",
//                dstAddr = "台中市西區民權路",
//                dstPic = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A),
//                dstDep = "搭乘公車至草悟道站",
//                dstDate = "2024-12-07", // 第四筆台中
//                dstStart = "10:00",
//                dstEnd = "13:00"
//            )
//        )
//    }
}
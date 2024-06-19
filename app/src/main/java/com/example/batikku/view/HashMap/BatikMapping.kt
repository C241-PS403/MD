package com.example.batikku.view.HashMap



class BatikMapping {

    private val batikMap: HashMap<String, Int> = hashMapOf()

    init {
        populateBatikMap()
    }

    private fun populateBatikMap() {
        batikMap["Motif Tujuh Rupa"] = 1
        batikMap["Motif Parang"] = 2
        batikMap["Motif Tambal"] = 3
        batikMap["Motif Mega Mendung"] = 4
        batikMap["Motif Truntum"] = 5
        batikMap["Motif Sekar Jagad"] = 6
        batikMap["Motif Keraton"] = 7
        batikMap["Motif Gentongan"] = 8
        batikMap["Motif Liong"] = 9
        batikMap["Motif Geblek Renteng"] = 10
    }

    fun getBatikId(batikName: String): Int? {
        return batikMap[batikName]
    }
}

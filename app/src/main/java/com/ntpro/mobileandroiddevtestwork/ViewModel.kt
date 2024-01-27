package com.ntpro.mobileandroiddevtestwork

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val server = Server()
    private val _deals = mutableListOf<Server.Deal>()
    private val _sortedDeals = MutableLiveData<List<Server.Deal>>()
    val sortedDeals: LiveData<List<Server.Deal>> get() = _sortedDeals
    var currentSortField = "Дата изменения сделки"
    private val maxDealsCount = 1000
    private var currentSortOrder = SortOrder.ASCENDING


    init {
        subscribeToDeals()
    }

    private fun subscribeToDeals() {
        server.subscribeToDeals { newDeals ->
            viewModelScope.launch(Dispatchers.Default){
                synchronized(_deals) {
                    _deals.addAll(newDeals)
                    sortDeals(currentSortField, currentSortOrder)
                }
            }
        }
    }

    /**
     * Сортировка сделок в зависимости от выбранного поля и
     * применение порядка сортировки (ASCENDING или DESCENDING)
     * @param sortField Выбранное поле для сортировки
     * @param currentSortOrder Текущий порядок сортировки (ASCENDING или DESCENDING)
     */
    private fun sortDeals(sortField: String, currentSortOrder: SortOrder) {
            val sortedDeals = when (sortField) {
                "Дата изменения сделки" -> _deals.sortedBy { it.timeStamp }
                "Имя инструмента" -> _deals.sortedBy { it.instrumentName }
                "Цена сделки" -> _deals.sortedBy { it.price }
                "Объем сделки" -> _deals.sortedBy { it.amount }
                "Сторона сделки" -> _deals.sortedBy { it.side }
                else -> _deals
            }
            val slicedDeals = if (currentSortOrder == SortOrder.ASCENDING) {
                sortedDeals.take(maxDealsCount)
            } else {
                sortedDeals.reversed().take(maxDealsCount)
            }
                _sortedDeals.postValue(slicedDeals)
    }

    /**
     * Обработка выбора поля сортировки
     * Изменение порядка сортировки при каждом выборе и вызов
     * функции сортировки с обновленными параметрами
     * @param sortField Выбранное поле для сортировки
     */
    fun handleSortSelection(sortField: String) {
        currentSortField = sortField
        if (currentSortOrder == SortOrder.ASCENDING) {
            currentSortOrder = SortOrder.DESCENDING
        } else {
            currentSortOrder = SortOrder.ASCENDING
        }
        sortDeals(currentSortField, currentSortOrder)
    }

    enum class SortOrder {
        ASCENDING,
        DESCENDING
    }
}
package com.ntpro.mobileandroiddevtestwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ntpro.mobileandroiddevtestwork.databinding.ActivityMainBinding
import com.ntpro.mobileandroiddevtestwork.databinding.BottomSheetSortBinding

class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterDeal: AdapterDeal
    private lateinit var viewModel: ViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        setUpListeners()
        setUpAdapter()
        observeViewModel()
        handleSortSelection(viewModel.currentSortField)
    }

    /**
     * Наблюдаем за изменениями в списке сделок и обновляем адаптер
     */
    private fun observeViewModel() {
        viewModel.sortedDeals.observe(this) { deals ->
            adapterDeal.submitList(deals)
        }
    }

    private fun setUpAdapter() {
        adapterDeal = AdapterDeal()
        binding.recyclerDeal.adapter = adapterDeal
        binding.recyclerDeal.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpListeners() {
        binding.buttomSort.setOnClickListener {
            dialogSort()
        }
        binding.icnDown.setOnClickListener {
            viewModel.handleSortSelection(viewModel.currentSortField)
        }
        binding.icnUp.setOnClickListener {
            viewModel.handleSortSelection(viewModel.currentSortField)
        }
    }

    /**
     * Отображение диалогового окна для выбора сортировки и
     * настройка  слушателей для элементов диалогового окна
     */
    private fun dialogSort() {
        val bindingSort: BottomSheetSortBinding =
            BottomSheetSortBinding.inflate(LayoutInflater.from(this))
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(bindingSort.root)
        dialog.show()

        bindingSort.rowDateOfChange.setOnClickListener { onSortFieldClicked("Дата изменения сделки", dialog) }
        bindingSort.rowName.setOnClickListener { onSortFieldClicked("Имя инструмента", dialog) }
        bindingSort.rowPrice.setOnClickListener { onSortFieldClicked("Цена сделки", dialog) }
        bindingSort.rowVolume.setOnClickListener { onSortFieldClicked("Объем сделки", dialog) }
        bindingSort.rowParty.setOnClickListener { onSortFieldClicked("Сторона сделки", dialog) }
    }

    /**
     * Обработка выбора поля сортировки
     * @param sortField Выбранное поле сортировки
     * @param dialog BottomSheetDialog для закрытия после выбора
     */
    private fun onSortFieldClicked(sortField: String, dialog: BottomSheetDialog) {
        viewModel.handleSortSelection(sortField)
        handleSortSelection(sortField)
        dialog.dismiss()
    }

    /**
     * Обновление текста кнопки с выбранным полем сортировки
     * @param sortField Выбранное поле сортировки
     */
    private fun handleSortSelection(sortField: String) {
        binding.buttomSort.text = getString(R.string.buttom_sort, sortField)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
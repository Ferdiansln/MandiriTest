package com.example.mandiritest.ui.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.RadioButton
import com.example.mandiritest.Constant.CATEGORIES
import com.example.mandiritest.R
import com.example.mandiritest.databinding.SourceFilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SourceFilterBottomSheet : BottomSheetDialogFragment() {

    companion object {
        private const val COUNTRY = "COUNTRY"
        private const val CATEGORY = "CATEGORY"
        private const val LANGUAGE = "LANGUAGE"

        fun createInstance(
            country: String?,
            category: String?,
            language: String?,
            listener: SourceFilterSubmitListener
        ): SourceFilterBottomSheet {
            val bt = SourceFilterBottomSheet()
            val bundle = Bundle()
            bundle.apply {
                putString(COUNTRY, country)
                putString(CATEGORY, category)
                putString(LANGUAGE, language)
            }
            bt.arguments = bundle
            bt.listener = listener
            return bt
        }
    }

    interface SourceFilterSubmitListener {
        fun onSubmit(country: String?, category: String?, language: String?)
    }

    var listener: SourceFilterSubmitListener? = null
    private lateinit var binding: SourceFilterBottomSheetBinding
    private var langValue: String? = null
    private var countryId: String? = null
    private var category: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SourceFilterBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryId = arguments?.getString(COUNTRY)
        category = arguments?.getString(CATEGORY)
        langValue = arguments?.getString(LANGUAGE)
        val countryList = resources.getStringArray(R.array.country_name)
        val countryIdList = resources.getStringArray(R.array.country_id)
        binding.apply {
            val countryAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                countryList
            )
            var selectedCountry = -1
            for (i in countryIdList.indices) {
                if (countryIdList[i] == countryId) {
                    selectedCountry = i
                    break
                }
            }
            countrySpinner.adapter = countryAdapter
            if (selectedCountry >= 0) countrySpinner.setSelection(selectedCountry)
            countrySpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    countryId = countryIdList[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
            val categoryAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                CATEGORIES
            )
            val selectedCategory = categoryAdapter.getPosition(category)
            categorySpinner.adapter = categoryAdapter
            if (selectedCategory >= 0) categorySpinner.setSelection(selectedCategory)
            categorySpinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    category = CATEGORIES[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
            submitBtn.setOnClickListener {
                listener?.onSubmit(countryId, category, langValue)
                dismiss()
            }
        }
        handleLanguageRadioButton()
    }

    private fun setRadioButtonCheckedChange(radioButton: RadioButton, value: String?) {
        if (value == langValue) radioButton.isChecked = true
        radioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                uncheckOthers(radioButton)
                langValue = value
            }
        }
    }

    private fun handleLanguageRadioButton() {
        binding.apply {
            setRadioButtonCheckedChange(arRb, "ar")
            setRadioButtonCheckedChange(deRb, "de")
            setRadioButtonCheckedChange(enRb, "en")
            setRadioButtonCheckedChange(esRb, "es")
            setRadioButtonCheckedChange(frRb, "fr")
            setRadioButtonCheckedChange(heRb, "he")
            setRadioButtonCheckedChange(itRb, "it")
            setRadioButtonCheckedChange(nlRb, "nl")
            setRadioButtonCheckedChange(noRb, "no")
            setRadioButtonCheckedChange(ptRb, "pt")
            setRadioButtonCheckedChange(ruRb, "ru")
            setRadioButtonCheckedChange(seRb, "se")
            setRadioButtonCheckedChange(zhRb, "zh")
            setRadioButtonCheckedChange(allRb, null)
        }
    }

    private fun uncheckOthers(chosenRb: RadioButton) {
        binding.apply {
            if (arRb != chosenRb) arRb.isChecked = false
            if (deRb != chosenRb) deRb.isChecked = false
            if (enRb != chosenRb) enRb.isChecked = false
            if (esRb != chosenRb) esRb.isChecked = false
            if (frRb != chosenRb) frRb.isChecked = false
            if (heRb != chosenRb) heRb.isChecked = false
            if (itRb != chosenRb) itRb.isChecked = false
            if (nlRb != chosenRb) nlRb.isChecked = false
            if (noRb != chosenRb) noRb.isChecked = false
            if (ptRb != chosenRb) ptRb.isChecked = false
            if (ruRb != chosenRb) ruRb.isChecked = false
            if (seRb != chosenRb) seRb.isChecked = false
            if (zhRb != chosenRb) zhRb.isChecked = false
            if (allRb != chosenRb) allRb.isChecked = false
        }
    }
}

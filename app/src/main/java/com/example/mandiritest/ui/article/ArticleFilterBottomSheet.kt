package com.example.mandiritest.ui.article

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.FragmentManager
import com.example.mandiritest.R
import com.example.mandiritest.databinding.ArticleFilterBottomSheetBinding
import com.example.mandiritest.utils.DateUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class ArticleFilterBottomSheet : BottomSheetDialogFragment() {

    companion object {
        private const val DATE_FROM = "DATE_FROM"
        private const val DATE_TO = "DATE_TO"
        private const val PARAM_Q = "PARAM_Q"
        private const val Q_IN_TITLE = "Q_IN_TITLE"
        private const val LANGUAGE = "LANGUAGE"
        private const val SOURCES = "SOURCES"

        fun createInstance(
            from: String?, to: String?, q: String?, qInTitle: String?, language: String?,
            sources: ArrayList<String>?, listener: ArticleFilterSubmitListener
        ): ArticleFilterBottomSheet {
            val bt = ArticleFilterBottomSheet()
            val bundle = Bundle()
            bundle.apply {
                putString(DATE_FROM, from)
                putString(DATE_TO, to)
                putString(PARAM_Q, q)
                putString(Q_IN_TITLE, qInTitle)
                putString(LANGUAGE, language)
                putStringArrayList(SOURCES, sources)
            }
            bt.arguments = bundle
            bt.listener = listener
            return bt
        }
    }

    interface ArticleFilterSubmitListener {
        fun onSubmit(
            from: String,
            to: String,
            q: String,
            qInTitle: String,
            language: String?,
            sources: String
        )
    }

    var listener: ArticleFilterSubmitListener? = null
    private lateinit var binding: ArticleFilterBottomSheetBinding
    private var langValue: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ArticleFilterBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val from = arguments?.getString(DATE_FROM)
        val to = arguments?.getString(DATE_TO)
        val paramQ = arguments?.getString(PARAM_Q)
        val qInTitle = arguments?.getString(Q_IN_TITLE)
        langValue = arguments?.getString(LANGUAGE)
        val sources = arguments?.getStringArrayList(SOURCES)
        binding.apply {
            fromEt.setText(from)
            toEt.setText(to)
            qEt.setText(paramQ)
            qInTitleEt.setText(qInTitle)
            if (!sources.isNullOrEmpty()) {
                val sb = StringBuilder()
                for (source in sources) {
                    sb.append("$source,")
                }
                sourcesEt.setText(sb.removeSuffix(","))
            }
            fromEt.setOnClickListener {
                val dp = initDatePicker(title = getString(R.string.from_hint),
                    setTimeMillis = if(fromEt.text.isNotBlank()) DateUtils.getTimestampFromStringYmd(fromEt.text.toString()) else null,
                    maxTimeMillis = if(toEt.text.isNotBlank()) DateUtils.getTimestampFromStringYmd(toEt.text.toString()) else System.currentTimeMillis(),
                    minTimeMillis = null,
                    onDateSetListener = { view, year, month, dayOfMonth ->
                        var m = (month+1).toString()
                        if(month<10) m = "0$m"
                        var d = dayOfMonth.toString()
                        if(dayOfMonth<10) d = "0$dayOfMonth"
                        val date = "$year-$m-$d"
                        fromEt.setText(date)
                    }
                )
                dp.show()
            }
            toEt.setOnClickListener {
                val dp = initDatePicker(title = getString(R.string.from_hint),
                    setTimeMillis = if(toEt.text.isNotBlank()) DateUtils.getTimestampFromStringYmd(toEt.text.toString()) else null,
                    maxTimeMillis = System.currentTimeMillis(),
                    minTimeMillis = if(fromEt.text.isNotBlank()) DateUtils.getTimestampFromStringYmd(fromEt.text.toString()) else null,
                    onDateSetListener = { view, year, month, dayOfMonth ->
                        var m = (month+1).toString()
                        if(month<10) m = "0$m"
                        var d = dayOfMonth.toString()
                        if(dayOfMonth<10) d = "0$dayOfMonth"
                        val date = "$year-$m-$d"
                        toEt.setText(date)
                    }
                )
                dp.show()
            }
            submitBtn.setOnClickListener {
                listener?.onSubmit(
                    fromEt.text.toString(),
                    toEt.text.toString(),
                    qEt.text.toString(),
                    qInTitleEt.text.toString(),
                    langValue,
                    sourcesEt.text.toString()
                )
                dismiss()
            }
        }
        handleLanguageRadioButton()
    }
    private fun initDatePicker(
        title: String? = null,
        setTimeMillis: Long? = null,
        maxTimeMillis: Long? = null,
        minTimeMillis: Long? = null,
        onDateSetListener: DatePickerDialog.OnDateSetListener
    ): DatePickerDialog {
        val calendar = Calendar.getInstance()
            .apply { setTimeMillis?.let { timeInMillis = it } }
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            onDateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.apply {
            setTitle(title)
            maxTimeMillis?.let { datePicker.maxDate = it }
            minTimeMillis?.let { datePicker.minDate = it }
        }
        return datePickerDialog
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

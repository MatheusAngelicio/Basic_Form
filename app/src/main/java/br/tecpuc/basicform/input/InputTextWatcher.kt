package br.tecpuc.basicform.input

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

class InputTextWatcher(private val textInputLayout: TextInputLayout) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        textInputLayout.isErrorEnabled = false
        textInputLayout.error = null
    }

    override fun afterTextChanged(s: Editable) {

    }
}
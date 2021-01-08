package br.tecpuc.basicform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.tecpuc.basicform.util.Event


class MainViewModel : ViewModel() {

    var person_name: String = ""
    var person_age: String = ""
    var person_phone: String = ""
    var person_rg: String = ""
    var person_cpf: String = ""
    var person_city: String = ""

    val formSaved = MutableLiveData<Event<Unit>>()

    var error = mutableListOf<FormError>()
    var isError = MutableLiveData(Event(false))


    fun save() {
        if (!correctField()) {
            isError.value = Event(true)
            return
        } else {
            formSaved.value = Event(Unit)
        }
    }

    private fun correctField(): Boolean {
        var valid = true
        error.clear()

        if (person_name.isEmpty()) {
            error.add(FormError.MISSING_NAME)
            valid = false
        }

        if (person_age.isEmpty()) {
            error.add(FormError.MISSING_AGE)
            valid = false
        }

        if (person_phone.isEmpty()) {
            error.add(FormError.MISSING_PHONE)
            valid = false
        }

        if (person_rg.isEmpty()) {
            error.add(FormError.MISSING_RG)
            valid = false
        }

        if (person_cpf.isEmpty()) {
            error.add(FormError.MISSING_CPF)
            valid = false
        }

        if (person_city.isEmpty()) {
            error.add(FormError.MISSING_CITY)
            valid = false
        }
        return valid
    }

}

enum class FormError {
    MISSING_NAME, MISSING_AGE, MISSING_PHONE, MISSING_RG, MISSING_CPF, MISSING_CITY,
    UNKNOWN
}
package br.tecpuc.basicform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import br.tecpuc.basicform.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        buttonSave.setOnClickListener { viewModel.save() }
    }

    private fun observeViewModel() {

        viewModel.formSaved.observe(this, {
            it?.getContentIfNotHandled()?.let {
                Toast.makeText(this, getString(R.string.save_form), Toast.LENGTH_LONG).show()
            }
        })

        viewModel.isError.observe(this, {
            it?.getContentIfNotHandled()?.let { isError ->

                if (!isError) return@observe

                viewModel.error.forEach { error -> handleError(error) }
            }
        })

    }


    private fun handleError(error: FormError) {
        when (error) {
            FormError.MISSING_NAME -> {
                inputNickName.error = getString(R.string.missing_name)
                editTextNickName.requestFocus()
            }

            FormError.MISSING_AGE -> {
                inputAge.error = getString(R.string.missing_age)
                editTextAge.requestFocus()
            }

            FormError.MISSING_PHONE -> {
                inputPhone.error = getString(R.string.missing_phone)
                editTextPhone.requestFocus()
            }

            FormError.MISSING_RG -> {
                inputRg.error = getString(R.string.missing_rg)
                editTextRg.requestFocus()
            }

            FormError.MISSING_CPF -> {
                inputCpf.error = getString(R.string.missing_cpf)
                editTextRg.requestFocus()
            }

            FormError.MISSING_CITY -> {
                inputCity.error = getString(R.string.missing_city)
                editTextCity.requestFocus()
            }

            else -> FormError.UNKNOWN
        }
    }

}
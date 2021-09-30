package br.tecpuc.basicform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import br.tecpuc.basicform.databinding.ActivityMainBinding
import br.tecpuc.basicform.input.InputTextWatcher

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.buttonSave.setOnClickListener { viewModel.save() }

        binding.editTextNickName.addTextChangedListener(InputTextWatcher(binding.inputNickName))
        binding.editTextAge.addTextChangedListener(InputTextWatcher(binding.inputAge))
        binding.editTextPhone.addTextChangedListener(InputTextWatcher(binding.inputPhone))
        binding.editTextRg.addTextChangedListener(InputTextWatcher(binding.inputRg))
        binding.editTextCpf.addTextChangedListener(InputTextWatcher(binding.inputCpf))
        binding.editTextCity.addTextChangedListener(InputTextWatcher(binding.inputCity))
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
                binding.inputNickName.error = getString(R.string.missing_name)
                binding.editTextNickName.requestFocus()
            }

            FormError.MISSING_AGE -> {
                binding.inputAge.error = getString(R.string.missing_age)
                binding.editTextAge.requestFocus()
            }

            FormError.MISSING_PHONE -> {
                binding.inputPhone.error = getString(R.string.missing_phone)
                binding.editTextPhone.requestFocus()
            }

            FormError.MISSING_RG -> {
                binding.inputRg.error = getString(R.string.missing_rg)
                binding.editTextRg.requestFocus()
            }

            FormError.MISSING_CPF -> {
                binding.inputCpf.error = getString(R.string.missing_cpf)
                binding.editTextCpf.requestFocus()
            }

            FormError.MISSING_CITY -> {
                binding.inputCity.error = getString(R.string.missing_city)
                binding.editTextCity.requestFocus()
            }

            else -> FormError.UNKNOWN
        }
    }

}
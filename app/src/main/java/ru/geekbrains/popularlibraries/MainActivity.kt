package ru.geekbrains.popularlibraries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.databinding.ActivityMainBinding
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_ONE
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_TWO
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_ZERO

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val presenter by moxyPresenter { CountersPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickListener()
    }

    private fun initClickListener() {

        with(binding) {
            btnNumber1.setOnClickListener {
                presenter.onCounterOneClick()
            }
            btnNumber2.setOnClickListener {
                presenter.onCounterTwoClick()
            }
            btnNumber3.setOnClickListener {
                presenter.onCounterThirdClick()
            }
        }
    }

    override fun setCounterOneText(counter: String) = with(binding) {
        tvText1.text = counter
    }

    override fun setCounterTwoText(counter: String) = with(binding) {
        tvText2.text = counter
    }

    override fun setCounterThirdText(counter: String) = with(binding) {
        tvText3.text = counter
    }
}
package com.example.testmovi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovi.R
import com.example.testmovi.databinding.ItemForecastDailyBinding
import com.example.testmovi.domain.model.ForecastDaily
import com.example.testmovi.domain.model.WeatherList
import com.example.testmovi.util.HumanUtil

class ForecastAdapter(
    var listData: List<WeatherList> = listOf(),
) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    fun notifyData(listData: List<WeatherList>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    override fun getItemCount(): Int = listData.count()

    class ViewHolder(
        private val parent: ViewGroup,
        private val viewBinding: ItemForecastDailyBinding = ItemForecastDailyBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(item: WeatherList) {
            val context = parent.context
            viewBinding.date.text = context.getString(R.string.date, HumanUtil.displayDate(item.dt))
            viewBinding.temperature.text = context.getString(R.string.temperature, item.temp?.eve?.toString())
            viewBinding.humidity.text = context.getString(R.string.humidity, "${item.humidity}")
            viewBinding.pressure.text = context.getString(R.string.pressure, item.pressure?.toString())
            viewBinding.description.text = context.getString(R.string.description, item.weather?.getOrNull(0)?.description)
        }
    }
}
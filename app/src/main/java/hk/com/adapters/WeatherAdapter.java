package hk.com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hk.com.entities.Weather;
import hk.com.entities.WeatherList;
import hk.com.hkweather.R;
import hk.com.interfacies.OnRVWeatherClickListener;

/**
 * Created by Hovhannisyan.Karo on 04.03.2018.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {


    private Context context;
    private LayoutInflater inflater;
    private List<WeatherList> weatherList;
    private OnRVWeatherClickListener onRVWeatherClickListener;

    public WeatherAdapter(Context context, List<WeatherList> weatherList, OnRVWeatherClickListener onRVWeatherClickListener) {
        this.context = context;
        this.weatherList = weatherList;
        this.onRVWeatherClickListener = onRVWeatherClickListener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_weather, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WeatherList currWeather = weatherList.get(position);
        holder.bind(currWeather);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_city_name)
        TextView tvCityName;
        @BindView(R.id.tv_temp)
        TextView tvTemp;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;
        Unbinder unbinder;
        WeatherList weatherList;

        public MyViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            llRoot.setOnClickListener(this);
        }

        public void bind(WeatherList currWeather) {
            weatherList = currWeather;
            tvCityName.setText(currWeather.getName());
            tvTemp.setText(String.valueOf(currWeather.getMain().getTemp()));
        }

        @Override
        public void onClick(View view) {
            onRVWeatherClickListener.onItemCLicked(view, weatherList);
        }
    }
}

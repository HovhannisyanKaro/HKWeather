package hk.com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hk.com.entities.WeatherList;
import hk.com.enums.Constants;
import hk.com.hkweather.R;
import hk.com.interfacies.OnRVWeatherClickListener;

/**
 * Created by Hovhannisyan.Karo on 05.03.2018.
 */

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<WeatherList> weatherList;
    private OnRVWeatherClickListener onRVWeatherClickListener;

    public DailyAdapter(Context context, List<WeatherList> weatherList, OnRVWeatherClickListener onRVWeatherClickListener) {
        this.context = context;
        this.weatherList = weatherList;
        this.onRVWeatherClickListener = onRVWeatherClickListener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DailyAdapter.MyViewHolder(inflater.inflate(R.layout.item_weather_daily, parent, false));
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        WeatherList weatherList;
        Unbinder unbinder;


        public MyViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);

        }

        public void bind(WeatherList currWeather) {
            weatherList = currWeather;
            tvDate.setText(new Date(currWeather.getDt()).toString() + " temp = " + weatherList.getMain().getTemp());
        }
    }
}

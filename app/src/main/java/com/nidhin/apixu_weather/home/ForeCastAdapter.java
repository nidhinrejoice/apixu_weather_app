package com.nidhin.apixu_weather.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nidhin.apixu_weather.R;
import com.nidhin.apixu_weather.data.model.Forecastday;
import com.nidhin.apixu_weather.util.Utils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ashwini Kumar.
 */

public class ForeCastAdapter extends RecyclerView.Adapter<ForeCastAdapter.SimpleViewHolder> {
    private final Context context;
    private List<Forecastday> list;
    private final Map<View, Map<Integer, View>> cache = new HashMap<View, Map<Integer, View>>();

    public ForeCastAdapter(Context context, List<Forecastday> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder viewHolder, int position) {

        Forecastday forecastday = list.get(position);
        try {
            viewHolder.tvDay.setText(Utils.getFormattedDate(forecastday.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.tvWeather.setText(forecastday.getDay().getAvgtempC() + "°C");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    private Forecastday getItem(int position) {
        return list.get(position);
    }


    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDay, tvWeather;

        public SimpleViewHolder(View itemView) {

            super(itemView);
            itemView.setTag(this);
            Map<Integer, View> itemMap = cache.get(itemView);

            if (itemMap == null) {
                itemMap = new HashMap<Integer, View>();
                tvDay = itemView.findViewById(R.id.day);
                tvWeather = itemView.findViewById(R.id.weather);
                itemMap.put(R.id.day, tvDay);
                itemMap.put(R.id.weather, tvWeather);
                cache.put(itemView, itemMap);
            } else {
                tvDay = (TextView) itemMap.get(R.id.day);
                tvWeather = (TextView) itemMap.get(R.id.weather);
            }
        }
    }

}
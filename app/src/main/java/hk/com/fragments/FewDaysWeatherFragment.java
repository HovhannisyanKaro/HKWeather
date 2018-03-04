package hk.com.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hk.com.activities.MainActivity;
import hk.com.adapters.DailyAdapter;
import hk.com.controllers.DBController;
import hk.com.controllers.DataController;
import hk.com.controllers.MyNetworkCallHandlerController;
import hk.com.controllers.NetworkController;
import hk.com.controllers.ViewController;
import hk.com.entities.WeatherList;
import hk.com.hkweather.R;
import hk.com.interfacies.OnRVWeatherClickListener;
import hk.com.utils.LogUtils;

public class FewDaysWeatherFragment extends Fragment implements OnRVWeatherClickListener {

    @BindView(R.id.iv_add)
    ImageView ivAdd;
    Unbinder unbinder;
    @BindView(R.id.rv_daily)
    RecyclerView rvDaily;
    private WeatherList weatherList;
    private DailyAdapter adapter;


    public static FewDaysWeatherFragment newInstance(WeatherList weatherList) {
        FewDaysWeatherFragment fewDaysWeatherFragment = new FewDaysWeatherFragment();
        fewDaysWeatherFragment.weatherList = weatherList;
        return fewDaysWeatherFragment;
    }

    public FewDaysWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_few_days_weather, container, false);
        LogUtils.d("FROMFragment " + weatherList.toString());
        getActivity().setTitle(weatherList.getName());
        unbinder = ButterKnife.bind(this, view);
        if (!DBController.getInstance().isExists(getActivity(), weatherList.getName())) {
            ivAdd.setVisibility(View.VISIBLE);
        }
        NetworkController.getNetworkController().getWeatherDaily(weatherList.getName(), getActivity(), MyNetworkCallHandlerController.getMyNetworkCallHandlerController());

        return view;
    }

    public void setAdapter() {
        adapter = new DailyAdapter(getActivity(), DataController.getDataController().getFewDaysData().getList(), this);
        rvDaily.setAdapter(adapter);
        rvDaily.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewController.getViewController().setFewDaysFragment(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mGetActivity().hideSearchView(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().setTitle(R.string.app_name);
        mGetActivity().hideSearchView(false);
    }

    private MainActivity mGetActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_add)
    public void onViewClicked() {
        try {
            DBController.saveToSqlite(getActivity(), weatherList);
            mGetActivity().setAdapter(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemCLicked(View view, WeatherList weatherList) {

    }
}

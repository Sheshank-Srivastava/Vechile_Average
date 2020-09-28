package io.github.sheshank_srivastava.vehicle_average;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AppManager extends Application {
    private static AppManager manager = null;

    private long mOldKiloMeter = 0;
    private int mCurrentKiloMeter = 0;
    private float mCurrentAverage = 0.0f;

    private float current_litre = 0.0f;

    private float total_litre = 0.0f;
    private long total_kilometer = 0;

    private SharedPreferences preferences;

    public float getCurrentAverage() {
        return mCurrentAverage;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = this;
        readBackUp();
    }

    public static AppManager getInstance() {
        return manager;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    private void readBackUp() {
        preferences = getSharedPreferences("calculator_average", Context.MODE_PRIVATE);
        mOldKiloMeter = preferences.getLong(Constants.OLD_KILOMETER, 0);
        total_litre = preferences.getFloat(Constants.TOTAL_FUEL, 0.0f);
        mCurrentAverage = preferences.getFloat(Constants.CURRENT_AVERAGE, 0.0f);
    }

    public void saveData(long kilometer, float fuel) {
        total_litre = preferences.getFloat(Constants.TOTAL_FUEL, 0.0f);
        float total_fuel = total_litre + fuel;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(Constants.NEW_KILOMETER, kilometer);
        editor.putFloat(Constants.TOTAL_FUEL, total_fuel);
        editor.apply();

        if (mOldKiloMeter == 0.0f){

        }
    }

}

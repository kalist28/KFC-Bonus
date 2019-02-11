package kalistdev.com.kfcbonus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

import kalistdev.com.kfcbonus.Actions.ActionList;
import kalistdev.com.kfcbonus.Coupons.CouponsList;
import kalistdev.com.kfcbonus.Menu.MenuFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    SharedPreferences sharedPreferences;
    private static final String KEY = "ad_key";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    PushEvents("menu_actions", "Actions", "Click actions menu");
                    OpenFragment(new ActionList());
                    return true;
                case R.id.navigation_dashboard:
                    PushEvents("menu_foods_menu", "Foods Menu", "Click foods menu");
                    OpenFragment(new MenuFragment());
                    return true;
                case R.id.navigation_notifications:
                    PushEvents("menu_coupons", "Coupons", "Click coupons menu");
                    OpenFragment(new CouponsList());
                    return true;
                case R.id.Settings:
                    OpenFragment(new Settings());
                    return true;
            }

            return false;
        }
    };

    private void PushEvents(String event, String nameEvent, String message){
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle eventBundle = new Bundle();
        eventBundle.putString(nameEvent, message);
        firebaseAnalytics.logEvent(event, eventBundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-3171253839600710~1742080443");

        sharedPreferences = getPreferences(MODE_PRIVATE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);;
        FrameLayout frameLayout;

        float scale = getResources().getDisplayMetrics().density;
        int top;
        int bottom;

        if (Objects.equals(sharedPreferences.getString(KEY, "false"), "false")){
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

            InterstitialAd interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdUnitId("ca-app-pub-3171253839600710/3260800381");
            interstitialAd.loadAd(new AdRequest.Builder().build());
            interstitialAd.show();
            top = (int) (50 * scale);
        }else{
            top = 0;
        }
        bottom = (int) (61 * scale);
        layoutParams.setMargins(0, top, 0, bottom);
        frameLayout = findViewById(R.id.FrameLayout);
        frameLayout.setLayoutParams(layoutParams);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        OpenFragment(new ActionList());
    }

    private void OpenFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout, fragment).commit();
    }
}

package kalistdev.com.kfcbonus.Coupons;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kalistdev.com.kfcbonus.DataBaseHelper;
import kalistdev.com.kfcbonus.Food.Drink;
import kalistdev.com.kfcbonus.Food.FastFood;
import kalistdev.com.kfcbonus.Food.Food;
import kalistdev.com.kfcbonus.Menu.RVAdapterFood;
import kalistdev.com.kfcbonus.R;

public class CouponInformationActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_information);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(" ");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Picasso.get().load(getIntent().getStringExtra("uriImage")).into((ImageView) findViewById(R.id.top_image));

        TextView textView = findViewById(R.id.infoText);
        textView.setText("Код купона: " + getIntent().getIntExtra("couponNumber", 1111)
                + " \nЦена: " + getIntent().getIntExtra("price", 50) + "\u20BD");
        int[] mas  = getCouponComponents(getIntent().getStringExtra("Components"));
        RecyclerView recyclerView = findViewById(R.id.rvcoupon);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        List<Food> foods = FillListCouponComponents(mas);
        RVAdapterFood rvAdapterFood = new RVAdapterFood(foods);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(rvAdapterFood);
    }

    private int[] getCouponComponents(String sqlInformation){
        String[] mas = sqlInformation.split(",");
        int[] components = new int[mas.length];
        for(int i = 0; i < mas.length; i++){
            components[i] = Integer.parseInt(mas[i]);
        }
        return components;
    }


    private List<Food> FillListCouponComponents(int[] mas) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getBaseContext());
        try {
            dataBaseHelper.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d("234234",   "   " + mas.length);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = null;
        List<Food> foods = new ArrayList<>();

        for (int m  = 0; m < mas.length; m++) {
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM fast_foods ", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if (cursor.getInt(0) == mas[m]) {
                    foods.add(new FastFood(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getInt(8)));
                }
                cursor.moveToNext();
            }

            cursor = sqLiteDatabase.rawQuery("SELECT * FROM drinks ", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if (cursor.getInt(0) == mas[m]) {
                    foods.add(new Drink(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5),
                            cursor.getString(6),
                            null,
                            cursor.getInt(7)));
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        return foods;
    }
}

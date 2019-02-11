package kalistdev.com.kfcbonus.Menu;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kalistdev.com.kfcbonus.DataBaseHelper;
import kalistdev.com.kfcbonus.Food.Drink;
import kalistdev.com.kfcbonus.Food.FastFood;
import kalistdev.com.kfcbonus.Food.Food;
import kalistdev.com.kfcbonus.R;

public class FoodsListActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = findViewById(R.id.rv_foods);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        try {
            dataBaseHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        RVAdapterFood rvAdapterMenu = new RVAdapterFood(FillArrayListFastFood());

        rv.hasFixedSize();
        rv.setLayoutManager(mGridLayoutManager);

        rv.setAdapter(rvAdapterMenu);

    }

    /**
     * Возвращает заполненый массив элементами Food.
     *
     * Этот метод получает через Intent название таблицы из DataBase и tag элемента Food.
     * Элементы сортируются, все элементы имеющие соответствующий tag загружаются в массив
     *
     * @return массив элементов Food
     */
    private List<Food> FillArrayListFastFood(){
        List<Food> foods = new ArrayList<>();
        String tableName = getIntent().getStringExtra("FoodTable");
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + tableName, null);
        cursor.moveToFirst();
        Log.d(getLocalClassName(), "Load " + getIntent().getStringExtra("FoodTag"));
        while (!cursor.isAfterLast()) {
            if (cursor.getString(1).equals(getIntent().getStringExtra("FoodTag"))) {
                switch (tableName){
                    case "fast_foods":{
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
                        break;
                    }
                    case "drinks":{
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
                }
            }
            cursor.moveToNext();
        }
        cursor.close();

        return foods;
    }
}


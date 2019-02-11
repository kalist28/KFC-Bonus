package kalistdev.com.kfcbonus.Menu;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import kalistdev.com.kfcbonus.R;

public class FoodInformationActivity extends AppCompatActivity {

    private TextView compositionFood;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        ImageView imageFood = findViewById(R.id.info_food_top_image);
        TextView nameFood = findViewById(R.id.info_food_name);
        TextView addNameFood = findViewById(R.id.info_food_add_name);
        TextView priceFood = findViewById(R.id.info_food_price);
        TextView descriptionFood = findViewById(R.id.info_food_description);
        compositionFood = findViewById(R.id.info_food_composition);
        TextView caloriesFood = findViewById(R.id.info_food_calories);
        checkBox = findViewById(R.id.info_food_checkBox);

        Picasso.get().load(getIntent().getStringExtra("UriImageFood")).into(imageFood);
        nameFood.setText(getIntent().getStringExtra("NameFood"));

        if (getIntent().getStringExtra("AddNameFood") != null ){
            addNameFood.setText(getIntent().getStringExtra("AddNameFood"));
        } else {
            addNameFood.setVisibility(View.GONE);
        }

        if (getIntent().getIntExtra("PriceFood", 0) != 0) {
            priceFood.setText("Цена: " + getIntent().getIntExtra("PriceFood", 0) + "\u20BD");
        } else {
            priceFood.setVisibility(View.GONE);
        }

        descriptionFood.setText(getIntent().getStringExtra("DescriptionFood"));

        if (getIntent().getStringExtra("CompositionFood") != null) {
            compositionFood.setText(getIntent().getStringExtra("CompositionFood"));
        } else {
            compositionFood.setVisibility(View.GONE);
            checkBox.setVisibility(View.GONE);
        }

        if (getIntent().getIntExtra("CaloriesFood", 0) != 0) {
            caloriesFood.setText(getIntent().getIntExtra("CaloriesFood", 0) + " ККАЛ");
        } else {
            caloriesFood.setVisibility(View.GONE);
        }

        onCheckboxClicked(null);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(" ");
    }

    /**
     * Если Checkbox активен то для @compositionFood устанавливается параметр VISIBLE иначе GONE.
     * @param view onClick Checkbox.
     */
    public void onCheckboxClicked(View view) {
        if (!checkBox.isChecked()){
            compositionFood.setVisibility(View.GONE);
        }else {
            compositionFood.setVisibility(View.VISIBLE);
        }

    }
}


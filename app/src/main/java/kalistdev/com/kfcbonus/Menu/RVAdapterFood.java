package kalistdev.com.kfcbonus.Menu;

import android.content.Intent;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kalistdev.com.kfcbonus.Food.Food;
import kalistdev.com.kfcbonus.R;


public class RVAdapterFood extends RecyclerView.Adapter<RVAdapterFood.MenuHolder> {

    private List<Food> foods;

    public RVAdapterFood(List<Food> foods) {
        this.foods = foods;
        Log.d("Count ^", " "+foods.size());
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food, viewGroup, false);
        return new MenuHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuHolder menuHolder, int i) {

        Picasso.get().load(foods.get(i).get_uri()).into(menuHolder.imageView);
        menuHolder.textView.setText(foods.get(i).get_name());
        if (!foods.get(i).get_addName().equals("острый")){
            menuHolder.hotImageView.setVisibility(View.GONE);
        }
        menuHolder.cardView.setOnClickListener(new View.OnClickListener() {
            int id = menuHolder.getAdapterPosition();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodInformationActivity.class);
                intent.putExtra("UriImageFood", foods.get(id).get_uri());
                intent.putExtra("NameFood", foods.get(id).get_name());
                intent.putExtra("AddNameFood", foods.get(id).get_addName());
                intent.putExtra("PriceFood", foods.get(id).get_price());
                intent.putExtra("DescriptionFood", foods.get(id).get_description());
                intent.putExtra("CompositionFood", foods.get(id).get_composition());
                intent.putExtra("CaloriesFood", foods.get(id).get_calories());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        ImageView hotImageView;
        TextView textView;
        public MenuHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_food);
            imageView = itemView.findViewById(R.id.image_food);
            hotImageView = itemView.findViewById(R.id.image_hot);
            textView = itemView.findViewById(R.id.name_food);
        }

    }
}

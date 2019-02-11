package kalistdev.com.kfcbonus.Menu;

import android.content.Intent;
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

import kalistdev.com.kfcbonus.Food.FoodCard;
import kalistdev.com.kfcbonus.R;


public class RVAdapterMenu extends RecyclerView.Adapter<RVAdapterMenu.MenuHolder> {

    private List<FoodCard> foods;

    public RVAdapterMenu(List<FoodCard> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food, viewGroup, false);
        return new MenuHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuHolder menuHolder, final int i) {

        Picasso.get().load(foods.get(i).getUriImage()).into(menuHolder.imageView);
        menuHolder.textView.setText(foods.get(i).getName());
        menuHolder.hotImageView.setVisibility(View.GONE);
        menuHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FoodsListActivity.class);
                intent.putExtra("FoodTag", foods.get(i).getTag());
                intent.putExtra("FoodTable", foods.get(i).getTable());
                Log.d("Intent", foods.get(i).getTag());
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

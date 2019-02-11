package kalistdev.com.kfcbonus.Actions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kalistdev.com.kfcbonus.InformationActivity;
import kalistdev.com.kfcbonus.R;

public class RVAdapterActions extends RecyclerView.Adapter<RVAdapterActions.CouponHolder>{
    private List<Action> actions;

    RVAdapterActions(List<Action> actions) {
        this.actions = actions;
    }


    @NonNull
    @Override
    public CouponHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.action, viewGroup, false);
        return new CouponHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CouponHolder ch, @SuppressLint("RecyclerView") final int i) {
        Picasso.get().load(actions.get(i).getUri()).into(ch.imageView);
        Log.d("dsfsd ", actions.get(i).getUri());
        final String Uri = actions.get(i).getUri();
        final String Title = actions.get(i).getName();
        final String TextInfo = actions.get(i).getInfo();
        ch.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actions.get(i).getInfo() == null) return;
                Intent intent = new Intent(ch.imageView.getContext(), InformationActivity.class);
                intent.putExtra("UriImage", Uri);
                intent.putExtra("Title", Title);
                intent.putExtra("TextInfo", TextInfo);
                ch.imageView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    class CouponHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        CouponHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv_action);
            imageView = itemView.findViewById(R.id.action_image);
        }
    }
}

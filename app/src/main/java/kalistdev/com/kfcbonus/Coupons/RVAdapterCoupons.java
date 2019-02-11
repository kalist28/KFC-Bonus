package kalistdev.com.kfcbonus.Coupons;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kalistdev.com.kfcbonus.R;

public class RVAdapterCoupons extends RecyclerView.Adapter<RVAdapterCoupons.CouponHolder>{
    private List<Coupon> coupons;

    RVAdapterCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }


    @NonNull
    @Override
    public CouponHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.coupon, viewGroup, false);
        return new CouponHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CouponHolder ch, @SuppressLint("RecyclerView") final int i) {
        Picasso.get().load(coupons.get(i).get_uri()).into(ch.couponImageView);
        ch.price.setText(coupons.get(i).get_price() + "\u20BD");
        ch.saved.setText(coupons.get(i).get_saved() + "\u20BD");
        ch.couponNumber.setText(coupons.get(i).get_couponNumber() + "");
        ch.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ch.cardView.getContext(), CouponInformationActivity.class);
                intent.putExtra("Components", coupons.get(i).get_info());
                intent.putExtra("uriImage", coupons.get(i).get_uri());
                intent.putExtra("couponNumber", coupons.get(i).get_couponNumber());
                intent.putExtra("price", coupons.get(i).get_price());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class CouponHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView couponImageView;
        TextView price;
        TextView saved;
        TextView couponNumber;
        CouponHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv);
            couponImageView = itemView.findViewById(R.id.coupon_image);
            price = itemView.findViewById(R.id.text_price);
            saved = itemView.findViewById(R.id.text_saved);
            couponNumber = itemView.findViewById(R.id.text_couponsNumber);
        }
    }
}

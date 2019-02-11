package kalistdev.com.kfcbonus.Coupons;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kalistdev.com.kfcbonus.Actions.Action;
import kalistdev.com.kfcbonus.DataBaseHelper;
import kalistdev.com.kfcbonus.R;

public class CouponsList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RVAdapterCoupons rvAdapterCoupons = new RVAdapterCoupons(FillArrayList());
        RecyclerView recyclerView = view.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvAdapterCoupons);
    }

    private List<Coupon> FillArrayList(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        try {
            dataBaseHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        List<Coupon> coupons = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM coupons ", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            coupons.add(new Coupon(
                    cursor.getInt(0),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(1),
                    cursor.getString(5),
                    cursor.getString(6)));
            cursor.moveToNext();
        }
        cursor.close();

        return coupons;
    }

}

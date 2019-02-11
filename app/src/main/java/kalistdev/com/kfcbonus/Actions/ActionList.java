package kalistdev.com.kfcbonus.Actions;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kalistdev.com.kfcbonus.DataBaseHelper;
import kalistdev.com.kfcbonus.R;

public class ActionList extends Fragment {

    SQLiteDatabase sqLiteDatabase;
    List<Action> actions = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_action_list, container, false);
    }

    private void FillArrayList(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM actions ", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            actions.add(new Action(cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

        try {
            dataBaseHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        FillArrayList();
        RVAdapterActions rvAdapterActions = new RVAdapterActions(actions);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvAdapterActions);

    }
}

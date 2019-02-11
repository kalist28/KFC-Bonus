package kalistdev.com.kfcbonus;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class Settings extends Fragment {
    Button checkText;
    EditText editText;
    TextView infoText1;
    TextView infoText2;
    SharedPreferences sharedPreferences;
    private static final String KEY = "ad_key";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkText = view.findViewById(R.id.checkText);
        editText = view.findViewById(R.id.EditText);
        infoText1 = view.findViewById(R.id.infotext1);
        infoText2 = view.findViewById(R.id.infotext2);
        sharedPreferences = Objects.requireNonNull(getActivity()).getPreferences(MODE_PRIVATE);

        if (Objects.equals(sharedPreferences.getString(KEY, "false"), "true")){
            checkText.setVisibility(View.GONE);
            editText.setVisibility(View.GONE);
            infoText1.setVisibility(View.GONE);
            infoText2.setVisibility(View.VISIBLE);
        }

        checkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getString = editText.getText().toString();

                if (getString.equals("A876D")){
                    @SuppressLint("CommitPrefEdits")
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY, "true");
                    editor.apply();
                    Toast.makeText(getContext(), "Перезапустите приложение что бы купон активировался.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Купон неверный!", Toast.LENGTH_SHORT).show();
                }
                editText.setText("");
            }
        });


    }
}

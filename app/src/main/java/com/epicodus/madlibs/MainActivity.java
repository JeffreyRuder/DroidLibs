package com.epicodus.madlibs;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mSubmitButton;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        String[] inputs = res.getStringArray(R.array.story_inputs_1);
        ArrayList<String> inputArrayList = new ArrayList<>(Arrays.asList(inputs));

        mListView = (ListView) findViewById(R.id.listView);
        UserInputAdapter adapter = new UserInputAdapter(this, inputArrayList);
        mListView.setAdapter(adapter);


        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText;
                boolean formIsValid = true;
                for (int i = 0; i < mListView.getCount(); i++) {
                    editText = (EditText) getViewByPosition(i, mListView).findViewById(R.id.user_input);
                    if (editText.length() == 0) {
                        formIsValid = false;
                    }
                }

                if (formIsValid) {
                    Toast.makeText(getApplicationContext(), "Successful form submission!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}

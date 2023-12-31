package com.example.myapplicationtwoactivities;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE =
            "com.example.android.myapplicationtwoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    private EditText mMessageEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mMessageEditText = (EditText) findViewById(R.id.editText_main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReplyHeadTextView = (TextView) findViewById(R.id.text_header_reply);
        mReplyTextView = (TextView) findViewById(R.id.text_message_reply);
    }

    public void launchSecondActivity(View view) {
        String message = mMessageEditText.getText().toString();

        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {

            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

}
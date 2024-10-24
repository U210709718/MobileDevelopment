package com.example.week5theo;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        ButtonLongClickListener listener = new ButtonLongClickListener();
        button.setOnLongClickListener(listener);


        //ButtonClickListener listener2 =
        button.setOnClickListener(new View.OnClickListener() {

    }
    public void buttonOnClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
    public void buttonOnClicked(View view){
        //first para is action
        Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.google.com"));
        //send a data from an activity to another activity


        launcher.launch(intent);
    }

    final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContract.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
                public onActivityResult(ActivityResult o) {
                    Intent data = o.getData();
                    String str = data.getStringExtra("msg");
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(str);

        }

    });

    public class ButtonLongClickListener implements View.onLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            return true;
        }

    }
}
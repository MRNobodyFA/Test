package com.ide;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText codeInput = findViewById(R.id.code_input);
        Button runButton = findViewById(R.id.run_button);
        TextView outputView = findViewById(R.id.output_view);

        runButton.setOnClickListener(v -> {
            String code = codeInput.getText().toString();
            String output = CodeAnalyzer.analyze(code);
            outputView.setText(output);
        });
    }
}

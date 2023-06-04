package com.swsoftware.synergytrains;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        int[][] numbers = new int[10][10];
        Random random = new Random();

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                numbers[x][y] = random.nextInt(250);
            }
        }

        for (int x = 0; x < 10; x++) {
            int[] line = numbers[x];
            numbers[x] = Arrays.stream(line).filter(
                    (number) -> number % 2 != 0 & number <= 100
            ).toArray();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int[] line : numbers) {
            for (int number : line) {
                stringBuilder.append(number).append(" ");
            }
            stringBuilder.append("\n");
        }

        textView.setText(stringBuilder);
    }
}
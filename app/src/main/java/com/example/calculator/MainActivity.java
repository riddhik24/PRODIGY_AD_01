package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    double firstNum;
    String operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button del = findViewById(R.id.del);
        Button ac = findViewById(R.id.ac);
        Button div = findViewById(R.id.division);
        Button add = findViewById(R.id.addition);
        Button sub = findViewById(R.id.subtraction);
        Button mul = findViewById(R.id.multiplication);
        Button equal = findViewById(R.id.equals);
        Button dot = findViewById(R.id.numdot);


        TextView screen = findViewById(R.id.screen);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setVisibility(View.VISIBLE);
                screen.setText("0");
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setVisibility(View.GONE);
            }
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for(Button b: nums){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!screen.getText().toString().equals("0")){
                        screen.setText(screen.getText().toString() + b.getText().toString());

                    }else{
                        screen.setText(b.getText().toString());
                    }
                }
            });
        }

        ArrayList<Button> opers = new ArrayList<>();
        opers.add(div);
        opers.add(add);
        opers.add(sub);
        opers.add(mul);

        for(Button b: opers){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firstNum= Double.parseDouble(screen.getText().toString());
                    operation= b.getText().toString();
                    screen.setText("0");
                }
            });
        }

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num= screen.getText().toString();
                if(num.length()>1) {
                    screen.setText(num.substring(0, num.length() - 1));
                }else if(num.length() ==1 && !num.equals("0")){
                    screen.setText("0");
                }
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!screen.getText().toString().contains(".")){
                    screen.setText(screen.getText().toString() + ".");
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(screen.getText().toString().equals("0")){
                    screen.setText("0");
                }else {

                    double secondNum = Double.parseDouble(screen.getText().toString());
                    double result = 0;

                    switch (operation) {
                        case "/":
                            result = firstNum / secondNum;
                            break;
                        case "X":
                            result = firstNum * secondNum;
                            break;
                        case "+":
                            result = firstNum + secondNum;
                            break;
                        case "-":
                            result = firstNum - secondNum;
                            break;
                        default:
                            screen.setText(screen.getText().toString());
                    }

                    screen.setText(String.valueOf(result));
                    firstNum = result;
                }
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNum=0;
                screen.setText("0");
            }
        });
    }
}
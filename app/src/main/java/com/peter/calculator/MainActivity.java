package com.peter.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_0;
    private Button button_plus;
    private Button button_minus;
    private Button button_multi;
    private Button button_div;
    private Button button_clear;
    private Button button_equals;
    private Button button_dot;
    private Button button_del;
    private Calculator calc;
    private TextView display;

    private AlertDialog setPin;
    private static String password;
    private EditText pinInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // temporary
        password = "5221";

        init();

        // TODO: turn on later
//        if(!readfile()){
//            setPassword();
//        }
    }

    private void setPassword() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflator = this.getLayoutInflater();

        final View view = inflator.inflate(R.layout.set_pin_dialog, null);

        builder.setTitle(R.string.setPin)
                .setView(view)
                .setPositiveButton(R.string.okayButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // overriden
                    }
                })
                .setNegativeButton(R.string.notNowButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, R.string.wontHaveAccess, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

        setPin = builder.create();

        setPin.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button okay = setPin.getButton(AlertDialog.BUTTON_POSITIVE);
                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pinInput = (EditText) view.findViewById(R.id.pinInput);
                        String pin = pinInput.getText().toString();
                        if (pin.length() != 4 || !checkNumeric(pin)) {
                            pinInput.setText("");
                            Toast.makeText(MainActivity.this, R.string.wrongFormat, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            password = pin;
                            setPin.dismiss();
                        }
                    }

                    private boolean checkNumeric(String pin) {
                        String full = "0123456789";
                        for (int i = 0; i < pin.length(); i++) {
                            if (!full.contains(pin.charAt(i) + "")) {
                                return false;
                            }
                        }

                        return true;
                    }
                });
            }
        });

        setPin.show();
    }

    private boolean readfile() {
        return false;
    }

    private void init() {
        button_0 = (Button)findViewById(R.id.button_0);
        button_1 = (Button)findViewById(R.id.button_1);
        button_2 = (Button)findViewById(R.id.button_2);
        button_3 = (Button)findViewById(R.id.button_3);
        button_4 = (Button)findViewById(R.id.button_4);
        button_5 = (Button)findViewById(R.id.button_5);
        button_6 = (Button)findViewById(R.id.button_6);
        button_7 = (Button)findViewById(R.id.button_7);
        button_8 = (Button)findViewById(R.id.button_8);
        button_9 = (Button)findViewById(R.id.button_9);
        button_plus = (Button)findViewById(R.id.button_plus);
        button_minus = (Button)findViewById(R.id.button_minus);
        button_multi = (Button)findViewById(R.id.button_multi);
        button_div = (Button)findViewById(R.id.button_div);
        button_dot = (Button)findViewById(R.id.button_dot);
        button_clear = (Button)findViewById(R.id.button_clear);
        button_equals = (Button)findViewById(R.id.button_equals);
        button_del = (Button)findViewById(R.id.button_del);
        display = (TextView)findViewById(R.id.display);

        calc = new Calculator("simple");

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returnValue = calc.click("multi", v);
                display.setText(returnValue);
                checkSize();
                if(returnValue.equals("multi"))
                    display.setText(R.string.multi);
            }
        });
        button_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returnValue = calc.click("div", v);
                display.setText(returnValue);
                checkSize();
                if(returnValue.equals("div"))
                    display.setText(R.string.div);
            }
        });
        button_dot.setOnClickListener(this);
        button_del.setOnClickListener(this);
        button_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (display.getText().equals("0") && !calc.doCalc){
                    // do nothing
                }
                else{
                    int buttonId = v.getId();
                    Button temp = (Button)findViewById(buttonId);

                    String returnValue = calc.click(temp.getText().toString(), v);
                    display.setText(returnValue);

                    checkSize();
                    if(returnValue.equals("multi"))
                        display.setText(R.string.multi);
                    if(returnValue.equals("div"))
                        display.setText(R.string.div);


                    if (display.getText().toString().charAt(display.getText().length() - 1) == '0'
                            && display.getText().toString().charAt(display.getText().length() - 2) == '.'){
                        display.setText(display.getText().toString().substring(0, display.getText().toString().indexOf('.')));
                    }
                }
            }
        });

        button_clear.setOnClickListener(this);


        button_clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Button temp = (Button)findViewById(v.getId());
                if (display.getText().equals(password)) {
                    Intent intent = new Intent(MainActivity.this, HiddenPage.class);
                    startActivity(intent);
                    String returnValue = calc.click(temp.getText().toString(), v);
                    display.setText(returnValue);
                } else {
                    // TODO: Empty everything
                }
                return true;
            }
        });
    }


    @Override
    public void onClick(View v){
        int buttonId = v.getId();
        Button temp = (Button)findViewById(buttonId);

        String returnValue = calc.click(temp.getText().toString(), v);
        display.setText(returnValue);

        checkSize();
        if(returnValue.equals("multi") || returnValue.equals("multi."))
            display.setText(R.string.multi);
        if(returnValue.equals("div") || returnValue.equals(("div.")))
            display.setText(R.string.div);;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void checkSize(){
        if(display.getText().length() > 18)
            display.setTextSize(33);
        else
            display.setTextSize(54);
    }
}
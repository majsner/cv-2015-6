package pef.kit.hotel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {

    // Navigace
    public void drawerClick(MenuItem item){
        new NavClickHandler(this).itemClick(item);
    }

    public void buttonClick(View view){
        new NavClickHandler(this).buttonClick(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //        dynamicky omezení 1 - 30
        Spinner days = (Spinner) findViewById(R.id.days);
        Integer[] daysArray = new Integer[30];
        for(int i = 0;i<=29;i++){
            daysArray[i] = (i+1);
        }
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, daysArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        days.setAdapter(spinnerArrayAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendButton(View view) {
        if (((EditText) findViewById(R.id.name)).getText() == null || ((EditText) findViewById(R.id.name)).getText().toString().equals("")) {
            showToast(getString(R.string.nameValidation));
            return;
        }
        if (!isValidPhone(((EditText) findViewById(R.id.phone)).getText())) {
            showToast(getString(R.string.phoneValidation));
            return;
        }
        if (!isValidEmail(((EditText)findViewById(R.id.email)).getText())) {
            showToast(getString(R.string.emailValidation));
            return;
        }
        ArrayList<String> data = new ArrayList<String>();
        data.add(0,((EditText) findViewById(R.id.name)).getText().toString());
        data.add(1,((EditText) findViewById(R.id.address)).getText().toString());
        data.add(2,((EditText) findViewById(R.id.phone)).getText().toString());
        data.add(2,((EditText) findViewById(R.id.email)).getText().toString());
        data.add(3,((EditText) findViewById(R.id.datum)).getText().toString());
        Boolean breakfast = ((CheckBox) findViewById(R.id.breakfast)).isChecked();
        int days = ((Spinner) findViewById(R.id.days)).getSelectedItemPosition();
        showToast("Formulář úspěšně odeslán");
    }

    public void resetButton(View view){
        loopLayoutReset((LinearLayout) findViewById(R.id.layout));
        Spinner spinner = (Spinner) findViewById(R.id.days);
        spinner.setSelection(0);
        ((CheckBox) findViewById(R.id.breakfast)).setChecked(false);
    }

    private void loopLayoutReset(LinearLayout ll){
        int count = ll.getChildCount();
        for (int i = 0;i <= count;i++){
            View v = ll.getChildAt(i);
            if (v instanceof LinearLayout) loopLayoutReset((LinearLayout) v);
            if (v != null && v instanceof EditText) {
                clearText(v);
            }
        }
    }

    private void clearText(View v){
        ((TextView) v).setText("");
    }

    public void showToast(CharSequence text) {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public final static boolean isValidPhone(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.PHONE.matcher(target).matches();
        }
    }

}

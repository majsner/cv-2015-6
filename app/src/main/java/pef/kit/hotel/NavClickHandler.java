package pef.kit.hotel;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by jan on 02.11.15.
 */
public class NavClickHandler {

    private Activity activity;

    public NavClickHandler(Activity activity){
        this.activity = activity;
    }

    public void itemClick(MenuItem item){
        runActivity(item.getItemId());
    }
    public void buttonClick(View view){
        runActivity(view.getId());
    }

    private void runActivity (int id) {
        Intent intent = null;
        switch (id){
            case R.id.button_booking:
            case R.id.drawer_booking:
                intent = new Intent(activity,BookingActivity.class);
                break;
            case R.id.button_services:
            case R.id.drawer_services:
                intent = new Intent(activity,ServicesActivity.class);
                break;
            case R.id.button_rooms:
            case R.id.drawer_rooms:
                intent = new Intent(activity,RoomsActivity.class);
                break;
            case R.id.button_about:
            case R.id.drawer_about:
                intent = new Intent(activity,AboutActivity.class);
                break;
            case R.id.button_contact:
            case R.id.drawer_contact:
                intent = new Intent(activity,ContactActivity.class);
                break;
        }
        if (intent != null) activity.startActivity(intent);
    }



}

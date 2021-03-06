package org.wysaid.filtervideocamera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {

    public static final String LOG_TAG = "wysaid";

    public static class DemoClassDescription {
        String activityName;
        String title;
//        DemoClassDescription() {
//            activityName = null;
//            title = null;
//        }

        DemoClassDescription(String _name, String _title) {
            activityName = _name;
            title = _title;
        }
    }

    private static final DemoClassDescription mDemos[] = new DemoClassDescription[]{
            new DemoClassDescription("CameraDemoActivity", "cameraDemo"),
            new DemoClassDescription("VideoPlayerActivity", "playerDemo")
    };

    public class MyButton extends Button implements View.OnClickListener {
        private DemoClassDescription mDemo;
        public void setDemo(DemoClassDescription demo) {
            mDemo = demo;
            setText(mDemo.title);
            setOnClickListener(this);
        }
        MyButton(Context context) {
            super(context);
        }

        @Override
        public void onClick(final View v) {
            Log.i(LOG_TAG, String.format("%s is clicked!", mDemo.title));
            Class cls = null;
            try {
                cls = Class.forName("org.wysaid.filtervideocamera." + mDemo.activityName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return ;
            }

            try {
                if(cls != null)
                    startActivity(new Intent(MainActivity.this, cls));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mLayout = (LinearLayout) findViewById(R.id.buttonLayout);

        for(DemoClassDescription demo : mDemos) {
            MyButton btn = new MyButton(this);
            btn.setDemo(demo);
            mLayout.addView(btn);
        }
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        System.exit(0);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        System.exit(0);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

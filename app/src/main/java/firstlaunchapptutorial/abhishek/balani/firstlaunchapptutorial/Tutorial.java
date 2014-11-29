package firstlaunchapptutorial.abhishek.balani.firstlaunchapptutorial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Abhishek Balani www.oddblogger.com on 11/28/2014.
 */
public class Tutorial extends FragmentActivity {

    private TutorialPagerAdapter mPagerAdapter;
    private List<Fragment> fragments;
    private ViewPager mPager;
    private static final int NUM_PAGES = 4; //The number of total pages you have
    Button prev, next;
    int flag =0;
    Context TutorialContext = this;

    private boolean isFirstTime() {
        /***
         * Checks that application runs first time and write flag at SharedPreferences
         * @return true if 1st time
         */

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (!isFirstTime()) {


            Intent mainnew1 = new Intent(TutorialContext, MainActivity.class);
            startActivity(mainnew1);
            finish();


        }


        setContentView(R.layout.tutorial_layout);


        mPager = (ViewPager) findViewById(R.id.pagerTutorial);
        mPagerAdapter = new TutorialPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
       // Toast.makeText(this, mPager.getCurrentItem(), Toast.LENGTH_SHORT);
        prev = (Button) findViewById(R.id.prev_button);
         next = (Button) findViewById(R.id.next_button);

        prev.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem( mPager.getCurrentItem() + 1);
                prev.setVisibility(View.VISIBLE);
                next.setText("Next");
                if(flag==1){
                    Intent mainnew = new Intent(TutorialContext, MainActivity.class); //Change MainActivity.class with the activity you would like to launch at the end
                    startActivity(mainnew);
                    finish();
                }
                if(mPager.getCurrentItem()==NUM_PAGES-1){

                    next.setText("Finish");
                    flag=1;
                } else {
                    flag=0;
                }



            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem( mPager.getCurrentItem() - 1);

                if(mPager.getCurrentItem()==0)
                {
                    prev.setVisibility(View.INVISIBLE);
                    next.setText("Start");
                }

                if(mPager.getCurrentItem()<NUM_PAGES-1){

                    next.setText("Next");
                    flag=0;
                }

            }
        });

    }

    private class TutorialPagerAdapter extends FragmentPagerAdapter {
        public TutorialPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - First tutorial page
                    return TutorialPage1.init(position);
                case 1: // Fragment # 1 - Second tutorial page
                    return TutorialPage2.init(position);
                case 2:
                    return TutorialPage3.init(position);
                default:
                    return TutorialPage4.init(position);
            }
        }
    }
    private Toast toast;
    private long lastBackPressTime = 0;
    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 3000) {
            toast = Toast.makeText(this, "Press back again to exit!", Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }

            //If user exits before completing tutorial, set RanBefore false again.
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            boolean ranBefore = preferences.getBoolean("RanBefore", false);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", false);
            editor.commit();
            super.onBackPressed();
        }
    }
}
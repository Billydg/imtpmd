package com.hsleiden.imtpmd.studiebarometer;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private PieChart mChart;
    public static final int MAX_ECTS = 60;
    public static int currentEcts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Studiebarometer");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public  void voortgang (View view){
        mViewPager.setCurrentItem(1);
    }




    /**
     * Overzicht fragment containing het overzicht.
     */
    public static class OverzichtFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private ViewPager mViewPager;

        private PieChart mChart;
        public static final int MAX_ECTS = 60;
        public static int currentEcts = 0;

        String student = new String("Pepijn van Hengel");
        String studentnummer = new String("s1088219");

        public OverzichtFragment() {

        }


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static OverzichtFragment newInstance(int sectionNumber) {
            OverzichtFragment fragment = new OverzichtFragment();
            return fragment;



        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.content_overzicht, container, false);

            super.onCreate(savedInstanceState);
            //  Log.d("tot hier", "en niet verder");

            TextView naamStudent = (TextView)rootView.findViewById(R.id.textnaam);
            naamStudent.setText("Naam: " + student);

            TextView nummerStudent = (TextView)rootView.findViewById(R.id.textstudent);
            nummerStudent.setText("Studentnummer: " + studentnummer);

            mChart = (PieChart) rootView.findViewById(R.id.chart);
            mChart.setDescription("");
            mChart.setTouchEnabled(false);
            mChart.setDrawSliceText(true);
            mChart.getLegend().setEnabled(false);
            mChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
            mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

            setData(0);

            return rootView;
        }

        private void setData(int aantal) {
            currentEcts = aantal;
            ArrayList<Entry> yValues = new ArrayList<>();
            ArrayList<String> xValues = new ArrayList<>();

            yValues.add(new Entry(aantal, 0));
            xValues.add("Behaalde ECTS");

            yValues.add(new Entry(60 - currentEcts, 1));
            xValues.add("Resterende ECTS");

            //  http://www.materialui.co/colors
            ArrayList<Integer> colors = new ArrayList<>();
            if (currentEcts <10) {
                colors.add(Color.rgb(244,81,30));
            } else if (currentEcts < 40){
                colors.add(Color.rgb(235,0,0));
            } else if  (currentEcts < 50) {
                colors.add(Color.rgb(253,216,53));
            } else {
                colors.add(Color.rgb(67,160,71));
            }
            colors.add(Color.rgb(255,0,0));

            PieDataSet dataSet = new PieDataSet(yValues, "ECTS");
            dataSet.setColors(colors);

            PieData data = new PieData(xValues, dataSet);
            mChart.setData(data); // bind dataset aan chart.
            mChart.invalidate();  // Aanroepen van een redraw
            Log.d("aantal =", ""+currentEcts);
        }

    }





    /**
     * Fysiek fragment containing fysiek.
     */
    public static class VoortgangFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public VoortgangFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static VoortgangFragment newInstance(int sectionNumber) {
            VoortgangFragment fragment = new VoortgangFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.content_voortgang, container, false);
        }
    }


    /**
     * Mula Method fragment containing the Mula Method.
     */
    public static class VakkenFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public VakkenFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static VakkenFragment newInstance(int sectionNumber) {
            VakkenFragment fragment = new VakkenFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.content_vakken, container, false);
        }
    }




















    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return OverzichtFragment.newInstance(position + 1);
            }
            else if (position == 1) {
                return VoortgangFragment.newInstance(position + 1);
            }
            return VakkenFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "OVERZICHT";
                case 1:
                    return "VOORTGANG";
                case 2:
                    return "VAKKEN";
            }
            return null;
        }
    }
}

package terence.marathon;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;


public class Marathon extends ActionBarActivity implements ActionBar.TabListener {

    Fragment_Paper_Adapter FPA;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final ActionBar actionbar = getSupportActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        FPA = new Fragment_Paper_Adapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(FPA);

        for(int i=0; i<FPA.getCount();i++){
            actionbar.addTab(actionbar.newTab().setText(FPA.getPageTitle(i)).setTabListener(this));
        }

          //add tab
//        actionbar.addTab(actionbar.newTab().setText(R.string.tab1).setTabListener(this));
//        actionbar.addTab(actionbar.newTab().setText(R.string.tab2).setTabListener(this));
    }

    @Override
    public void onTabReselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabUnselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }




    public class Fragment_Paper_Adapter extends FragmentPagerAdapter{
        public Fragment_Paper_Adapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position){
            return PlaceholderFragment.newInstance(position + 1);
        }


        @Override
        public CharSequence getPageTitle(int position){
            Locale l = Locale.getDefault();
            switch (position){
                case 0:
                    return getString(R.string.tab1).toUpperCase(l);
                case 1:
                    return getString(R.string.tab2).toUpperCase(l);
                case 2:
                    return getString(R.string.tab3).toUpperCase(l);
                case 3:
                    return getString(R.string.tab4).toUpperCase(l);
                case 4:
                    return getString(R.string.tab5).toUpperCase(l);
                case 5:
                    return getString(R.string.tab6).toUpperCase(l);
                case 6:
                    return getString(R.string.tab7).toUpperCase(l);

            }
            return null;
        }
        @Override
        public int getCount(){
            return 7;
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_test1, container, false);

            return rootView;
        }
    }




    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_marathon, menu);
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
    */
}

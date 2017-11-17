package anulom.executioner5.com3.anulom;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import anulom.executioner5.com3.anulom.database.DBOperation;
import anulom.executioner5.com3.anulom.fragment.CompletedDetails;
import anulom.executioner5.com3.anulom.fragment.NewDetails;
import anulom.executioner5.com3.anulom.fragment.OlderDetails;
import anulom.executioner5.com3.anulom.fragment.TodayDetails;

public class SearchActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DBOperation database;

    String Date, ID;

    private SharedPreferences usermail;
    private String username2 = "", password2 = "";
    public static NextActivity thisnext = null;
    DBOperation db;

    int i = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);
        NextActivity.pDialog.show();
        System.out.println("Entered SearchActivity");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SearchActivity.this, "SEARCH Completed", Toast.LENGTH_LONG).show();


            }
        });
        database = new DBOperation(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("  Anulom");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        usermail = PreferenceManager.getDefaultSharedPreferences(this);
        username2 = usermail.getString("username", "");
        password2 = usermail.getString("password", "");


        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, tabLayout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setText(tab.getText());
            tab.setCustomView(relativeLayout);

            tab.select();
        }
        viewPager.setCurrentItem(0, false);

    }


    private void setupViewPager(ViewPager viewPager) {


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        if (GenericMethods.todayyy.equals("true")) {
//            System.out.println("from viewpager today");

            adapter.addFragment(new TodayDetails(), "Today");
//                viewPager.setAdapter(adapter);
        }

        if (GenericMethods.olderrr.equals("true")) {
//            System.out.println("from viewpager older");
//                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new OlderDetails(), "Older");
//                viewPager.setAdapter(adapter);
        }
        if (GenericMethods.completeddd.equals("true")) {
//            System.out.println("from viewpager comp");
//                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new CompletedDetails(), "Completed");
//                viewPager.setAdapter(adapter);
        }
        if (GenericMethods.newwww.equals("true")) {
//            System.out.println("from viewpager new");
//                ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(new NewDetails(), "New");

        }

//        System.out.println("View count" + (i + 1));
        viewPager.setAdapter(adapter);
        NextActivity.pDialog.dismiss();


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void search_back(View v) {
        GenericMethods.value = "false";
        GenericMethods.todayyy = "false";
        GenericMethods.olderrr = "false";
        GenericMethods.completeddd = "false";
        GenericMethods.newwww = "false";

        Intent i = new Intent(SearchActivity.this, NextActivity.class);
        startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

        // handleIntent(getIntent());
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        MenuItem refresh = menu.findItem(R.id.action_refresh);
        refresh.setVisible(false);

//        MenuItem weekend=menu.findItem(R.id.weekend);
//        weekend.setVisible(false);

        MenuItem sh = menu.findItem(R.id.search);
        sh.setVisible(false);


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        menu.findItem(R.id.login_id).setTitle(username2);
        return super.onCreateOptionsMenu(menu);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {


            case R.id.abt:
                Intent i3 = new Intent(getApplicationContext(), about.class);
                startActivity(i3);
                return true;


            case R.id.log_out:

                usermail = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = usermail.edit();
                editor.remove("username");
                editor.remove("password");
                editor.apply();

                GenericMethods.ctr2 = 0;
                GenericMethods.ctr3 = 0;
                Intent i = new Intent(getApplicationContext(), Login.class);

                startActivity(i);


        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {
        Intent startMain = new Intent(SearchActivity.this, NextActivity.class);
        startActivity(startMain);
    }


}

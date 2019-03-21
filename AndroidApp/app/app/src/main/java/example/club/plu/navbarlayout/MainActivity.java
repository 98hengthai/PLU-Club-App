package example.club.plu.navbarlayout;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.MenuItemCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


//import example.club.plu.navbarlayout.Services.MyService;
//import example.club.plu.navbarlayout.utils.NetworkHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{//, LoaderManager.LoaderCallbacks<String> {

    /********************  Server connection - Delete comment to undo
    private static final String URL = "http://560057.youcanlearnit.net/services/json/itemsfeed.php";
    private TextView tv;

    private BroadcastReceiver mBCR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(MyService.MY_SERVICE_PAYLOAD);
            tv.append(message + "\n");
        }
    };

  */

    private DrawerLayout drawer;
    private boolean networkOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /********************  Server connection - Delete comment to undo
        tv = (TextView) findViewById(R.id.description);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mBCR,
                    new IntentFilter(MyService.MY_SERVICE_MESSAGE));
        */

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //init drawer
        //The "Open..." and "Close..." are for Hearing Support.
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //init navigation view and its fragments
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //showing and closing navigation drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //set HomeFragment on start up
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

//        //Button Stuff
//        final Button button = (Button) findViewById(R.id.button2);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendEmail(v);
//
//            }
//        });
//        final Button but = (Button) findViewById(R.id.button);
//
//        but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                runClickHandler(v);
//
//            }
//        });

/********************  Server connection - Delete comment to undo
        networkOk = NetworkHelper.hasNetworkAccess(this);
        tv.append("Network ok " + networkOk);
 */

    }//onCreate()

/********************  Server connection - Delete comment to undo
    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mBCR);
    }
*/

    private void sendEmail(View v) {

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { getString(R.string.email) });
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacting About " + getString(R.string.club_example));
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(Intent.createChooser(emailIntent, ""));
    }

    /********************  Server connection - Delete comment to undo
    public void runClickHandler(View view){
        //TextView t = (TextView) findViewById(R.id.description);


        //t.append("Hello");

        //getSupportLoaderManager().restartLoader(0, null, this).forceLoad();
        Intent intent = new Intent(this, MyService.class);
        intent.setData(Uri.parse(URL));
        startService(intent);
        startService(intent);
        startService(intent);
    }
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_clubs:
                ClubsFragment clubsFragment = new ClubsFragment();
                //init the array of all clubs name
                clubsFragment.initializeClubsArray(new String[0]);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,clubsFragment).commit();
                break;
            case R.id.nav_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventsFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    /**@NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        tv.append("creating loader\n");
        return new MyTaskLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String o) {
        tv.append("Loader finished, returned: " + o + "\n");
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    //TODO
    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        String[] choices = {"Club 1", "Club 2", "Home"};
        ArrayAdapter<String> a;
        a = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_spinner_item, choices);
        spinner.setAdapter(a); // set the adapter to provide layout of rows and content
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        return true;
    }*/

    /**private class MyAsyncTask extends AsyncTask<String, String, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            for (String str: strings) {
                publishProgress(str);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv.append(values[0] + "\n");
        }
    }
    private static class MyTaskLoader extends AsyncTaskLoader<String>{

        public MyTaskLoader(@NonNull Context context) {


            super(context);
        }

        @Override
        public String loadInBackground() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from the loader";
        }

        @Override
        public void deliverResult(@Nullable String data) {
            data += ", delivered";
            super.deliverResult(data);
        }
    }**/
}

package example.club.plu.navbarlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{//, LoaderManager.LoaderCallbacks<String> {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    }//onCreate()

    private void sendEmail(View v) {

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { getString(R.string.email) });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacting About " + getString(R.string.club_example));
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, ""));
    }

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

<<<<<<< HEAD
=======
                ClubsFragment clubsFragment = new ClubsFragment();
>>>>>>> cb198f05b50fced86c34d98e20dd279187f8455d
                //init the array of all clubs name
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClubsFragment()).commit();
                break;
            case R.id.nav_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventsFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

<<<<<<< HEAD



=======
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



>>>>>>> cb198f05b50fced86c34d98e20dd279187f8455d
}

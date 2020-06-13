package com.f.goretails;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class SearchActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private WebView webView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Bundle bundle = getIntent().getExtras();
        String query = bundle.getString("query");
        getSupportActionBar().setTitle(query);
        String str = query.replace(" ","-");
        webView.loadUrl("http://goretails.in/product/"+str+"/");

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(SearchActivity.this, HomeActivity.class));
                        break;
                    case R.id.account:
                        startActivity(new Intent(SearchActivity.this, AccountActivity.class));
                        break;
                    case R.id.shop:
                        startActivity(new Intent(SearchActivity.this, ShopActivity.class));
                        break;
                    case R.id.fruits:
                        startActivity(new Intent(SearchActivity.this, FruitsActivity.class));
                        break;
                    case R.id.vegetables:
                        startActivity(new Intent(SearchActivity.this, VegetablesActivity.class));
                        break;
                    case R.id.grocery:
                        startActivity(new Intent(SearchActivity.this, GroceryActivity.class));
                        break;
                    case R.id.dairy_products:
                        startActivity(new Intent(SearchActivity.this, DairyProductsActivity.class));
                        break;
                    case R.id.seasonal_fruits:
                        startActivity(new Intent(SearchActivity.this, SeasonalFruitsActivity.class));
                        break;
                    case R.id.offers:
                        startActivity(new Intent(SearchActivity.this, OffersActivity.class));
                        break;
                    case R.id.orders:
                        startActivity(new Intent(SearchActivity.this, OrdersActivity.class));
                        break;
                    case R.id.contact:
                        startActivity(new Intent(SearchActivity.this, ContactActivity.class));
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.search_bar:
                search(item);
                break;
            case R.id.reset:
                webView.reload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void search(MenuItem menuItem){
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type Here to Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else if(webView.canGoBack()) {
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

}

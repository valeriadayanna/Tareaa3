package com.example.tareaa3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import Modelo.Usuario;
import de.hdodenhof.circleimageview.CircleImageView;

public class LytInicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle actionBar;
    DrawerLayout drawer;
    Toolbar toolBar;
    NavigationView navigation;
    FragmentManager frgManager;
    FragmentTransaction frgTransaction;
    View view;
    TextView txtUserName;
    CircleImageView image;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyt_inicio);

        Bundle bundle = this.getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("Usuario");

        drawer = findViewById(R.id.drawerLyt);
        toolBar = findViewById(R.id.lytToolbar);
        navigation = findViewById(R.id.lyt_Inicio);
        setSupportActionBar(toolBar);
        navigation.setNavigationItemSelectedListener(this);

        view = navigation.getHeaderView(0);
        txtUserName = view.findViewById(R.id.txtUserName);
        txtUserName.setText(usuario.getUser());

        image = view.findViewById(R.id.picImage);
        Glide.with(this).load(usuario.getPhoto()).centerCrop().into(image);

        if(usuario.getRoleID().equals("2")) {
            Menu menu = navigation.getMenu();

            MenuItem item = menu.findItem(R.id.itemAgregar);
            item.setVisible(false);

            item = menu.findItem(R.id.itemAjustes);
            item.setVisible(false);
        }
        actionBar = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(actionBar);
        actionBar.setDrawerIndicatorEnabled(true);
        actionBar.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        if (item.getItemId() == R.id.itemHome) {
            frgManager = getSupportFragmentManager();
            frgTransaction = frgManager.beginTransaction();
            frgTransaction.replace(R.id.frame, new MyFragments());
            frgTransaction.commit();
        } else if (item.getItemId() == R.id.itemAgregar) {
            frgManager = getSupportFragmentManager();
            frgTransaction = frgManager.beginTransaction();
            frgTransaction.replace(R.id.frame, new MySecondFragments());
            frgTransaction.commit();
        } else if (item.getItemId() == R.id.itemAjustes) {
            frgManager = getSupportFragmentManager();
            frgTransaction = frgManager.beginTransaction();
            frgTransaction.replace(R.id.frame, new MyThirdFragments());
            frgTransaction.commit();
        }
        return true;
    }
}
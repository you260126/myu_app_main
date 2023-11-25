package com.example.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        //툴바를 액션 바로 설정
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 자동 레이아웃 타이틀 표시하지않기

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_chatting, R.id.nav_wishlist)
                .setOpenableLayout(drawer)
                .build();
        //네비게이션 구성을 정의합니다.
        //이는 드로어를 열거나 닫을 때 어떤 목적지를 고려할지를 결정합니다.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //자동햄버거 아이콘 표시 - 클릭시 네비게이션 드로어 열림

        NavigationUI.setupWithNavController(navigationView, navController);
        // 네비게이션뷰와 컨트롤러를 연결.

        // QR 코드 스캔 버튼 찾기
        View headerView = navigationView.getHeaderView(0);
        Button scan_qr_btn = headerView.findViewById(R.id.scan_qr_btn); // 여기서 scan_qr_btn은 QR 코드 스캔 버튼의 ID입니다.

        // QR 코드 스캔 버튼 클릭 리스너 설정
        scan_qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() { // 뒤로 가기 눌렀을 시
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                String scannedCode = data.getStringExtra("scannedCode");
                Toast.makeText(this, scannedCode, Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "스캔을 취소하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
package com.mexicandeveloper.iplocation.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mexicandeveloper.iplocation.R;
import com.mexicandeveloper.iplocation.model.IPGeolocationEntity;
import com.mexicandeveloper.iplocation.viewModel.IpGeoLocationViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private IpGeoLocationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(IpGeoLocationViewModel.class);

        viewModel.iPGeolocationEntity().observe(this, new Observer<IPGeolocationEntity>() {
            @Override
            public void onChanged(IPGeolocationEntity ipGeolocationEntity) {
                if(ipGeolocationEntity.status.equals("success")){
                    ((TextView) findViewById(R.id.tvAnswer)).setText(ipGeolocationEntity.toString());
                }else{
                    ((TextView) findViewById(R.id.tvAnswer)).setText(ipGeolocationEntity.toString());
                }
            }
        });
        viewModel.errorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                String text = "You Got an Error: " + errorMessage;
                ((TextView) findViewById(R.id.tvAnswer)).setText(text);
            }
        });

        findViewById(R.id.btStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ((EditText) findViewById(R.id.etHome)).getText().toString();
                viewModel.getIPLocation(ip);
            }
        });

    }
}
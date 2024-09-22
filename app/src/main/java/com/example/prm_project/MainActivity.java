package com.example.prm_project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.prm_project.R.layout;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public final class MainActivity extends AppCompatActivity {
    Connection con;
    String str;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_main);
        ConnectionClass connectionClass = new ConnectionClass();
        con = connectionClass.Con();
        connect();
    }
    public void connect(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(()->{
            try {
                if(con == null){
                    str = "Error";
                }else{
                    str = "Connected with SQL Server";
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            runOnUiThread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            });
        });
    }
}

package com.example.prm_project;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.prm_project.adapter.FoodAdapter;
import com.example.prm_project.adapter.FoodRecyclerAdapter;
import com.example.prm_project.model.Food;
import com.example.prm_project.viewmodel.FoodViewModel;
import org.jetbrains.annotations.Nullable;

public final class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodRecyclerAdapter adapter;
    private FoodViewModel foodViewModel;
    private List<Food> foodList;
    private FoodAdapter foodAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.rv_bestseller);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Khởi tạo ViewModel
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        // Quan sát dữ liệu từ ViewModel và cập nhật UI
        foodViewModel.getFoodList().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                if (foods != null) {
                    // Cập nhật dữ liệu cho RecyclerView
                    adapter = new FoodRecyclerAdapter(MainActivity.this, foods);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

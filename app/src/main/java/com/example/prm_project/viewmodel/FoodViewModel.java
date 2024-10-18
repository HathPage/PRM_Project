package com.example.prm_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm_project.model.Food;
import com.example.prm_project.api.FoodApiService;
import com.example.prm_project.api.RetrofitClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodViewModel extends ViewModel {
    private MutableLiveData<List<Food>> foodList;
    private FoodApiService foodApiService;

    public FoodViewModel() {
        foodApiService = RetrofitClient.getClient("http://172.20.10.5:8080/api/v1/").create(FoodApiService.class); // Base URL của API
    }

    // Hàm này sẽ trả về LiveData để Activity/Fragment có thể quan sát
    public LiveData<List<Food>> getFoodList() {
        if (foodList == null) {
            foodList = new MutableLiveData<>();
            loadFoodItems(); // Tải dữ liệu món ăn khi lần đầu gọi
        }
        return foodList;
    }

    // Gọi API để lấy danh sách món ăn từ server
    private void loadFoodItems() {
        Call<List<Food>> call = foodApiService.getAllFoods();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (response.isSuccessful()) {
                    foodList.setValue(response.body()); // Cập nhật dữ liệu LiveData
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                // Xử lý lỗi khi không lấy được dữ liệu
                foodList.setValue(null); // Hoặc có thể xử lý chi tiết hơn
            }
        });
    }
}

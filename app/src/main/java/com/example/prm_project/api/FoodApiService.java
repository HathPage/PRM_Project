package com.example.prm_project.api;
import com.example.prm_project.model.Food;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface FoodApiService {
    @GET("food")
    Call<List<Food>> getAllFoods();
}
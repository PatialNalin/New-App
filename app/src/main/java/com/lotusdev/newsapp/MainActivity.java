package com.lotusdev.newsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface{
    private RecyclerView rvNews,rvCateg;
    private ProgressBar loading;
    private ArrayList<CatagoryRVModel> catagoryRVModels;
    private ArrayList<Articles> articlesArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCateg = findViewById(R.id.catagories);
        loading = findViewById(R.id.progress);
        rvNews = findViewById(R.id.newspad);
        articlesArrayList = new ArrayList<>();
        catagoryRVModels = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(catagoryRVModels,this, this::onCategoryClickInterface);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvCateg.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rvNews.setAdapter(newsRVAdapter);
        rvCateg.setAdapter(categoryRVAdapter);
        getCategories();
        getNews("Hot-News");
        newsRVAdapter.notifyDataSetChanged();
    }

    private void getCategories(){
        catagoryRVModels.add(new CatagoryRVModel("Hot-News","https://plus.unsplash.com/premium_photo-1688561383440-feef3fee567d?w=400&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8YnJlYWtpbmclMjBuZXdzfGVufDB8fDB8fHww"));
        catagoryRVModels.add(new CatagoryRVModel("General","https://plus.unsplash.com/premium_photo-1691223714882-57a432c4edaf?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTN8fEdlbmVyYWwlMjBOZXdzfGVufDB8fDB8fHww"));
        catagoryRVModels.add(new CatagoryRVModel("Science","https://images.unsplash.com/photo-1614935151651-0bea6508db6b?w=400&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fFNjaWVuY2V8ZW58MHx8MHx8fDA%3D"));
        catagoryRVModels.add(new CatagoryRVModel("Technology","https://images.unsplash.com/photo-1496065187959-7f07b8353c55?w=400&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fFRlY2hub2xvZ3l8ZW58MHx8MHx8fDA%3D"));
        catagoryRVModels.add(new CatagoryRVModel("Entertainment","https://images.unsplash.com/photo-1603190287605-e6ade32fa852?w=400&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHx8MA%3D%3D"));
        catagoryRVModels.add(new CatagoryRVModel("Sports","https://images.unsplash.com/photo-1599058917212-d750089bc07e?w=400&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fHNwb3J0fGVufDB8fDB8fHww"));
        catagoryRVModels.add(new CatagoryRVModel("Business","https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?w=400&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8YnVzc2luZXNzfGVufDB8fDB8fHww"));
        catagoryRVModels.add(new CatagoryRVModel("Health","https://images.unsplash.com/photo-1507120410856-1f35574c3b45?w=400&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fGhlYWx0aHxlbnwwfHwwfHx8MA%3D%3D"));
        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String categ){
        loading.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category="+categ+"&apiKey=f370486c5f9b4edbb42114c5954d74bb";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains+stackflow.com&sortBy=publishedAt&language=en&apiKey=f370486c5f9b4edbb42114c5954d74bb";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModel> call;
        if(categ.equals("Hot-News")){
            call = retrofitAPI.getAllNews(url);
        }else{
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }
            call.enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                        if(response.isSuccessful()) {
                            NewsModel newModel = response.body();
                            loading.setVisibility(View.GONE);
                            ArrayList<Articles> article = newModel.getArticles();
                            for (int i = 0; i < article.size(); i++) {
                                articlesArrayList.add(new Articles(article.get(i).getTitle(), article.get(i).getDescription(), article.get(i).getUrl(), article.get(i).getUrlToImage(), article.get(i).getPublishAt(), article.get(i).getContent()));
                            }
                            newsRVAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(MainActivity.this,"Technical Issue",Toast.LENGTH_LONG);
                        }
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable throwable) {
                    Toast.makeText(MainActivity.this,"Technical Issue",Toast.LENGTH_LONG);
                }
            });
    }

    @Override
    public void onCategoryClickInterface(int position) {
            String category = catagoryRVModels.get(position).getCategory();
        getNews(category);
    }
}
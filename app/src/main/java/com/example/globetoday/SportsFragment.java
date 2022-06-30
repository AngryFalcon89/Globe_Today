package com.example.globetoday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {
    String API = "6908bb4b71ae46fc926253f68c890aec";
    ArrayList<News> NewsArray;
    ListAdapter adapter;
    String country= "in";
    private RecyclerView recyclerViewOfSports;
    private String category ="sports";
    ProgressBar progressBar;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sports_fragment,null);

        recyclerViewOfSports = view.findViewById(R.id.sports_fragment);
        NewsArray = new ArrayList<>();
        recyclerViewOfSports.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ListAdapter(getContext(),NewsArray);
        recyclerViewOfSports.setAdapter(adapter);

        progressBar=view.findViewById(R.id.loading_indicator_sports);
        textView = view.findViewById(R.id.empty_view_sports);
        findNews();
        return view;
    }

    private void findNews() {
        ApiUtility.getApiInterface().getCategoryNews(country,category,100,API).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    NewsArray.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                textView.setText("No News");
                textView.setVisibility(View.VISIBLE);
            }
        });
    }
}


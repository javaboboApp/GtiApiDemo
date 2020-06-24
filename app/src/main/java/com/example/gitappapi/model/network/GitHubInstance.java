package com.example.gitappapi.model.network;

import com.example.gitappapi.model.data.Repository;
import com.example.gitappapi.util.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.gitappapi.util.Constants.BASE_URL;

public class GitHubInstance
{

    public static GitHubInstance gitHubInstance = null;

    private GitService gitService;

    public static GitHubInstance getUniqueInstance(){
        if(gitHubInstance == null){
            gitHubInstance = new GitHubInstance();
        }
        return gitHubInstance;
    }

    private Retrofit  createRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public GitService createService(){
        return createRetrofitInstance().create(GitService.class);
    }



}

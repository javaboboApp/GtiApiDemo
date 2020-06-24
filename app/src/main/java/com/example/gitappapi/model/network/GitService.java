package com.example.gitappapi.model.network;

import com.example.gitappapi.model.data.Repository;
import com.example.gitappapi.util.Event;
import com.example.gitappapi.util.Resource;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.example.gitappapi.util.Constants.GIT_REPOSITORIES;
import static com.example.gitappapi.util.Constants.USER_PATH;

public interface GitService {
    @GET(GIT_REPOSITORIES)
    Observable<List<Repository>>  getUserRepositories(@Path(USER_PATH) String userName);
}

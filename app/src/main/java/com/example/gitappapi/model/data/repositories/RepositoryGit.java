package com.example.gitappapi.model.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.gitappapi.model.data.Repository;
import com.example.gitappapi.util.Event;
import com.example.gitappapi.util.Resource;

import java.util.List;

import io.reactivex.Observable;

public interface RepositoryGit {
    Observable<List<Repository>>  getUserRepositories(String userName) ;

}

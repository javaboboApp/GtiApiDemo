package com.example.gitappapi.model.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gitappapi.model.data.Repository;
import com.example.gitappapi.model.network.GitService;
import com.example.gitappapi.util.Event;
import com.example.gitappapi.util.Resource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RepositoryGitImpl implements RepositoryGit {
    public static final String TAG = "RepositoryGitImpl";
    private GitService gitService;

    public RepositoryGitImpl(GitService gitService) {
        this.gitService = gitService;
    }


    @Override
    public Observable<List<Repository>>  getUserRepositories(String userName) {
        return gitService.getUserRepositories(userName);
    }


}

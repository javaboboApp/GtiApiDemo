package com.example.gitappapi.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gitappapi.model.data.Repository;
import com.example.gitappapi.model.data.repositories.RepositoryGit;
import com.example.gitappapi.util.Resource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GitViewModel extends BaseViewModel {

    private static final String TAG = "GitViewModel";
    private MutableLiveData<Resource<List<Repository>>> _getUserRepositories = new MutableLiveData<>();
    private LiveData<Resource<List<Repository>>> getUserRepositories = _getUserRepositories;
    private RepositoryGit repositoryGit;


    public GitViewModel(RepositoryGit repositoryGit) {
        this.repositoryGit = repositoryGit;
    }

    public void getUserRepositories(String userName) {
        emitLoading();
        subscribe(repositoryGit.getUserRepositories(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError));

    }

    private void emitLoading() {
        _getUserRepositories.setValue(Resource.loading(new ArrayList<>()));
    }

    private void onSuccess(List<Repository> repositories) {
        Log.i(TAG, "onSuccess: called");
        //list is empty
        if (repositories.size() == 0) {
            _getUserRepositories.setValue(Resource.empty(repositories));
            return;
        }
        _getUserRepositories.setValue(Resource.success(repositories));


    }


    private void onError(Throwable throwable) {

        Log.i(TAG, "onError: " + throwable.getMessage());

        _getUserRepositories.setValue(Resource.error("onError: " + throwable.getMessage(),
                new ArrayList<>()));

    }


    public LiveData<Resource<List<Repository>>> observeGetUserRepositories() {
        return getUserRepositories;
    }

    @Override
    protected void onCleared() {
        cleanDisposable();
        super.onCleared();
    }
}

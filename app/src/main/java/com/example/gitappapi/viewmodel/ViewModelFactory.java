package com.example.gitappapi.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gitappapi.model.data.repositories.RepositoryGit;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final RepositoryGit repositoryGit;

    public ViewModelFactory(RepositoryGit repositoryGit) {
        this.repositoryGit = repositoryGit;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GitViewModel(repositoryGit);
    }
}

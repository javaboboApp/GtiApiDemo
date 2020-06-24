package com.example.gitappapi.viewmodel;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void subscribe(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    protected void cleanDisposable(){
        compositeDisposable.clear();
    }

}

package com.example.gitappapi.ui.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gitappapi.R;
import com.example.gitappapi.model.data.Repository;
import com.example.gitappapi.model.data.repositories.RepositoryGitImpl;
import com.example.gitappapi.model.network.GitHubInstance;
import com.example.gitappapi.ui.adapter.RepositoryListAdapter;
import com.example.gitappapi.util.Constants;
import com.example.gitappapi.util.KeyWordHelper;
import com.example.gitappapi.util.Resource;
import com.example.gitappapi.util.ToastHelper;
import com.example.gitappapi.viewmodel.GitViewModel;
import com.example.gitappapi.viewmodel.ViewModelFactory;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private EditText edittext_search;
    private Button bottom_search;
    private RecyclerView gitHubrecyclerView;
    private TextView msg_empty_status_in_the_center;
    private ProgressBar progressBar;
    private RepositoryListAdapter repositoryListAdapter = new RepositoryListAdapter();
    private GitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
        initViewModel();
        initObservers();
    }

    private void initObservers() {
        viewModel.observeGetUserRepositories().observe(this, this::observerSearchResults);
    }

    private void observerSearchResults(Resource<List<Repository>> result) {
        switch (result.status) {

            case SUCCESS:
                repositoryListAdapter.submitList(result.data.peekContent());
                hideProgressBar();
                hideMsgStatus();
                showRecyclerView();
                break;
            case EMPTY:
                hideProgressBar();
                showMsgEmptyStatus();
                hideRecyclerView();
                break;
            case ERROR:
                if (result.data.getContentIfNoHandled() != null) {
                    hideProgressBar();
                    showError();
                }
                hideMsgStatus();
                hideRecyclerView();
                break;
            case LOADING:
                if (result.data.getContentIfNoHandled() != null) {
                    showProgressBar();
                }

                break;

        }
    }

    private void showRecyclerView() {
        gitHubrecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideRecyclerView() {
        gitHubrecyclerView.setVisibility(View.INVISIBLE);
    }

    private void showMsgEmptyStatus() {
        msg_empty_status_in_the_center.setVisibility(View.VISIBLE);
    }

    private void hideMsgStatus() {
        msg_empty_status_in_the_center.setVisibility(View.INVISIBLE);
    }

    private void showError() {
        ToastHelper.showToast(HomeActivity.this, getString(R.string.toast_error_msg), Toast.LENGTH_LONG);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void initListeners() {
        bottom_search.setOnClickListener(this::onClick);
    }


    private void initViews() {
        edittext_search = findViewById(R.id.git_repo_edittext);
        bottom_search = findViewById(R.id.bottom_search);
        progressBar = findViewById(R.id.progressBar);
        msg_empty_status_in_the_center = findViewById(R.id.list_is_empty_msg);
        gitHubrecyclerView = findViewById(R.id.repository_recycler_view);
        gitHubrecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        gitHubrecyclerView.setAdapter(repositoryListAdapter);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this,
                new ViewModelFactory(new RepositoryGitImpl(GitHubInstance.getUniqueInstance().createService())))
                .get(GitViewModel.class);
    }

    public void onClick(View v) {
        if (!validateEditTextSearch()) {
            showErrorValidation();
            return;
        }
        viewModel.getUserRepositories(edittext_search.getText().toString());
        KeyWordHelper.hideKeyboardFrom(HomeActivity.this, edittext_search);
    }

    private void showErrorValidation() {
        ToastHelper.showToast(HomeActivity.this, getString(R.string.text_to_search_no_valid), Toast.LENGTH_LONG);
    }

    private boolean validateEditTextSearch() {
        String textToSearch = edittext_search.getText().toString();
        return !textToSearch.trim().isEmpty() && textToSearch.length() > Constants.MIM_SIZE_TO_SEARCH;
    }

    @Override
    protected void onDestroy() {
        //be careful avoid memory leaks
        gitHubrecyclerView.setAdapter(null);
        super.onDestroy();
    }
}
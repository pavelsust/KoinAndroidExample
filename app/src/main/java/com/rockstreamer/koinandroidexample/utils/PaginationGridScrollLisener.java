package com.rockstreamer.koinandroidexample.utils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by android on 10/10/2017.
 */

public abstract class PaginationGridScrollLisener extends RecyclerView.OnScrollListener {

    LinearLayoutManager layoutManager;

    public PaginationGridScrollLisener(LinearLayoutManager linearLayoutManager) {
        this.layoutManager = linearLayoutManager;
    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        getTotalPageCount();


        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();



        if (!isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount  && firstVisibleItemPosition >= 0) {
                loadMoreItem();
            }
        }
    }

    public abstract void loadMoreItem();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}

package com.example.demo.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.demo.R;
import com.example.demo.view.adapter.MyDataAdapter;
import com.example.demo.view.adapter.PostRecyclerAdapter;
import com.example.demo.viewmodel.MyListViewModel;

import java.util.ArrayList;

//import static com.example.demo.view.PaginationListener.PAGE_START;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerview;
    private MyListViewModel myListViewModel;
    private PostRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private LinearLayoutManager mLayoutManager;
    private boolean loading = true;
    private int currentPage =1;
    private boolean isLastPage = false;
    private int totalPage = 2;
    private boolean isLoading = false;
    private int itemCount = 0;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(mLayoutManager);
        adapter = new PostRecyclerAdapter(new ArrayList<>());
        recyclerview.setAdapter(adapter);
        myListViewModel = ViewModelProviders.of(this).get(MyListViewModel.class);
        fun();
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            currentPage++;
                            Log.v("...", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data
                            fun();
                            loading = true;
                        }
                    }
                }
            }
        });
      /*  recyclerview.addOnScrollListener(new PaginationListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {

                isLoading = true;
                currentPage++;
                fun();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });*/

        return view;

    }

    private void fun() {
        myListViewModel.getMutableLiveData(currentPage).observe(getActivity(), new Observer<ArrayList<MyListViewModel>>() {
            @Override
            public void onChanged(ArrayList<MyListViewModel> myListViewModels) {
                final ArrayList<MyListViewModel> items = new ArrayList<>();


              /*  for (int i = 0; i < my; i++) {
                    itemCount++;
                    MyListViewModel itemModel = new MyListViewModel();
                    itemModel.email=myListViewModels.get(i).email;
                    itemModel.name=myListViewModels.get(i).name;
                    itemModel.image=myListViewModels.get(i).image;
                    items.add(itemModel);
                }*/
                if (currentPage != 1) adapter.removeLoading();
                //  adapter = new PostRecyclerAdapter(myListViewModels);
                // recyclerview.setAdapter(adapter);
                adapter.clear();
                adapter.addItems(myListViewModels);
                if (swipeRefresh.isRefreshing())
                    swipeRefresh.setRefreshing(false);
                // check weather is last page or not
                if (currentPage < totalPage) {
                    adapter.addLoading();
                } else {
                    isLastPage = true;
                }
                isLoading = false;
            }
        });

    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = 1;
        isLastPage = false;
        adapter.clear();
        fun();
    }
}
package com.example.firstapplication.Fragments.NavigationFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.firstapplication.Adapters.OrderAdapter;
import com.example.firstapplication.Entities.Order;
import com.example.firstapplication.Interfaces.OrderInterface;
import com.example.firstapplication.MyApplication;
import com.example.firstapplication.R;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderFragment extends Fragment {
    Bundle bundle;
    ArrayList<Order> orders;
    RecyclerView recycler_order;
    OrderAdapter orderAdapter;
    public OrderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_order, container, false);
        bundle=getArguments();
//        Serializable item=bundle.getSerializable("item");
//        DrawerLayoutItem drawerLayoutItem=(DrawerLayoutItem)item;
//        drawerLayoutItem.getName();

        recycler_order=(RecyclerView)v.findViewById(R.id.recycler_order);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        recycler_order.setLayoutManager(manager);
        recycler_order.setHasFixedSize(true);

        Retrofit retrofit=MyApplication.connect();
        OrderInterface orderInterface=retrofit.create(OrderInterface.class);
        Call<ArrayList<Order>> call=orderInterface.getAllOrderToCustomer("c13d8c1d-6480-4cdd-89bc-5a36a5a44c9c");
        call.enqueue(new Callback<ArrayList<Order>>() {
            @Override
            public void onResponse(Call<ArrayList<Order>> call, Response<ArrayList<Order>> response) {
                if(response.isSuccessful()){
                     orders=response.body();
                     orderAdapter=new OrderAdapter(getActivity(), response.body(), new OrderAdapter.OnItemClickListenerOrder() {
                        @Override
                        public void onItemClick(Order item) {
                           if(item.getIsSelected() == 0){
                               item.setIsSelected(1);
                           }else {
                               item.setIsSelected(0);
                           }
                           orderAdapter.notifyDataSetChanged();
                        }
                    });
                    recycler_order.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Order>> call, Throwable t) {
            }
        });
        return v;
    }

}

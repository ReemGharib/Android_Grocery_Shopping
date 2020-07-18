package com.example.firstapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.firstapplication.MyApplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firstapplication.Entities.Order;
import com.example.firstapplication.Entities.OrderProduct;
import com.example.firstapplication.Interfaces.OrderProductInterface;
import com.example.firstapplication.R;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    ArrayList<Order> orderArrayList;
    OnItemClickListenerOrder listenerOrder;
    final Context context;
    private static final int SELECTED = 1;
    private static final int UNSELECTED = 0;

    CheckoutAdapter adapter;
    Animation slide_down, slide_up;

    public OrderAdapter(Context c, ArrayList<Order> arrayList, OnItemClickListenerOrder listenerOrder){
        context=c;
        this.listenerOrder=listenerOrder;
        orderArrayList=arrayList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SELECTED) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
            return new OrderViewHolder(v, context);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_order, parent, false);
            return new OrderViewHolder(v, context);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        if (getItemViewType(position) == SELECTED) {
            holder.order_id.setText(orderArrayList.get(position).getId());
            holder.customer_name.setText(orderArrayList.get(position).getCustomer().getFirstName());
            holder.customer_lastName.setText(orderArrayList.get(position).getCustomer().getLastName());
            holder.bind(orderArrayList.get(position),listenerOrder);

            ArrayList<OrderProduct> orderProductArrayList=showOrderDetails(orderArrayList.get(position).getId());

            adapter=new CheckoutAdapter(context, orderProductArrayList);
            holder.nav_recycler_order_details.setHasFixedSize(true);
            LinearLayoutManager manager=new LinearLayoutManager(context);
            holder.nav_recycler_order_details.setLayoutManager(manager);
            holder.nav_recycler_order_details.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            // holder.customer_name.startAnimation(slide_up);

        } else if (getItemViewType(position) == UNSELECTED) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            holder.item_id.setText(orderArrayList.get(position).getId());
            holder.bind(orderArrayList.get(position),listenerOrder);
            //holder.item_id.startAnimation(animation);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (orderArrayList.get(position).getIsSelected() == 1) {
            return SELECTED;
        }else {
            return UNSELECTED;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

     public ArrayList<OrderProduct> showOrderDetails(String id){
        OrderProductInterface orderProductInterface = MyApplication.connect().create(OrderProductInterface.class);
        Call<ArrayList<OrderProduct>> call= orderProductInterface.getAllOrderProductToOrder(id);
        call.enqueue(new Callback<ArrayList<OrderProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderProduct>> call, Response<ArrayList<OrderProduct>> response) {
                    response.body();
                    MyApplication.test = response.body();
            }
            @Override
            public void onFailure(Call<ArrayList<OrderProduct>> call, Throwable t) {
                Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
            }
        });
        return MyApplication.test;
    }

    public interface OnItemClickListenerOrder{
        void onItemClick(Order item);
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView item_id, customer_name, customer_lastName, order_id;
        LinearLayout linearLayout;
        RecyclerView nav_recycler_order_details;
         public OrderViewHolder(@NonNull View itemView, Context c) {
             super(itemView);
             linearLayout=itemView.findViewById(R.id.slide_up_layout);
             order_id=itemView.findViewById(R.id.test_order_id_2);
             item_id=itemView.findViewById(R.id.test_order_id);
             customer_name=itemView.findViewById(R.id.test_order_customer_name);
             customer_lastName=itemView.findViewById(R.id.test_order_customer_lastName);
             nav_recycler_order_details=itemView.findViewById(R.id.nav_recycler_order_details);
//             LinearLayoutManager manager=new LinearLayoutManager(c);
//             nav_recycler_order_details.setLayoutManager(manager);
//             nav_recycler_order_details.setHasFixedSize(true);

         }
        public void bind(final Order item, final OnItemClickListenerOrder listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
     }

}

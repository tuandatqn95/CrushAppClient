package com.crush.crushappclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.OrderItemAdapter;
import com.crush.crushappclient.fragment.ProductFragment;
import com.crush.crushappclient.model.Order;
import com.crush.crushappclient.model.OrderItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";
    public static final int REQUEST_CODE_ORDER_COMPLETE = 11;

    private List<OrderItem> orderItemList;

    @BindView(R.id.recyclerView_order_drink)
    RecyclerView rvOrderDrink;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);

        mFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        orderItemList = (List<OrderItem>) intent.getSerializableExtra(ProductFragment.LIST_ORDER_ITEM);
        OrderItemAdapter adapter = new OrderItemAdapter(orderItemList);

        rvOrderDrink.setAdapter(adapter);
        rvOrderDrink.setLayoutManager(new LinearLayoutManager(this));
        rvOrderDrink.setItemAnimator(new DefaultItemAnimator());
        rvOrderDrink.addItemDecoration(new DividerItemDecoration(rvOrderDrink.getContext(), DividerItemDecoration.VERTICAL));

    }

    @OnClick(R.id.btn_cart)
    public void OnBtnCartCLicked(View view) {
        Order order = new Order();
        // TODO: 6/3/2018 - Add more information into order
        long price = 0;
        for (OrderItem orderItem : orderItemList) {
            price += orderItem.getPrice() * orderItem.getQuantity();
        }
        order.setTotalPrice(price);

        onCheckout(order, orderItemList);
    }

    private void onCheckout(Order order, List<OrderItem> orderItemList) {
        addOrder(order, orderItemList).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Intent resultIntent = new Intent();
                // TODO: 6/3/2018 - Add intent for cart complete here
                setResult(REQUEST_CODE_ORDER_COMPLETE, resultIntent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CartActivity.this, "Checkout Failure, please try again!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private Task<Void> addOrder(final Order order, final List<OrderItem> orderItems) {
        // Create reference for new rating, for use inside the transaction
        final DocumentReference orderRef = mFirestore.collection("orders").document();
        // In a transaction, add the new rating and update the aggregate totals
        return mFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                // Commit to Firestore
                transaction.set(orderRef, order);

                for (OrderItem orderItem : orderItemList) {
                    final DocumentReference orderItemRef = orderRef.collection("orderitems").document();
                    transaction.set(orderItemRef, orderItem);
                }
                return null;
            }
        });
    }


}

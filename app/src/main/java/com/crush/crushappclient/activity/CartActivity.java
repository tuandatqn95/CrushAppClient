package com.crush.crushappclient.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.OrderItemAdapter;
import com.crush.crushappclient.fragment.ProductFragment;
import com.crush.crushappclient.model.Order;
import com.crush.crushappclient.model.OrderItem;
import com.crush.crushappclient.util.StringFormatUtils;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
    private List<OrderItem> orderItemList;

    @BindView(R.id.recyclerView_order_drink)
    RecyclerView rvOrderDrink;

    @BindView(R.id.order_total_price)
    TextView txtTotalPrice;

    @BindView(R.id.cart_activity_toolbar)
    Toolbar toolbar;

    @BindView(R.id.order_address)
    EditText orderAddress;

    @BindView(R.id.order_note)
    EditText orderNote;



    private FirebaseFirestore mFirestore;
    private long totalPrice = 0;
    private Dialog dialog;
    private String address="";
    private String note="";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        addToolBar();

        mFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        orderItemList = (List<OrderItem>) intent.getSerializableExtra(ProductFragment.LIST_ORDER_ITEM);
        OrderItemAdapter adapter = new OrderItemAdapter(orderItemList);

        rvOrderDrink.setAdapter(adapter);
        rvOrderDrink.setLayoutManager(new LinearLayoutManager(this));
        rvOrderDrink.setItemAnimator(new DefaultItemAnimator());
        rvOrderDrink.addItemDecoration(new DividerItemDecoration(rvOrderDrink.getContext(), DividerItemDecoration.VERTICAL));

        setTotalPrice();

        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.RECTANGLE);
        background.setCornerRadius(15);
        background.setStroke(1, Color.YELLOW);
        orderAddress.setBackground(background);
        orderNote.setBackground(background);
    }

    @OnClick(R.id.btn_cart)
    public void OnBtnCartCLicked(View view) {
        Order order = new Order();
        order.setUserId(FirebaseAuth.getInstance().getUid());
        order.setAddress(orderAddress.getText().toString());
        order.setNote(orderNote.getText().toString());
        order.setStatus("ORDERED");
        order.setTotalPrice(totalPrice);

        onCheckout(order, orderItemList);
    }



    private void onCheckout(Order order, List<OrderItem> orderItemList) {
        addOrder(order, orderItemList).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
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

    private void setTotalPrice(){
        for (OrderItem orderItem : orderItemList) {
            totalPrice += orderItem.getPrice() * orderItem.getQuantity();
        }
        txtTotalPrice.setText(StringFormatUtils.FormatCurrency(totalPrice));
    }



    @SuppressLint("ResourceAsColor")
    private void addToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //toolbar.setLogo(R.drawable.crush_logo_yellow);
        toolbar.setTitle("Crush Milk Tea");
        toolbar.setTitleTextColor(R.color.colorMainYellow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 16908332:
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, resultIntent);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}

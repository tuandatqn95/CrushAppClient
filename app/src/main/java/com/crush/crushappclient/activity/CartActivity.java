package com.crush.crushappclient.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.OrderItemAdapter;
import com.crush.crushappclient.fragment.ProductFragment;
import com.crush.crushappclient.model.Order;
import com.crush.crushappclient.model.OrderItem;
import com.crush.crushappclient.util.StringFormatUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends AppCompatActivity implements OrderItemAdapter.OnOrderItemSelectedListener {

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

    private OrderItemAdapter adapter;
    private ProgressDialog mProgressDialog;
    private String address = "";
    private String note = "";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        addToolBar();

        mFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        orderItemList = (List<OrderItem>) intent.getSerializableExtra(ProductFragment.LIST_ORDER_ITEM);
        adapter = new OrderItemAdapter(orderItemList, this);

        rvOrderDrink.setAdapter(adapter);
        rvOrderDrink.setLayoutManager(new LinearLayoutManager(this));
        rvOrderDrink.setItemAnimator(new DefaultItemAnimator());
        rvOrderDrink.addItemDecoration(new DividerItemDecoration(rvOrderDrink.getContext(), DividerItemDecoration.VERTICAL));

        updateTotalPrice();
        addBackGround();

    }

    @OnClick(R.id.btn_cart)
    public void OnBtnCartCLicked(View view) {
        if (orderItemList.size() < 1) {
            Toast.makeText(CartActivity.this, "Please order!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(orderAddress.getText())) {
            Toast.makeText(CartActivity.this, "Please fill address", Toast.LENGTH_SHORT).show();
            return;
        }
        showAlertDialog();
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Do you want to order?");
        builder.setCancelable(false);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showProgressDialog("Ordering...");
                Order order = new Order();
                order.setUserId(FirebaseAuth.getInstance().getUid());
                order.setAddress(orderAddress.getText().toString());
                order.setNote(orderNote.getText().toString());
                order.setStatus("ORDERED");
                order.setTotalPrice(updateTotalPrice());
                onCheckout(order, orderItemList);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void onCheckout(Order order, List<OrderItem> orderItemList) {
        addOrder(order, orderItemList).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                hideProgressDialog();
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                hideProgressDialog();
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
                long amount = 0;

                // Commit to Firestore
                transaction.set(orderRef, order);

                for (OrderItem orderItem : orderItemList) {
                    final DocumentReference orderItemRef = orderRef.collection("orderitems").document();
                    transaction.set(orderItemRef, orderItem);
                    amount += orderItem.getQuantity();
                    order.setAmount(amount);
                }

                transaction.set(orderRef, order);
                return null;
            }
        });
    }

    private long updateTotalPrice() {
        long totalPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            totalPrice += orderItem.getPrice() * orderItem.getQuantity();
        }
        txtTotalPrice.setText(StringFormatUtils.FormatCurrency(totalPrice));
        return totalPrice;
    }


    @SuppressLint("ResourceAsColor")
    private void addToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addBackGround() {
        GradientDrawable orderbackground = new GradientDrawable();
        orderbackground.setShape(GradientDrawable.RECTANGLE);
        orderbackground.setCornerRadius(15);
        orderbackground.setStroke(1, Color.BLACK);
        GradientDrawable notebackground = new GradientDrawable();
        notebackground.setShape(GradientDrawable.RECTANGLE);
        notebackground.setCornerRadius(15);
        notebackground.setStroke(1, Color.BLACK);
        orderAddress.setBackground(orderbackground);
        orderNote.setBackground(notebackground);
    }

    private void showProgressDialog(String caption) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.setMessage(caption);
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(ProductFragment.LIST_ORDER_ITEM, (Serializable) orderItemList);
        setResult(Activity.RESULT_CANCELED, resultIntent);
        super.onBackPressed();


    }

    @Override
    public void OnOrderItemDelete(int position) {
        updateTotalPrice();
    }
}

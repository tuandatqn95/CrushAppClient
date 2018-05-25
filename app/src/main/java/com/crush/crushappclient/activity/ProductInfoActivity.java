package com.crush.crushappclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.adapter.ToppingAdapter;
import com.crush.crushappclient.model.MainDrink;
import com.crush.crushappclient.model.OrderItem;
import com.crush.crushappclient.model.Topping;
import com.crush.crushappclient.util.StringFormatUtils;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInfoActivity extends AppCompatActivity implements EventListener<DocumentSnapshot> {

    public static final String ARG_PARAM_1 = "CATEGORY_ID";
    public static final String ARG_PARAM_2 = "MAINDRINK_ID";
    private static final String TAG = "ProductInfoActivity";

    @BindView(R.id.txtvdrinkName)
    TextView txtvName;

    @BindView(R.id.txtvdrinkPrice)
    TextView txtvPrice;

    @BindView(R.id.imageViewDrink)
    ImageView imgvDrink;

    @BindView(R.id.imgvback)
    ImageView imgvBack;

    @BindView(R.id.btnOrder)
    Button btnOrder;

    @BindView(R.id.numberPickerQuantity)
    NumberPicker npQuantity;

    @BindView(R.id.recyclerViewTopping)
    RecyclerView recyclerView;

    private ToppingAdapter adapter;
    private FirebaseFirestore mFirestore;
    private Query mQuery;

    private DocumentReference drinkRef;
    ListenerRegistration mDrinkRegistration;
    private String categoryId;
    private String drinkId;

    ArrayList<DocumentSnapshot> toppingList = new ArrayList<>();
    private MainDrink drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        categoryId = intent.getStringExtra(ARG_PARAM_1);
        drinkId = intent.getStringExtra(ARG_PARAM_2);

        mFirestore = FirebaseFirestore.getInstance();
        mQuery = mFirestore.collection("toppings");
        drinkRef = mFirestore.collection("categories").document(categoryId).collection("maindrinks").document(drinkId);

        adapter = new ToppingAdapter(mQuery, new ToppingAdapter.OnToppingSelectedListener() {
            @Override
            public void OnToppingSelected(DocumentSnapshot snapshot) {
                toppingList.add(snapshot);
                setdrinkPrice();
            }

            @Override
            public void OnToppingDeselected(DocumentSnapshot snapshot) {
                toppingList.remove(snapshot);
                setdrinkPrice();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        init();


    }

    private void init() {

        npQuantity.setMinValue(1);
        npQuantity.setMaxValue(50);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null) {
            adapter.startListening();
        }
        mDrinkRegistration = drinkRef.addSnapshotListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.startListening();
        }
        if (mDrinkRegistration != null) {
            mDrinkRegistration.remove();
            mDrinkRegistration = null;
        }

    }


    private void setdrinkPrice() {

        long price = drink.getPrice();
        for(DocumentSnapshot snapshot : toppingList){
            price+=snapshot.getLong(Topping.KEY_TOPPING_PRICE);
        }

        txtvPrice.setText(StringFormatUtils.FormatCurrency(price));
    }

    private void addEvent() {


    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
        drink = snapshot.toObject(MainDrink.class);
        Log.d(TAG,"onEvent: "+drink);
        setdrinkPrice();
        txtvName.setText(drink.getName());
    }
}

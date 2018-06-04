package com.crush.crushappclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductInfoActivity extends AppCompatActivity implements EventListener<DocumentSnapshot> {

    public static final String ARG_PARAM_1 = "CATEGORY_ID";
    public static final String ARG_PARAM_2 = "MAINDRINK_ID";
    public static final String TAG = "ProductInfoActivity";
    public static final String LIST_TOPPING = "LIST_TOPPING";
    public static final String KEY_ORDER_ITEM = "ORDER_ITEM";

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

    private long price;
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
        // get drink from Tab Product Fragment
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
                updateDrinkPrice();
            }

            @Override
            public void OnToppingDeselected(DocumentSnapshot snapshot) {
                toppingList.remove(snapshot);
                updateDrinkPrice();
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
        //addEvent();

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

    @OnClick(R.id.imgvback)
    public void OnImgBackClicked(View view){
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    @OnClick(R.id.btnOrder)
    public void OnBtnOrderClicked(View view){
        Intent resultIntent = new Intent();
        List<Topping> listTopping = new ArrayList<>();
        for (DocumentSnapshot topping : toppingList) {
            listTopping.add(topping.toObject(Topping.class));
        }
        OrderItem orderItem = new OrderItem(drink, listTopping, npQuantity.getValue(), price);
        resultIntent.putExtra(KEY_ORDER_ITEM, (Serializable) orderItem);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void addEvent() {
        imgvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent();
                List<Topping> listTopping = new ArrayList<>();
                for (DocumentSnapshot topping : toppingList) {
                    listTopping.add(topping.toObject(Topping.class));
                }
                OrderItem orderItem = new OrderItem(drink, listTopping, npQuantity.getValue(), price);
                intentResult.putExtra(KEY_ORDER_ITEM, (Serializable) orderItem);
                setResult(Activity.RESULT_OK, intentResult);
                finish();
            }
        });

    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
        MainDrink mainDrink = snapshot.toObject(MainDrink.class);

        OnMainDrinkLoaded(mainDrink);
    }

    private void OnMainDrinkLoaded(MainDrink drink) {
        this.drink = drink;
        this.price = getDrinkPrice();
        txtvPrice.setText(StringFormatUtils.FormatCurrency(price));
        txtvName.setText(drink.getName());
    }


    private long getDrinkPrice() {
        long price = drink.getPrice();
        for (DocumentSnapshot snapshot : toppingList) {
            price += snapshot.getLong(Topping.KEY_TOPPING_PRICE);
        }
        return price;
    }

    private void updateDrinkPrice() {
        this.price = getDrinkPrice();
        txtvPrice.setText(StringFormatUtils.FormatCurrency(price));
    }
}

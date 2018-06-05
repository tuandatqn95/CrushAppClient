package com.crush.crushappclient.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.fragment.ProfileFragment;
import com.crush.crushappclient.model.Customer;
import com.crush.crushappclient.util.StringFormatUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Transaction;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileManagerActivity extends AppCompatActivity implements EventListener<DocumentSnapshot> {
    private static final String TAG = "ProfileManagerActivity";
    public static final String USER_INFO = "user_info";
    @BindView(R.id.spinnerSex)
    Spinner spinnerSex;

    @BindView(R.id.edtname)
    EditText name;

    @BindView(R.id.edtaddress)
    EditText address;

    @BindView(R.id.txtvdate)
    TextView date;

    @BindView(R.id.txtvPhone)
    TextView phone;

    @BindView(R.id.edtemail)
    EditText email;

    @BindView(R.id.customer_name)
    TextView customerName;


    Calendar cal;
    Date dateFinish;
    Date hourFinish;
    private FirebaseFirestore mFirestore;
    private DocumentReference mUserRef;
    private ListenerRegistration mUserRegistration;
    private DocumentSnapshot mSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        mFirestore = FirebaseFirestore.getInstance();
        mUserRef = mFirestore.collection("customers").document(FirebaseAuth.getInstance().getUid());



    }

    @Override
    protected void onStart() {
        super.onStart();
        mUserRegistration = mUserRef.addSnapshotListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mUserRegistration != null) {
            mUserRegistration.remove();
            mUserRegistration = null;
        }
    }

    @OnClick(R.id.txtvdate)
    public void showDatePickerDialog( View view) {
        final TextView date = (TextView) view;
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                date.setTag(new Date(year-1900,monthOfYear,dayOfMonth));
            }
        };

        String s = date.getText()+"";
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1]) - 1;
        int nam = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(
                ProfileManagerActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Pick date");
        pic.show();
    }

    @OnClick(R.id.btnupdate)
    public void onBtnUpdateClick(View view) {
        Customer customer = new Customer();

        customer.setName(name.getText().toString());
        customer.setAddress(address.getText().toString());
        customer.setBirth((Date) date.getTag());
        customer.setPhone(phone.getText().toString());
        customer.setEmail(email.getText().toString());
        customer.setGender(spinnerSex.getSelectedItem().toString());

        addCustomer(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileManagerActivity.this, "Update profile fail, please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @OnClick(R.id.profile_manager_back)
    public void OnProfileManagerBackClicked(){
        finish();
    }
    private Task<Void> addCustomer(final Customer customer) {
        // Create reference for new rating, for use inside the transaction
        final DocumentReference customerRef = mFirestore.collection("customers").document(FirebaseAuth.getInstance().getUid());

        // In a transaction, add the new rating and update the aggregate totals
        return mFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                // Commit to Firestore
                transaction.set(customerRef, customer);
                return null;
            }
        });
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
        phone.setText(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
        if(snapshot.getData()!=null){
            this.mSnapshot = snapshot;
            onUserLoaded(snapshot.toObject(Customer.class));
        }

    }

    private void onUserLoaded(Customer customer) {

        Log.d(TAG, "onUserLoaded: Customer" + customer);
        customerName.setText(customer.getName());
        name.setText(customer.getName());
        address.setText(customer.getAddress());
        email.setText(customer.getEmail());
        spinnerSex.setSelection(getSpinnerValue(customer.getGender()));
        if(customer.getBirth() == null){
            date.setText(StringFormatUtils.getFormatCurrentDate());
        }
        else{
            Log.d(TAG, "onUserLoaded: "+customer.getBirth());
            date.setText(StringFormatUtils.FormatToDate(customer.getBirth()));
        }
    }

    private int getSpinnerValue(String value){
        switch (value){
            case "Không xác định":
                return 0;
            case "Nam":
                return 1;
            case "Nữ":
                return 2;
        }
        return 0;
    }
}

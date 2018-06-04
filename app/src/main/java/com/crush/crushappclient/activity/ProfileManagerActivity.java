package com.crush.crushappclient.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.crush.crushappclient.R;
import com.crush.crushappclient.model.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileManagerActivity extends AppCompatActivity {

    public static final String USER_INFO = "user_info";
    @BindView(R.id.spinnerSex)
    Spinner spinnerSex;

    @BindView(R.id.edtname)
    EditText edtName;

    @BindView(R.id.edtemail)
    EditText edtEmail;


    Calendar cal;
    Date dateFinish;
    Date hourFinish;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        mFirestore = FirebaseFirestore.getInstance();

        // TODO: 6/4/2018 Check if intent != null then fill info to form 

    }

    @OnClick(R.id.txtvdate)
    public void showDatePickerDialog(View view) {
        final EditText txtvDate = (EditText) view;
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                txtvDate.setText((dayOfMonth > 9 ? dayOfMonth : "0" + dayOfMonth) + "/" + (monthOfYear > 8 ? monthOfYear + 1 : "0" + (monthOfYear + 1)) + "/" + year);
            }
        };
        String s = txtvDate.getText() + "";
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
        // TODO: 6/4/2018 -- Add more info
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
}

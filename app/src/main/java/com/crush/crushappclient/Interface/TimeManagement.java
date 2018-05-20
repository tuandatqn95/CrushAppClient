package com.crush.crushappclient.Interface;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

public interface TimeManagement {
    TimeManagement dialogDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener);

    void showDatePickerDialog();

    TimeManagement dialogTimePicker(TimePickerDialog.OnTimeSetListener onTimeSetListener);

    void showTimePickerDialog();
}

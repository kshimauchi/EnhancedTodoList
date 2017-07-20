package com.kshimauchi.enhancedtodolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by kshim on 7/17/2017.
 */
public class AddToDoFragment extends DialogFragment {

    private EditText toDo;
    private DatePicker dp;
    private Button add;
      //another addition for interface mismatch params
    private final String TAG = "addtodofragment";

    public AddToDoFragment() {    }

    //To have a way for the activity to get the data from the dialog
    //closeDialog added another spinnerResult to help shut this up
    public interface OnDialogCloseListener {
        void closeDialog(int year, int month, int day, String description,String spinnerResult);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);
        toDo = (EditText) view.findViewById(R.id.toDo);
        dp = (DatePicker) view.findViewById(R.id.datePicker);
        add = (Button) view.findViewById(R.id.add);


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dp.updateDate(year, month, day);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDialogCloseListener activity = (OnDialogCloseListener) getActivity();
                activity.closeDialog(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), toDo.getText().toString(),
                    com.kshimauchi.enhancedtodolist.UpdateToDoFragment.spinnerResult);  //need is this  spinnerResult = (String) spinner.getSelectedItem();
                AddToDoFragment.this.dismiss();
            }
        });
        return view;
    }
}

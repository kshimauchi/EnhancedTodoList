package com.kshimauchi.enhancedtodolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by kshim on 7/17/2017.
 */

public class UpdateToDoFragment extends DialogFragment {
    private EditText toDo;
    private DatePicker dp;
    private Button add;
    //SpinneResult and spinner are needed
    public static String spinnerResult;  //to hold the choice made from spinner in the calendar to be added as a database columns
    public Spinner spinner;

    private final String TAG = "updatetodofragment";
    private long id;

    public UpdateToDoFragment(){}
    //adding the string spinnerResult here
    public static UpdateToDoFragment newInstance(int year, int month, int day, String descrpition,String spinnerResult, long id) {
        UpdateToDoFragment f = new UpdateToDoFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", month);
        args.putInt("day", day);
        args.putLong("id", id);
        args.putString("description", descrpition);
        //add the spinner result to
        args.putString("spinnerResult",spinnerResult);
        f.setArguments(args);
        return f;
    }
    //To have a way for the activity to get the data from the dialog
    //needed to add the String result of the spinner here as part of the interface
    public interface OnUpdateDialogCloseListener {
        void closeUpdateDialog(int year, int month, int day, String description,String spinnerResult, long id);
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);
        toDo = (EditText) view.findViewById(R.id.toDo);
        dp = (DatePicker) view.findViewById(R.id.datePicker);
        add = (Button) view.findViewById(R.id.add);
       //spinner added here locates the spinner in the xml file by id
        spinner =(Spinner) view.findViewById(R.id.spinner);

        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        id = getArguments().getLong("id");
        String description = getArguments().getString("description");
        spinnerResult = (String) spinner.getSelectedItem();
        dp.updateDate(year, month, day);
        toDo.setText(description);
        add.setText("Update");

       //setSelectedListener stores the spinner choice
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               //sets the default selection for the spinner
               spinner.setSelection(position);
               // work(0). school(1) home(2) appointments(3)
               spinnerResult = parentView.getItemAtPosition(position).toString();
               Log.d(TAG,"spinnerResult choice" + spinnerResult.toString());
           }
           //boilerplate as are many methods of an interface
           @Override
           public void onNothingSelected(AdapterView<?> parent) {}

       });
    //need to implment in main first
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateToDoFragment.OnUpdateDialogCloseListener activity = (UpdateToDoFragment.OnUpdateDialogCloseListener) getActivity();
                Log.d(TAG, "id: " + id);
                activity.closeUpdateDialog(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(),
                        toDo.getText().toString(),spinnerResult.toString(), id);
                UpdateToDoFragment.this.dismiss();
            }
        });
        return view;
    }
}

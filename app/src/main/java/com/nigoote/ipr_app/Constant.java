package com.nigoote.ipr_app;

import android.app.AlertDialog;
import android.content.ContentProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

public class Constant {
    public static String host = "http://192.168.1.69/ipr/";
    Context context;
    String mtitle ;

    public Constant(Context context, String mtitle) {
        this.context = context;
        this.mtitle = mtitle;
    }

    public void openDialog(String message){
        AlertDialog dlg = new AlertDialog.Builder(context)
                .setTitle(mtitle)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();;
                    }
                })
                .create();
        dlg.show();

    }

    public void cleanData(EditText editText1,EditText editText2){
        editText1.setText("");
        editText2.setText("");
    }


}

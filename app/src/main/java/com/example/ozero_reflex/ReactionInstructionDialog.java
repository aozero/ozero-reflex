package com.example.ozero_reflex;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Alexander Ozero on 26/09/2015.
 */
// https://developer.android.com/guide/topics/ui/dialogs.html 2015-09-26
public class ReactionInstructionDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("In this mode, your goal is to quickly react when the text changes" +
                " by tapping the button. When you close this dialog, the timer will begin.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Close the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

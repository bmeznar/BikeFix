package com.example.bikefix;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CommentDialog extends AppCompatDialogFragment {
    private EditText editTextComment;
    private CommentDialogListener listener;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog_comment,null);
        builder.setView(view)
                .setTitle("Add Comment")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String koment=editTextComment.getText().toString();
                        listener.applyText(koment);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        editTextComment=view.findViewById(R.id.komentar);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(CommentDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must add CommentDialogListener");
        }
    }

    public interface CommentDialogListener{
        void applyText(String comment);
    }
}

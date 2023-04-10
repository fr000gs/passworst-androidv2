package io.github.fr000gs.passworst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static native String hello(String pswd, String user);
    static {
        System.loadLibrary("passworstlib");
    }

    public static String output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void getHash(View mew) {
        EditText usertext = findViewById(R.id.editUser);
        EditText pswdtext = findViewById(R.id.editPswd);
        TextView tex1 = findViewById(R.id.textPswd);
        MainActivity.output = MainActivity.hello(String.valueOf(pswdtext.getText()), String.valueOf(usertext.getText()));
        tex1.setText(output);
    }

    public void copyHash(View mew) {
        Context context = getApplicationContext();
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", output);
        clipboard.setPrimaryClip(clip);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
            //Snackbar mySnackbar = Snackbar.make(findViewById(R.id.contLa), "Copied", Snackbar.LENGTH_SHORT);
            //mySnackbar.setAction(R.string.undo_string, new MyUndoListener());
            //mySnackbar.show();
        }
    }

    public void seeHash(View mew) {
        CheckBox cb = findViewById(R.id.checkBox);
        if (cb.isChecked()) {
            EditText pswdtext = findViewById(R.id.editPswd);
            pswdtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            pswdtext.setTypeface(Typeface.SANS_SERIF);
        }
        else {
            EditText pswdtext = findViewById(R.id.editPswd);
            pswdtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public void main(String[] args) {
    }
}
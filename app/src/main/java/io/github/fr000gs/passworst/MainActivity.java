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
//import android.widget.SeekBar;
//import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {
    public static String output = "";

    static {
        System.loadLibrary("passworstlib");
    }

    private static native String handle(String user, String pswd, int len, boolean type);

    public static void main(String[] args) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Slider slider = findViewById(R.id.slider);
        slider.addOnChangeListener(
                (
                        slider1, value, fromUser
                ) -> {
                    TextView c = findViewById(R.id.sliderValue);
                    c.setText(String.format("Length: %s", (int) value));
                    getHash();
                }
        );
        //findViewById(R.id.editUser)

    }

    public void clear(View view) {
        EditText a = findViewById(R.id.editUser);
        a.setText("");
        EditText b = findViewById(R.id.editPswd);
        b.setText("");
        TextView c = findViewById(R.id.textPswd);
        c.setText("");
        Slider slider = findViewById(R.id.slider);
        slider.setValue(12);
        CheckBox cb = findViewById(R.id.showBox);
        cb.setChecked(false);
        b.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    public void getHashView(View view) {
        getHash();
    }

    public void getHash() {
        EditText usertext = this.findViewById(R.id.editUser);
        EditText pswdtext = findViewById(R.id.editPswd);
        SwitchMaterial switchold = findViewById(R.id.switchOld);
        Slider slider = findViewById(R.id.slider);
        TextView tex1 = findViewById(R.id.textPswd);
        MainActivity.output = MainActivity.handle(String.valueOf(usertext.getText()),
                String.valueOf(pswdtext.getText()),
                (int) slider.getValue(),
                switchold.isChecked()
        );
        tex1.setText(output);
    }

    public void copyHash(View view) {
        getHash();
        Context context = getApplicationContext();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", output);
        clipboard.setPrimaryClip(clip);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
        } else {
            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.contLa), "Copied", Snackbar.LENGTH_SHORT);
            mySnackbar.show();
        }
    }

    public void seeHash(View view) {
        CheckBox cb = findViewById(R.id.showBox);
        if (cb.isChecked()) {
            EditText pswdtext = findViewById(R.id.editPswd);
            pswdtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            pswdtext.setTypeface(Typeface.SANS_SERIF);
        } else {
            EditText pswdtext = findViewById(R.id.editPswd);
            pswdtext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
}
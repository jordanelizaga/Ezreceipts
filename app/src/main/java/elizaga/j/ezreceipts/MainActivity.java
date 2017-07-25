package elizaga.j.ezreceipts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {

    private Guideline vertFiftyGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vertFiftyGuide = findViewById(R.id.main_50pVertGuide);

        /* Set onClickButtonListeners*/
        findViewById(R.id.main_saveBtn).setOnClickListener(this);

        ToggleButton hideImageBtn = findViewById(R.id.main_hideImageBtn);
        hideImageBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) vertFiftyGuide.getLayoutParams();
                if (checked) {
                    params.guidePercent = 0.5f;
                    vertFiftyGuide.setLayoutParams(params);
                } else {
                    params.guidePercent = 0.1f;
                    vertFiftyGuide.setLayoutParams(params);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_saveBtn:
                EditText total = findViewById(R.id.main_total);
                Log.v("myTag", "This button was clicked");
                writePostToTextFile(total.getText() + "\n With a newline \n");
                finish();
                break;
        }

    }

    public static boolean canWriteOnExternalStorage() {
        // get the state of your external storage
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // if storage is mounted return true
            Log.v("myTag", "Yes, can write to external storage.");
            return true;
        }
        return false;
    }

    public void writePostToTextFile(String str) {
        // get the path to sdcard
        // to this path add a new directory path
        if (canWriteOnExternalStorage()) {
            File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            //File dir = new File(sdcard);
            // create this directory if not already created
            //dir.mkdir();
            // create the file in which we will write the contents
            File file = new File(sdcard, "myTextFile.txt");
            try {
                FileOutputStream os = new FileOutputStream(file, false); // Boolean sets to append
                //String data = "This is the content of my file \n This should be a new line \n";
                //String data = "This should be appended \n This should be a new line\n";
                os.write(str.getBytes());
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

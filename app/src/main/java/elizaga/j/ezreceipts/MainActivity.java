package elizaga.j.ezreceipts;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    private Guideline vertFiftyGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vertFiftyGuide = findViewById(R.id.main_50pVertGuide);

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
}

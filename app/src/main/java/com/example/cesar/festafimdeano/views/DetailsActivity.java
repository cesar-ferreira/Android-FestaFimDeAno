package com.example.cesar.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.cesar.festafimdeano.R;
import com.example.cesar.festafimdeano.constants.FimDeAnoConstants;
import com.example.cesar.festafimdeano.util.SecurityPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.check_participate)
    CheckBox checkParticipate;

    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mSecurityPreferences = new SecurityPreferences(this);

        loadDataFromActivity();

    }

    @OnClick(R.id.check_participate)
    public void participate(View view) {

        if (checkParticipate.isChecked()){

            this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE,FimDeAnoConstants.CONFIRMED_WILL_GO);

        } else {

            this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE,FimDeAnoConstants.CONFIRMED_WONT_GO);

        }
    }

    private void loadDataFromActivity(){

        Bundle extras = getIntent().getExtras();

        if (extras != null){

            String presence = extras.getString(FimDeAnoConstants.PRESENCE);

            if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)){
                checkParticipate.setChecked(true);
            }else{
                checkParticipate.setChecked(false);
            }
        }
    }

}

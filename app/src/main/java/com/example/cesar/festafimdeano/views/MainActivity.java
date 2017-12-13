package com.example.cesar.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cesar.festafimdeano.R;
import com.example.cesar.festafimdeano.constants.FimDeAnoConstants;
import com.example.cesar.festafimdeano.util.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.text_today)
    TextView textToday;

    @BindView(R.id.text_days_left)
    TextView textDaysLeft;

    @BindView(R.id.button_confirm)
    Button buttonConfirm;

    private SecurityPreferences mSecurityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mSecurityPreferences = new SecurityPreferences(this);

        textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeftToEndOfYear()), getString(R.string.dias));
        textDaysLeft.setText(daysLeft);
    }

    @Override
    protected void onResume (){
        super.onResume();

        verifyPresence();
    }

    @OnClick(R.id.button_confirm)
    public void confirm(View view) {

        String presence = mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(FimDeAnoConstants.PRESENCE, presence);
        startActivity(intent);
    }

    private void verifyPresence(){

        String presence = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE);

        if (presence.equals(""))
        {
            buttonConfirm.setText(R.string.nao_confirmado);

        } else if(presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)){

            buttonConfirm.setText(R.string.sim);

        }else {

            buttonConfirm.setText(R.string.nao);

        }

    }

    private int getDaysLeftToEndOfYear(){

        Calendar calendar = Calendar.getInstance();

        int today = calendar.get(Calendar.DAY_OF_YEAR);
        int dayDecember31 = calendar.getMaximum(Calendar.DAY_OF_YEAR);

        return  dayDecember31 - today;

    }

}

package com.starkinfoinc.retrofitx;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.starkinfoinc.retrofitx.model.GitUser;
import com.starkinfoinc.retrofitx.rest.GitClient;
import com.starkinfoinc.retrofitx.rest.ServiceGenarator;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Declare Elements in Layout
    TextView responseText ;
    Button searchBtn ;
    ProgressBar progressBar ;
    EditText editText ;
    ImageView imageView1 ;
    //Declare GitClient Interface
    GitClient gitclient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CAST elements
        searchBtn     = (Button)         findViewById(R.id.main_btn_lookup);
        responseText  = (TextView)       findViewById(R.id.main_text_response);
        editText      = (EditText)       findViewById(R.id.main_edit_username);
        progressBar   = (ProgressBar)    findViewById(R.id.main_progress);
        imageView1    = (ImageView)      findViewById(R.id.avatar) ;

        //Set progress bar visibility to invisible
        progressBar.setVisibility(View.INVISIBLE);

        //implementation of the GitClient interface.
        gitclient  = ServiceGenarator.getClient().create(GitClient.class);

        //OnClick routine
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SET ProgressBar to Visible
                progressBar.setVisibility(View.VISIBLE);

                //Get username from EditText View
                String user =editText.getText().toString();

                //Create Call
                final Call<GitUser> call = gitclient.getdata(user);

                //Call Enqueue
                call.enqueue(new Callback<GitUser>() {
                    @Override
                    public void onResponse(Call<GitUser> call, Response<GitUser> response) {

                        GitUser gitmodel = response.body();
                        if(gitmodel !=null){
                            //Set textView
                            responseText.setText(getString(R.string.main_response_text,
                                    gitmodel.getName(),
                                    gitmodel.getBlog(),
                                    gitmodel.getBio(),
                                    gitmodel.getCompany()));

                            responseText.setTextSize(15);
                            responseText.setTextColor(Color.GREEN);
                            //Load Image
                            Picasso.get()
                                    .load(gitmodel.getAvatarurl())
                                    .resize(500,500)
                                    .centerCrop()
                                    .into(imageView1);
                        }
                        else {

                            responseText.setText("");
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.main_error_text),
                                    Toast.LENGTH_SHORT).show();

                            if (imageView1 != null){
                            imageView1.setImageDrawable(null);
                            }

                        //Hide progressbar after Complete
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    }
                    @Override
                    public void onFailure(@NotNull Call<GitUser> call, Throwable t) {

                        //   Display Error message When the request fails
                        responseText.setText("Error Loading "+t.fillInStackTrace().toString()); //Error needs to be handled properly
                        responseText.setTextColor(Color.RED);
                        responseText.setTextSize(20);

                            if (imageView1 != null){
                                imageView1.setImageDrawable(null);
                            }

                        //Hide progressbar after Complete
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });
    }
}

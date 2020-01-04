package com.taufiq.ayger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.taufiq.ayger.IntroSlider.PrefManager;
import com.taufiq.ayger.apihelper.BaseApiService;
import com.taufiq.ayger.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    BaseApiService mApiService;
    Context mContext;
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    ProgressDialog loading;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefManager= new PrefManager(LoginActivity.this);
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        initComponents();
        loading = new ProgressDialog(LoginActivity.this);
        if (prefManager.getSPSudahLogin()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();

        }
    }

    private void initComponents() {
        etEmail = (EditText) findViewById(R.id.edEmail);
        etPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestLogin();
            }
        });
    }

    private void requestLogin() {
        loading.show();
        mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")) {

                            // Jika login berhasil maka data nama yang ada di response API
                            // akan diparsing ke activity selanjutnya.
                            Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                            prefManager.saveSPString(prefManager.SP_NAMA, jsonRESULTS.getString("nama"));
                            prefManager.saveSPString(prefManager.SP_ID, jsonRESULTS.getString("iduser"));
                            // Shared Pref ini berfungsi untuk menjadi trigger session login
                            prefManager.saveSPBoolean(prefManager.SP_SUDAH_LOGIN, true);
//                            String nama = jsonRESULTS.getJSONObject("email").getString("nama");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            intent.putExtra("result_nama", nama);
                            startActivity(intent);
                        } else {
                            // Jika login gagal
                            String error_message = jsonRESULTS.getString("error_msg");
                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
                loading.dismiss();
            }
        });
    }
}

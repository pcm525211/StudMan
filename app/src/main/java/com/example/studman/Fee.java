package com.example.studman;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.payumoney.core.PayUmoneyConfig;
import com.payumoney.core.PayUmoneyConstants;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.sdkui.ui.utils.ResultModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;


public class Fee extends AppCompatActivity {



    private PayUmoneySdkInitializer.PaymentParam mPaymentParams;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    String URL = "https://www.leancerweb.com/studman/fee/index.php?";
    private ProgressDialog progressDialog;
    String courseid,userid,feeamount;
    TextView feea;

    MaterialButton btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee);
        progressDialog = new ProgressDialog(Fee.this);
        progressDialog.setMessage("Please wait...");
        btnPay = (MaterialButton) findViewById(R.id.btn_pay);
        feea = (TextView) findViewById(R.id.feeamount);


        settings = getSharedPreferences("settings", MODE_PRIVATE);
        editor = settings.edit();
        editor.putBoolean("is_prod_env", false);
        editor.apply();
        final SharedPreferences myshare = getSharedPreferences("userid",MODE_PRIVATE);
        userid = myshare.getString("userid","");

        checkFee(userid);
        progressDialog.show();
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                launchPayUMoney(feeamount);
            }
        });

    }

    private void checkFee(String userId){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL+"user_id="+userId, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            if(response.getString("status").equals("success") ){

                                if(response.getString("paid").equals("no")){
                                    courseid = response.getString("course_id");
                                    feeamount = response.getString("feeamount");
                                    feea.setText(feeamount);
                                    progressDialog.dismiss();
                                }else{
                                    showMsg(response.getString("msg"));
                                }

                            }else{
                                showMsg(response.getString("msg"));
                            }


                        }catch (JSONException je){}

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Fee.this, "here "+error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

    private void showMsg(String msg){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);


        builder.setTitle("Message");
        builder.setMessage(msg);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(Fee.this,MainActivity.class));
                finish();
            }
        });

        androidx.appcompat.app.AlertDialog dialog = builder.create();

        dialog.show();
    }

        private void launchPayUMoney(String feeamount){
            PayUmoneyConfig payUmoneyConfig = PayUmoneyConfig.getInstance();

            payUmoneyConfig.setDoneButtonText("Done");
            payUmoneyConfig.setPayUmoneyActivityTitle("Pay to Genius");

            PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();

            String txnId = System.currentTimeMillis() + "";
            String phone = "9876543210";
            String productName = "geniusproduct";
            String firstName = "test";
            String email = "test@example.com";
            String udf1 = "";
            String udf2 = "";
            String udf3 = "";
            String udf4 = "";
            String udf5 = "";
            String udf6 = "";
            String udf7 = "";
            String udf8 = "";
            String udf9 = "";
            String udf10 = "";

            builder.setAmount(feeamount)
                    .setTxnId(txnId)
                    .setPhone(phone)
                    .setProductName(productName)
                    .setFirstName(firstName)
                    .setEmail(email)
                    .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")
                    .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
                    .setUdf1(udf1)
                    .setUdf2(udf2)
                    .setUdf3(udf3)
                    .setUdf4(udf4)
                    .setUdf5(udf5)
                    .setUdf6(udf6)
                    .setUdf7(udf7)
                    .setUdf8(udf8)
                    .setUdf9(udf9)
                    .setUdf10(udf10)
                    .setIsDebug(true)
                    .setKey("vGo3fnZx")
                    .setMerchantId("6172433");

            try{
                mPaymentParams = builder.build();

                generateHashFromServer(mPaymentParams);






            }catch(Exception e){}
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // Result Code is -1 send from Payumoney activity
            Log.d("FeeActivity", "request code " + requestCode + " resultcode " + resultCode);
            if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
                    null) {
                TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager
                        .INTENT_EXTRA_TRANSACTION_RESPONSE);

                ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);

                // Check which object is non-null
                if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                    if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {

                        progressDialog.dismiss();

                        progressDialog.show();

                        saveToserver();

                    } else {
                        //Failure Transaction
                        Toast.makeText(Fee.this, "Fail", Toast.LENGTH_SHORT).show();
                        showMsg("Sorry Payment Fail");
                    }


                } else if (resultModel != null && resultModel.getError() != null) {
                    Log.d("PAYU", "Error response : " + resultModel.getError().getTransactionResponse());
                } else {
                    Log.d("PAYU", "Both objects are null!");
                }
            }
        }

        public void generateHashFromServer(PayUmoneySdkInitializer.PaymentParam paymentParam) {
            //nextButton.setEnabled(false); // lets not allow the user to click the button again and again.

            HashMap<String, String> params = paymentParam.getParams();

            // lets create the post params
            StringBuffer postParamsBuffer = new StringBuffer();
            postParamsBuffer.append(concatParams(PayUmoneyConstants.KEY, params.get(PayUmoneyConstants.KEY)));
            postParamsBuffer.append(concatParams(PayUmoneyConstants.AMOUNT, params.get(PayUmoneyConstants.AMOUNT)));
            postParamsBuffer.append(concatParams(PayUmoneyConstants.TXNID, params.get(PayUmoneyConstants.TXNID)));
            postParamsBuffer.append(concatParams(PayUmoneyConstants.EMAIL, params.get(PayUmoneyConstants.EMAIL)));
            postParamsBuffer.append(concatParams("productInfo", params.get(PayUmoneyConstants.PRODUCT_INFO)));
            postParamsBuffer.append(concatParams("firstName", params.get(PayUmoneyConstants.FIRSTNAME)));
            postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF1, params.get(PayUmoneyConstants.UDF1)));
            postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF2, params.get(PayUmoneyConstants.UDF2)));
            postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF3, params.get(PayUmoneyConstants.UDF3)));
            postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF4, params.get(PayUmoneyConstants.UDF4)));
            postParamsBuffer.append(concatParams(PayUmoneyConstants.UDF5, params.get(PayUmoneyConstants.UDF5)));

            String postParams = postParamsBuffer.charAt(postParamsBuffer.length() - 1) == '&' ? postParamsBuffer.substring(0, postParamsBuffer.length() - 1).toString() : postParamsBuffer.toString();

            // lets make an api call
            GetHashesFromServerTask getHashesFromServerTask = new GetHashesFromServerTask();
            getHashesFromServerTask.execute(postParams);
        }


        protected String concatParams(String key, String value) {
            return key + "=" + value + "&";
        }

        /**
         * This AsyncTask generates hash from server.
         */
        private class GetHashesFromServerTask extends AsyncTask<String, String, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();


                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... postParams) {

                String merchantHash = "";
                try {
                    //TODO Below url is just for testing purpose, merchant needs to replace this with their server side hash generation url
                    URL url = new URL("https://www.leancerweb.com/studman/hash.php");

                    String postParam = postParams[0];

                    byte[] postParamsByte = postParam.getBytes("UTF-8");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length", String.valueOf(postParamsByte.length));
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(postParamsByte);

                    InputStream responseInputStream = conn.getInputStream();
                    StringBuffer responseStringBuffer = new StringBuffer();
                    byte[] byteContainer = new byte[1024];
                    for (int i; (i = responseInputStream.read(byteContainer)) != -1; ) {
                        responseStringBuffer.append(new String(byteContainer, 0, i));
                    }

                    JSONObject response = new JSONObject(responseStringBuffer.toString());


                    Log.d("PAYUDEBUG", response.getString("hashtest"));
                    Iterator<String> payuHashIterator = response.keys();
                    while (payuHashIterator.hasNext()) {
                        String key = payuHashIterator.next();
                        switch (key) {
                            /**
                             * This hash is mandatory and needs to be generated from merchant's server side
                             *
                             */
                            case "result":
                                merchantHash = response.getString(key);
                                break;
                            default:
                                break;
                        }
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return merchantHash;
            }

            @Override
            protected void onPostExecute(String merchantHash) {
                super.onPostExecute(merchantHash);

                progressDialog.dismiss();



                if (merchantHash.isEmpty() || merchantHash.equals("")) {
                    Toast.makeText(Fee.this, "Could not generate hash", Toast.LENGTH_SHORT).show();
                } else {
                    mPaymentParams.setMerchantHash(merchantHash);

                    PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams,Fee.this,R.style.AppTheme_default,true);
                }
            }
        }

        private  void saveToserver(){
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    URL+"user_id="+userid+"&course_id="+courseid+"&feeamount="+feeamount, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try{
                                progressDialog.dismiss();
                                if(response.getString("status").equals("success") ){
                                    showMsg(response.getString("msg"));

                                }else{
                                    showMsg(response.getString("msg"));
                                }
                            }catch (JSONException je){}

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Fee.this, "here "+error.toString(), Toast.LENGTH_LONG).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjReq);
        }
}

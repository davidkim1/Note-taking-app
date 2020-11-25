package com.example.notetaking;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Server {

    private final RequestQueue QUEUE;
    public static final String URL = ""; //REPLACE WITH YOUR SERVER URL
    private String token;

    public Server(Context context) {
        // Instantiate the RequestQueue.
        QUEUE = Volley.newRequestQueue(context);

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) { this.token = token; }

    public Request forgotPassword(final String email, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) throws JSONException {

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "forgotPassword");
        jsonBody.put("email", email);


        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener) {

            //Here we override the header creation method of the Request class
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                //Token
                headers.put("autho_token", getToken());
                return headers;
            }
        };

        return QUEUE.add(jsonPostRequest);
    }

    public Request setDocument(String id, String text, String title, String date, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) throws JSONException {

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "setDocument");
        JSONObject doc = new JSONObject();
        doc.put("id", id);
        doc.put("text", text);
        doc.put("creation_date", date);
        doc.put("title", title);
        jsonBody.put("document", doc);

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener) {

            //Here we override the header creation method of the Request class
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                //Token
                headers.put("autho_token", getToken());
                return headers;
            }
        };

        return QUEUE.add(jsonPostRequest);
    }

    public Request setDocumentAccessors(String accessor, String docId, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) throws JSONException {

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "setDocumentAccessors");
        jsonBody.put("document_id", docId);
        ArrayList<String> list = new ArrayList<String>();
        list.add(accessor);
        jsonBody.put("accessors", new JSONArray(list));

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener) {

            //Here we override the header creation method of the Request class
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                //Token
                headers.put("autho_token", getToken());
                return headers;
            }
        };

        return QUEUE.add(jsonPostRequest);
    }


    public Request getDocument(Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) throws JSONException {

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "getDocument");
        jsonBody.put("document_id", "708b973f-245e-44b0-ad64-d26899e5c406");


        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener) {

            //Here we override the header creation method of the Request class
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                 //Token
                headers.put("autho_token", getToken());
                return headers;
            }
        };

        return QUEUE.add(jsonPostRequest);
    }

    public Request deleteDoc(Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) throws JSONException {

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "deleteDocument");
        jsonBody.put("document_id", "708b973f-245e-44b0-ad64-d26899e5c406");


        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener) {

            //Here we override the header creation method of the Request class
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                //Token
                headers.put("autho_token", getToken());
                return headers;
            }
        };

        return QUEUE.add(jsonPostRequest);
    }

    public Request authenticate(final String email, final String password, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) throws JSONException {

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "authenticate");
        jsonBody.put("password", password);
        jsonBody.put("email", email);
        jsonBody.put("time_unit", TimeUnit.MINUTES.name());
        jsonBody.put("time_span", 60);

        Log.d("login_app", email+"  "+password);

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener);

        return QUEUE.add(jsonPostRequest);
    }

    public Request createAccount(final String email, final String password, final String firstname,
                                 final String lastname,
                                 Response.Listener<JSONObject> successListener,
                                 Response.ErrorListener errorListener) throws JSONException
    {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "createAccount");
        jsonBody.put("password", password);
        jsonBody.put("email", email);
        jsonBody.put("first_name", firstname);
        jsonBody.put("last_name", lastname);

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener);

        return QUEUE.add(jsonPostRequest);
    }

    public Request registerAccount(final String email, final String password, final String tempPass,
                                 Response.Listener<JSONObject> successListener,
                                 Response.ErrorListener errorListener) throws JSONException
    {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "registerAccount");
        jsonBody.put("password", password);
        jsonBody.put("temp_password", tempPass);
        jsonBody.put("email", email);


        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener);

        return QUEUE.add(jsonPostRequest);
    }

    public Request deleteAccount(Response.Listener<JSONObject> successListener,
                                 Response.ErrorListener errorListener) throws JSONException
    {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "deleteAccount");

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, successListener, errorListener) {

            //Here we override the header creation method of the Request class
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                //Token
                headers.put("autho_token", getToken());//put your token here
                return headers;
            }
        };

        return QUEUE.add(jsonPostRequest);
    }

}

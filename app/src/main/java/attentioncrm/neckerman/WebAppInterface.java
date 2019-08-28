package attentioncrm.neckerman;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

class WebAppInterface {
    public static final String USER_CREDENTIALS = "userCredentials";
    private final Context mContext;

    private static final String TAG = WebAppInterface.class.getName();

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }


    @JavascriptInterface
        public void saveCredentials(String usr, String pwd) {
            Log.d(TAG, "saveCredentials: " + usr + " pass: " + pwd);
            if (Objects.equals(usr, "") && Objects.equals(pwd, "")) {
                return;
            }

            //save the values in SharedPrefs
            SharedPreferences.Editor editor = mContext.getSharedPreferences(USER_CREDENTIALS,MODE_PRIVATE).edit();
            editor.putString("usr", usr);
            editor.putString("pwd", pwd);
            editor.apply();
            loginSuccessFully();

        }

    @JavascriptInterface
    public void clearCredentials() {
        Log.d(TAG, "clearCredentials: ");

        //save the values in SharedPrefs
        SharedPreferences.Editor editor = mContext.getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE).edit();
        editor.putString("usr", "");
        editor.putString("pwd", "");
        editor.apply();

        Toast.makeText(mContext, "Invalid credentials", Toast.LENGTH_LONG).show();
    }
    @JavascriptInterface
    public void loginSuccessFully(){
        Toast.makeText(mContext, "Successful login", Toast.LENGTH_LONG).show();
    }


}
package uz.pdp.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public boolean isBackPressedOnce = false;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WebView webView = findViewById(R.id.webView);      //WebView ni e'lon qilish uchun
        webView.getSettings().setJavaScriptEnabled(true);  //JavaScript ga ruxsat berish va web saytni mobil ilovaga o'tkazish
        webView.loadUrl("https://www.google.com/");        //Kerakli saytni yozish uchun

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        };
        webView.setWebViewClient(webViewClient);
    }


    public void onBackPressed(){    // 2 marta Back ( < ) ni bosganda dasturdan chiqib ketish  uchun
        if (isBackPressedOnce){
            super.onBackPressed();
            return;
        }

        this.isBackPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();  //Ogohlantrish uchun signal ya'ni eslatma chiqarish  Toast(); show(); metodi yordamida
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressedOnce = false;
            }
        },2000);
    }
}

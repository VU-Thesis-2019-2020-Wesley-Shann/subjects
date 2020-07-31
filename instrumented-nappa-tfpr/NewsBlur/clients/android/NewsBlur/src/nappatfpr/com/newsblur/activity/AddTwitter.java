package nappatfpr.com.newsblur.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.network.APIConstants;
import nl.vu.cs.s2group.nappa.*;

public class AddTwitter extends NbActivity {

	public static final int TWITTER_AUTHED = 0x20;
	private WebView webview;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getLifecycle().addObserver(new NappaLifecycleObserver(this));
		setContentView(R.layout.activity_webcontainer);
		
		webview = (WebView) findViewById(R.id.webcontainer);
		webview.getSettings().setJavaScriptEnabled(true);
		
		webview.setWebViewClient(new WebViewClient() {
            // this was deprecated in API 24 but the replacement only added in the same release.
            // the suppression can be removed when we move past 24
            @SuppressWarnings("deprecation")
		    public boolean shouldOverrideUrlLoading(WebView view, String url){
		    	if (TextUtils.equals(url, APIConstants.buildUrl("/"))) {
		    		AddTwitter.this.setResult(TWITTER_AUTHED);
		    		AddTwitter.this.finish();
		    		return true;
		    	}
		        view.loadUrl(url);
		        return false;
		   }
		});
		
		webview.loadUrl(APIConstants.buildUrl(APIConstants.PATH_CONNECT_TWITTER));
	}
	
}

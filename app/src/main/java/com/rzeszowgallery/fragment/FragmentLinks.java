package com.rzeszowgallery.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rzeszowgallery.R;

public class FragmentLinks extends Fragment {

    private View view;
    private WebView webView;
    private WebSettings webSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_links, container, false);
        webView = view.findViewById(R.id.links_web_view);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.links_toolbar_title);
        LinksImgControl();

        return view;
    }

    private void LinksImgControl() {
        ImageView linkBtn1 = view.findViewById(R.id.links_img_1);
        ImageView linkBtn2 = view.findViewById(R.id.links_img_2);

        final LinearLayout navigationLayout = view.findViewById(R.id.links_navigation_layout);
        final LinearLayout webLayout = view.findViewById(R.id.links_web_layout);

        linkBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationLayout.setVisibility(View.GONE);
                webLayout.setVisibility(View.VISIBLE);
                webView.loadUrl(getResources().getString(R.string.links_subtitle_1));
                webView.setWebViewClient(new MyWebViewClient());
            }
        });

        linkBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigationLayout.setVisibility(View.GONE);
                webLayout.setVisibility(View.VISIBLE);
                webView.loadUrl(getResources().getString(R.string.links_subtitle_2));
                webView.setWebViewClient(new MyWebViewClient());
            }
        });
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals(getResources().getString(R.string.links_subtitle_1))) {
                return false;
            } else if(Uri.parse(url).getHost().equals(getResources().getString(R.string.links_subtitle_2))) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}

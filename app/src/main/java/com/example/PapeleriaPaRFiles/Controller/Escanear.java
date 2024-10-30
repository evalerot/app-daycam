package com.example.PapeleriaPaRFiles.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Escanear extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView escanerZXing;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ZXingScannerView zXingScannerView = new ZXingScannerView(this);
        this.escanerZXing = zXingScannerView;
        setContentView((View) zXingScannerView);
    }

    public void onResume() {
        super.onResume();
        this.escanerZXing.setResultHandler(this);
        this.escanerZXing.startCamera();
    }

    public void onPause() {
        super.onPause();
        this.escanerZXing.stopCamera();
    }

    public void handleResult(Result result) {
        String text = result.getText();
        Intent intent = new Intent();
        intent.putExtra("codigo", text);
        setResult(-1, intent);
        finish();
    }
}

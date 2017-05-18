package com.jitosoft.qrpay.presentation.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.jitosoft.qrpay.R;
import com.jitosoft.qrpay.presentation.util.LogUtils;
import com.jitosoft.qrpay.presentation.util.QRCodeGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QrCodeActivity extends AppCompatActivity {

    static String QRCODE_DATA = "QRCODE_DATA";
    @BindView(R.id.qrcode)
    ImageView imageView;

    public static void start(Context context, String jsonData) {
        Intent intent = new Intent(context, QrCodeActivity.class);
        intent.putExtra(QRCODE_DATA, jsonData);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);

        String data = ((String) getIntent().getSerializableExtra(QRCODE_DATA));


        // TODO: 2017. 5. 18. move to presenter
        try {
            QRCodeGenerator.encodeAsBitmap(data, 300, 300)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(bitmap -> imageView.setImageBitmap(bitmap));

        } catch (WriterException e) {
            LogUtils.error(QrCodeActivity.class.getName(), e.toString());
        }
    }
}

package com.unithon.york.activity.contact;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.unithon.york.BuildConfig;
import com.unithon.york.R;
import com.unithon.york.activity.BaseActivity;
import com.unithon.york.activity.SendDoneActivity;
import com.unithon.york.util.CConfig;
import com.unithon.york.util.Utils;

import java.util.Random;

public class ContactsListActivity extends FragmentActivity implements ContactsListFragment.OnContactsInteractionListener {

    private static final String TAG = "ContactsListActivity";

    private boolean isTwoPaneLayout;

    private boolean isSearchResultView = false;

    Context mContext;
    String pill_type;
    String smsMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        mContext = this;

        BaseActivity.add(this);
        pill_type = getIntent().getStringExtra(CConfig.PILL_TYPE);
        isTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);

        Log.e("@@@@", pill_type);
        String[] a = null;

        switch (pill_type) {
            case "KANGWON_CANCER":
                a = getResources().getStringArray(R.array.KANGWON_CANCER);
                break;
            case "KANGWON_HYPER":
                a = getResources().getStringArray(R.array.KANGWON_HYPER);
                break;
            case "KANGWON_BREAK":
                a = getResources().getStringArray(R.array.KANGWON_BREAK);
                break;
            case "KANGWON_HEART":
                a = getResources().getStringArray(R.array.KANGWON_HEART);
                break;
            case "KANGWON_VIBRIO":
                a = getResources().getStringArray(R.array.KANGWON_VIBRIO);
                break;
            case "KANGWON_RES":
                a = getResources().getStringArray(R.array.KANGWON_RES);
                break;

            case "KYUNGSANG_CANCER":
                a = getResources().getStringArray(R.array.KYUNGSANG_CANCER);
                break;
            case "KYUNGSANG_HYPER":
                a = getResources().getStringArray(R.array.KYUNGSANG_HYPER);
                break;
            case "KYUNGSANG_BREAK":
                a = getResources().getStringArray(R.array.KYUNGSANG_BREAK);
                break;
            case "KYUNGSANG_HEART":
                a = getResources().getStringArray(R.array.KYUNGSANG_HEART);
                break;
            case "KYUNGSANG_VIBRIO":
                a = getResources().getStringArray(R.array.KYUNGSANG_VIBRIO);
                break;
            case "KYUNGSANG_RES":
                a = getResources().getStringArray(R.array.KYUNGSANG_RES);
                break;

            case "JUNLLA_CANCER":
                a = getResources().getStringArray(R.array.JUNLLA_CANCER);
                break;
            case "JUNLLA_HYPER":
                a = getResources().getStringArray(R.array.JUNLLA_HYPER);
                break;
            case "JUNLLA_BREAK":
                a = getResources().getStringArray(R.array.JUNLLA_BREAK);
                break;
            case "JUNLLA_HEART":
                a = getResources().getStringArray(R.array.JUNLLA_HEART);
                break;
            case "JUNLLA_VIBRIO":
                a = getResources().getStringArray(R.array.JUNLLA_VIBRIO);
                break;
            case "JUNLLA_RES":
                a = getResources().getStringArray(R.array.JUNLLA_RES);
                break;

            case "CHUNGCUNG_CANCER":
                a = getResources().getStringArray(R.array.CHUNGCUNG_CANCER);
                break;
            case "CHUNGCUNG_HYPER":
                a = getResources().getStringArray(R.array.CHUNGCUNG_HYPER);
                break;
            case "CHUNGCUNG_BREAK":
                a = getResources().getStringArray(R.array.CHUNGCUNG_BREAK);
                break;
            case "CHUNGCUNG_HEART":
                a = getResources().getStringArray(R.array.CHUNGCUNG_HEART);
                break;
            case "CHUNGCUNG_VIBRIO":
                a = getResources().getStringArray(R.array.CHUNGCUNG_VIBRIO);
                break;
            case "CHUNGCUNG_RES":
                a = getResources().getStringArray(R.array.CHUNGCUNG_RES);
                break;

            case "JEJU_CANCER":
                a = getResources().getStringArray(R.array.JEJU_CANCER);
                break;
            case "JEJU_HYPER":
                a = getResources().getStringArray(R.array.JEJU_HYPER);
                break;
            case "JEJU_BREAK":
                a = getResources().getStringArray(R.array.JEJU_BREAK);
                break;
            case "JEJU_HEART":
                a = getResources().getStringArray(R.array.JEJU_HEART);
                break;
            case "JEJU_VIBRIO":
                a = getResources().getStringArray(R.array.JEJU_VIBRIO);
                break;
            case "JEJU_RES":
                a = getResources().getStringArray(R.array.JEJU_RES);
                break;
        }

        int r = 0;
        try {
            Random random = new Random();
            r = random.nextInt(a.length);
            smsMessage = a[r];
        } catch (Exception e) {
            e.printStackTrace();
            r = 0;
            a = getResources().getStringArray(R.array.JEJU_CANCER);
            smsMessage = a[r];
        }


        isTwoPaneLayout = getResources().getBoolean(R.bool.has_two_panes);
    }

    @Override
    public void onContactSelected(Uri contactUri) {
        final String phoneNumber = retrieveContactNumber(contactUri);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(Html.fromHtml("<font color=\"#ff0000\">" + "주의" + "</font>")).setMessage("이 문자를 통해 생기는 싸움, 불신, 불화는 저희가 책임지지 않습니다.\n" + "그래도 보내시겠습니까?").setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendSMS(phoneNumber, smsMessage + "\n\n친구와 현피를 뜨고싶다면 욕처방을 설치하세요!");
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();

    }

    public void sendSMS(String smsNumber, String smsText) {
        PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT_ACTION"), 0);
        PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED_ACTION"), 0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        // 전송 성공
                        //Toast.makeText(mContext, "전송 완료", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(mContext, SendDoneActivity.class));
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        // 전송 실패
                        Toast.makeText(mContext, "전송 실패", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        // 서비스 지역 아님
                        Toast.makeText(mContext, "서비스 지역이 아닙니다", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        // 무선 꺼짐
                        Toast.makeText(mContext, "무선(Radio)가 꺼져있습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        // PDU 실패
                        Toast.makeText(mContext, "PDU Null", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter("SMS_SENT_ACTION"));

        SmsManager mSmsManager = SmsManager.getDefault();
        mSmsManager.sendTextMessage(smsNumber, null, smsText, sentIntent, deliveredIntent);
    }

    @Override
    public void onSelectionCleared() {
    }

    @Override
    public boolean onSearchRequested() {
        return !isSearchResultView && super.onSearchRequested();
    }

    private String retrieveContactNumber(Uri uriContact) {

        String contactNumber = null;
        String contactID = null;

        // getting contacts ID
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }

        cursorID.close();

        Log.d(TAG, "Contact ID: " + contactID);

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        cursorPhone.close();

        return contactNumber;
    }
}

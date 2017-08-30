package com.example.ahmed.whatsapp.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.whatsapp.R;
//impoimport com.google.android.gms.common.ConnectionResult;
import com.example.ahmed.whatsapp.adapters.CountryAdapter;
import com.example.ahmed.whatsapp.model.Country;
import com.example.ahmed.whatsapp.other.CustomPhoneNumberFormattingTextWatcher;
import com.example.ahmed.whatsapp.other.OnPhoneChangedListener;
import com.example.ahmed.whatsapp.other.PhoneUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.example.ahmed.whatsapp.model.User;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 23/08/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RadioButton.OnCheckedChangeListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    protected static final TreeSet<String> CANADA_CODES = new TreeSet<String>();
    protected static final TreeSet<String> US_CODES = new TreeSet<String>();
    protected static final TreeSet<String> DO_CODES = new TreeSet<String>();
    protected static final TreeSet<String> PR_CODES = new TreeSet<String>();
    final static String KEY_NEW_ = "KEY_NEW_";
    final static String KEY_NEW_USER = "KEY_NEW_USER";
    final static int KEY_MODE = Context.MODE_PRIVATE;
    static final int REQUEST_IMAGE_CAPTURE = 200;
    private static final int SELECT_PHOTO = 100;
    private static Uri selectedImage;
    private static InputStream imageStream;
    private static String mCurrentPhotoPath;

    static {
        //USA
        US_CODES.add("201");
        US_CODES.add("202");
        US_CODES.add("203");
        US_CODES.add("205");
        US_CODES.add("206");
        US_CODES.add("207");
        US_CODES.add("208");
        US_CODES.add("209");
        US_CODES.add("210");
        US_CODES.add("212");
        US_CODES.add("213");
        US_CODES.add("214");
        US_CODES.add("215");
        US_CODES.add("216");
        US_CODES.add("217");
        US_CODES.add("218");
        US_CODES.add("219");
        US_CODES.add("224");
        US_CODES.add("225");
        US_CODES.add("228");
        US_CODES.add("229");
        US_CODES.add("231");
        US_CODES.add("234");
        US_CODES.add("239");
        US_CODES.add("240");
        US_CODES.add("248");
        US_CODES.add("251");
        US_CODES.add("252");
        US_CODES.add("253");
        US_CODES.add("254");
        US_CODES.add("256");
        US_CODES.add("260");
        US_CODES.add("262");
        US_CODES.add("267");
        US_CODES.add("269");
        US_CODES.add("270");
        US_CODES.add("276");
        US_CODES.add("281");
        US_CODES.add("301");
        US_CODES.add("302");
        US_CODES.add("303");
        US_CODES.add("304");
        US_CODES.add("305");
        US_CODES.add("307");
        US_CODES.add("308");
        US_CODES.add("309");
        US_CODES.add("310");
        US_CODES.add("312");
        US_CODES.add("313");
        US_CODES.add("314");
        US_CODES.add("315");
        US_CODES.add("316");
        US_CODES.add("317");
        US_CODES.add("318");
        US_CODES.add("319");
        US_CODES.add("320");
        US_CODES.add("321");
        US_CODES.add("323");
        US_CODES.add("325");
        US_CODES.add("330");
        US_CODES.add("334");
        US_CODES.add("336");
        US_CODES.add("337");
        US_CODES.add("339");
        US_CODES.add("347");
        US_CODES.add("351");
        US_CODES.add("352");
        US_CODES.add("360");
        US_CODES.add("361");
        US_CODES.add("386");
        US_CODES.add("401");
        US_CODES.add("402");
        US_CODES.add("404");
        US_CODES.add("405");
        US_CODES.add("406");
        US_CODES.add("407");
        US_CODES.add("408");
        US_CODES.add("409");
        US_CODES.add("410");
        US_CODES.add("412");
        US_CODES.add("413");
        US_CODES.add("414");
        US_CODES.add("415");
        US_CODES.add("417");
        US_CODES.add("419");
        US_CODES.add("423");
        US_CODES.add("425");
        US_CODES.add("430");
        US_CODES.add("432");
        US_CODES.add("434");
        US_CODES.add("435");
        US_CODES.add("440");
        US_CODES.add("443");
        US_CODES.add("469");
        US_CODES.add("478");
        US_CODES.add("479");
        US_CODES.add("480");
        US_CODES.add("484");
        US_CODES.add("501");
        US_CODES.add("502");
        US_CODES.add("503");
        US_CODES.add("504");
        US_CODES.add("505");
        US_CODES.add("507");
        US_CODES.add("508");
        US_CODES.add("509");
        US_CODES.add("510");
        US_CODES.add("512");
        US_CODES.add("513");
        US_CODES.add("515");
        US_CODES.add("516");
        US_CODES.add("517");
        US_CODES.add("518");
        US_CODES.add("520");
        US_CODES.add("530");
        US_CODES.add("540");
        US_CODES.add("541");
        US_CODES.add("551");
        US_CODES.add("559");
        US_CODES.add("561");
        US_CODES.add("562");
        US_CODES.add("563");
        US_CODES.add("567");
        US_CODES.add("570");
        US_CODES.add("571");
        US_CODES.add("573");
        US_CODES.add("574");
        US_CODES.add("575");
        US_CODES.add("580");
        US_CODES.add("585");
        US_CODES.add("586");
        US_CODES.add("601");
        US_CODES.add("602");
        US_CODES.add("603");
        US_CODES.add("605");
        US_CODES.add("606");
        US_CODES.add("607");
        US_CODES.add("608");
        US_CODES.add("609");
        US_CODES.add("610");
        US_CODES.add("612");
        US_CODES.add("614");
        US_CODES.add("615");
        US_CODES.add("616");
        US_CODES.add("617");
        US_CODES.add("618");
        US_CODES.add("619");
        US_CODES.add("620");
        US_CODES.add("623");
        US_CODES.add("626");
        US_CODES.add("630");
        US_CODES.add("631");
        US_CODES.add("636");
        US_CODES.add("641");
        US_CODES.add("646");
        US_CODES.add("650");
        US_CODES.add("651");
        US_CODES.add("660");
        US_CODES.add("661");
        US_CODES.add("662");
        US_CODES.add("678");
        US_CODES.add("682");
        US_CODES.add("701");
        US_CODES.add("702");
        US_CODES.add("703");
        US_CODES.add("704");
        US_CODES.add("706");
        US_CODES.add("707");
        US_CODES.add("708");
        US_CODES.add("712");
        US_CODES.add("713");
        US_CODES.add("714");
        US_CODES.add("715");
        US_CODES.add("716");
        US_CODES.add("717");
        US_CODES.add("718");
        US_CODES.add("719");
        US_CODES.add("720");
        US_CODES.add("724");
        US_CODES.add("727");
        US_CODES.add("731");
        US_CODES.add("732");
        US_CODES.add("734");
        US_CODES.add("740");
        US_CODES.add("754");
        US_CODES.add("757");
        US_CODES.add("760");
        US_CODES.add("763");
        US_CODES.add("765");
        US_CODES.add("770");
        US_CODES.add("772");
        US_CODES.add("773");
        US_CODES.add("774");
        US_CODES.add("775");
        US_CODES.add("781");
        US_CODES.add("785");
        US_CODES.add("786");
        US_CODES.add("801");
        US_CODES.add("802");
        US_CODES.add("803");
        US_CODES.add("804");
        US_CODES.add("805");
        US_CODES.add("806");
        US_CODES.add("808");
        US_CODES.add("810");
        US_CODES.add("812");
        US_CODES.add("813");
        US_CODES.add("814");
        US_CODES.add("815");
        US_CODES.add("816");
        US_CODES.add("817");
        US_CODES.add("818");
        US_CODES.add("828");
        US_CODES.add("830");
        US_CODES.add("831");
        US_CODES.add("832");
        US_CODES.add("843");
        US_CODES.add("845");
        US_CODES.add("847");
        US_CODES.add("848");
        US_CODES.add("850");
        US_CODES.add("856");
        US_CODES.add("857");
        US_CODES.add("858");
        US_CODES.add("859");
        US_CODES.add("860");
        US_CODES.add("862");
        US_CODES.add("863");
        US_CODES.add("864");
        US_CODES.add("865");
        US_CODES.add("866");
        US_CODES.add("870");
        US_CODES.add("901");
        US_CODES.add("903");
        US_CODES.add("904");
        US_CODES.add("906");
        US_CODES.add("907");
        US_CODES.add("908");
        US_CODES.add("909");
        US_CODES.add("910");
        US_CODES.add("912");
        US_CODES.add("913");
        US_CODES.add("914");
        US_CODES.add("915");
        US_CODES.add("916");
        US_CODES.add("917");
        US_CODES.add("918");
        US_CODES.add("919");
        US_CODES.add("920");
        US_CODES.add("925");
        US_CODES.add("928");
        US_CODES.add("931");
        US_CODES.add("936");
        US_CODES.add("937");
        US_CODES.add("940");
        US_CODES.add("941");
        US_CODES.add("947");
        US_CODES.add("949");
        US_CODES.add("951");
        US_CODES.add("952");
        US_CODES.add("954");
        US_CODES.add("956");
        US_CODES.add("970");
        US_CODES.add("971");
        US_CODES.add("972");
        US_CODES.add("973");
        US_CODES.add("978");
        US_CODES.add("979");
        US_CODES.add("980");
        US_CODES.add("985");
        US_CODES.add("989");

        //Dominican Republic
        DO_CODES.add("809");
        DO_CODES.add("829");
        DO_CODES.add("849");

        //Puerto Rico
        PR_CODES.add("787");
        PR_CODES.add("939");

        //Canada
        CANADA_CODES.add("204");
        CANADA_CODES.add("226");
        CANADA_CODES.add("236");
        CANADA_CODES.add("249");
        CANADA_CODES.add("250");
        CANADA_CODES.add("289");
        CANADA_CODES.add("306");
        CANADA_CODES.add("343");
        CANADA_CODES.add("365");
        CANADA_CODES.add("387");
        CANADA_CODES.add("403");
        CANADA_CODES.add("416");
        CANADA_CODES.add("418");
        CANADA_CODES.add("431");
        CANADA_CODES.add("437");
        CANADA_CODES.add("438");
        CANADA_CODES.add("450");
        CANADA_CODES.add("506");
        CANADA_CODES.add("514");
        CANADA_CODES.add("519");
        CANADA_CODES.add("548");
        CANADA_CODES.add("579");
        CANADA_CODES.add("581");
        CANADA_CODES.add("587");
        CANADA_CODES.add("604");
        CANADA_CODES.add("613");
        CANADA_CODES.add("639");
        CANADA_CODES.add("647");
        CANADA_CODES.add("672");
        CANADA_CODES.add("705");
        CANADA_CODES.add("709");
        CANADA_CODES.add("742");
        CANADA_CODES.add("778");
        CANADA_CODES.add("780");
        CANADA_CODES.add("782");
        CANADA_CODES.add("807");
        CANADA_CODES.add("819");
        CANADA_CODES.add("825");
        CANADA_CODES.add("867");
        CANADA_CODES.add("873");
        CANADA_CODES.add("902");
        CANADA_CODES.add("905");
    }

    protected SparseArray<ArrayList<Country>> mCountriesMap = new SparseArray<ArrayList<Country>>();
    protected PhoneNumberUtil mPhoneNumberUtil = PhoneNumberUtil.getInstance();
    protected String mLastEnteredPhone;
    protected CountryAdapter mAdapter;
    String urldisplay = "https://store-images.s-microsoft.com/image/apps.2614.9007199266251178.704f160d-aa1e-4d06-a6fb-2bdaead4cad1.ce965467-bd60-4ccf-a423-81e513c59500?w=180&h=180&q=60";
    //    @BindView(R.id.etUserEmail)
    EditText etUserEmail;
    //    @BindView(R.id.etUserPassword)
    EditText etUserPassword;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;
    View.OnClickListener retryListheneer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //retry internet connection
            retry();
        }
    };
    ////-----------------------------------------for registeration
    EditText etEmail = null, etPassword = null, etConfirmPassword = null, etName = null, etShownWords = null;
    RadioButton rbFemale = null, rbMale = null;
    TextView tvBirthday = null;
    ImageView ivUploadImage = null;
    /////////////////////////////////
    View headerView = null;
    String strGender = "";
    AlertDialog alertDialog = null;
    AlertDialog.Builder builder = null;
    String myFormat = "MM/dd/yy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    Calendar myCalendar = Calendar.getInstance();
    int year = myCalendar.get(Calendar.YEAR);
    int month = myCalendar.get(Calendar.MONTH);
    int day = myCalendar.get(Calendar.DAY_OF_MONTH);
    boolean set = false;//for birthday
    DatePickerDialog datePickerDialog = null;
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            tvBirthday.setTextColor(Color.WHITE);
            tvBirthday.setText(sdf.format(myCalendar.getTime()));

            if (datePickerDialog != null && datePickerDialog.isShowing()) {
                datePickerDialog.dismiss();
            }

            set = true;
        }

    };
    String SiteKey = "";
    String SecretKey = "";
    ImageView ivCaptcha = null;
    EditText etForgEmail = null;
    EditText etPhone = null;
    Spinner spinnerCountryCodes = null;
    protected AdapterView.OnItemSelectedListener mOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Country c = (Country) spinnerCountryCodes.getItemAtPosition(position);
            if (mLastEnteredPhone != null && mLastEnteredPhone.startsWith(c.getCountryCodeStr())) {
                return;
            }
            etPhone.getText().clear();
            etPhone.getText().insert(etPhone.getText().length() > 0 ? 1 : 0, String.valueOf(c.getCountryCode()));
            etPhone.setSelection(etPhone.length());
            mLastEnteredPhone = null;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
    protected OnPhoneChangedListener mOnPhoneChangedListener = new OnPhoneChangedListener() {
        @Override
        public void onPhoneChanged(String phone) {
            try {
                mLastEnteredPhone = phone;
                Phonenumber.PhoneNumber p = mPhoneNumberUtil.parse(phone, null);
                ArrayList<Country> list = mCountriesMap.get(p.getCountryCode());
                Country country = null;
                if (list != null) {
                    if (p.getCountryCode() == 1) {
                        String num = String.valueOf(p.getNationalNumber());
                        if (num.length() >= 3) {
                            String code = num.substring(0, 3);
                            if (CANADA_CODES.contains(code)) {
                                for (Country c : list) {
                                    // Canada has priority 2, US has priority 1
                                    if (c.getPriority() == 2) {
                                        country = c;
                                        break;
                                    }
                                }
                            } else if (DO_CODES.contains(code)) {
                                for (Country c : list) {
                                    // Dominican Republic has priority 3
                                    if (c.getPriority() == 3) {
                                        country = c;
                                        break;
                                    }
                                }
                            } else if (PR_CODES.contains(code)) {
                                for (Country c : list) {
                                    // Puerto Rico has priority 4
                                    if (c.getPriority() == 4) {
                                        country = c;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (country == null) {
                        //If no country code is entered, default to US
                        for (Country c : list) {
                            if (c.getPriority() == 1) {
                                country = c;
                                break;
                            }
                        }
                    }
                }
                if (country != null) {
                    final int position = country.getNum();
                    spinnerCountryCodes.post(new Runnable() {
                        @Override
                        public void run() {
                            spinnerCountryCodes.setSelection(position);
                        }
                    });
                }
            } catch (NumberParseException ignore) {
            }

        }
    };
    boolean bo = true;
    Button btnMale, btnFemale = null;
    LinearLayout llMale, llFemale = null;
    boolean bMale = true, bFemale = true;
    View.OnClickListener clkFemale = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (bFemale) {
                bFemale = false;
                //btnFemale.setPressed(true);
                btnFemale.setBackgroundResource(R.drawable.correct_answer);
                strGender = "Female";
                if (bMale == false) {
                    btnMale.setBackgroundResource(R.drawable.question);
                    bMale = true;
                }
                genderCanotBeChanged();
            } else {
                //btnFemale.setPressed(false);
                btnFemale.setBackgroundResource(R.drawable.question);
                bFemale = true;
            }
        }
    };
    View.OnClickListener clkMale = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (bMale) {
                bMale = false;
                //btnFemale.setPressed(true);
                btnMale.setBackgroundResource(R.drawable.correct_answer);
                strGender = "Male";
                if (bFemale == false) {
                    btnFemale.setBackgroundResource(R.drawable.question);
                    bFemale = true;
                }
                genderCanotBeChanged();
            } else {
                //btnFemale.setPressed(false);
                btnMale.setBackgroundResource(R.drawable.question);
                bMale = true;
            }
        }
    };
    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername = "", mPhotoUrl = "";
    private DrawerLayout drawerLayout = null;
    private NavigationView navigationView = null;
    private Bitmap mImageBitmap = null;
    private GoogleApiClient mGoogleApiClient;
    View.OnClickListener RqsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

//            SiteKey = getResources().getString(R.string.YOUR_API_SITE_KEY);
//            SecretKey = getResources().getString(R.string.YOUR_API_SECRET_KEY);

            mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                    .addApi(SafetyNet.API)
                    .addConnectionCallbacks(LoginActivity.this)
                    .addOnConnectionFailedListener(LoginActivity.this)
                    .build();

            mGoogleApiClient.connect();

            SafetyNet.SafetyNetApi.verifyWithRecaptcha(mGoogleApiClient, SiteKey)
                    .setResultCallback(new ResultCallback<SafetyNetApi.zzd>() {
                        @Override
                        public void onResult(@NonNull SafetyNetApi.zzd zzd) {
                            Status status = zzd.getStatus();

                            if ((status != null) && status.isSuccess()) {

                                fireToast("Success");
                                // Indicates communication with reCAPTCHA service was
                                // successful. Use result.getTokenResult() to get the
                                // user response token if the user has completed
                                // the CAPTCHA.
//
//                                if (!zzd.getTokenResult().isEmpty()) {
//                                    fireToast("!result.getTokenResult().isEmpty()");
//                                    // User response token must be validated using the
//                                    // reCAPTCHA site verify API.
//                                }else{
//                                    fireToast("result.getTokenResult().isEmpty()");
//                                }
                            } else {

                                Log.e("MY_APP_TAG", "Error occurred " +
                                        "when communicating with the reCAPTCHA service.");

                                fireToast("Error occurred " +
                                        "when communicating with the reCAPTCHA service.");

                                // Use status.getStatusCode() to determine the exact
                                // error code. Use this code in conjunction with the
                                // information in the "Handling communication errors"
                                // section of this document to take appropriate action
                                // in your app.
                            }
                        }

                    });

        }
    };

    // Another option is the built in Patterns starting with API Level 8:
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Toast.makeText(getApplicationContext(), "SayHi", Toast.LENGTH_SHORT).show();

        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(KEY_NEW_USER, KEY_MODE);
        editor = sharedPreferences.edit();

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (sharedPreferences.contains(KEY_NEW_)) {
            setContentView(R.layout.activity_main2);
            init1();
            init2();
        } else {
            setContentView(R.layout.activity_splash_screen);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setContentView(R.layout.activity_main2);
                    init1();
                    init2();
                    editor.putString(KEY_NEW_, "new");
                    editor.commit();
                }
            }, 5000);
        }


    }

    private void init1() {
        etUserPassword = (EditText) findViewById(R.id.etUserPassword);
        etUserEmail = (EditText) findViewById(R.id.etUserEmail);
        drawerLayout = (DrawerLayout) findViewById(R.id.regDrawerLayout);
        navigationView = (NavigationView) drawerLayout.findViewById(R.id.regNavView);

        //fit the navigationview all screen
        //change the width of the NavigationView programatically:
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) navigationView.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        navigationView.setLayoutParams(params);


    }

    private void init2() {


        //back button of the toolbar
        //navigationView.getHeaderView(0)
        headerView = LayoutInflater.from(this).inflate(R.layout.activity_register, navigationView, false);
        navigationView.addHeaderView(headerView);


        View view = headerView.findViewById(R.id.svReg).findViewById(R.id.llReg);

        etEmail = (EditText) view.findViewById(R.id.etRegEmail);
        etPassword = (EditText) view.findViewById(R.id.etRegPassword);
        etConfirmPassword = (EditText) view.findViewById(R.id.etRegConfPass);

        ivCaptcha = (ImageView) view.findViewById(R.id.llNotRobot).findViewById(R.id.ivCaptcha);
        etShownWords = (EditText) view.findViewById(R.id.llNotRobot).findViewById(R.id.etRegWordsShown);

        tvBirthday = (TextView) view.findViewById(R.id.llDOB).findViewById(R.id.tvBirthDay);
        ivUploadImage = (ImageView) view.findViewById(R.id.llPhoto).findViewById(R.id.ivRegUploadPhoto).findViewById(R.id.ivRegUploadPhoto);

//        rbFemale = (RadioButton) view.findViewById(R.id.llGender).findViewById(R.id.rgReg).findViewById(R.id.rbRegFemale);
//        rbMale = (RadioButton) view.findViewById(R.id.llGender).findViewById(R.id.rgReg).findViewById(R.id.rbRegMale);
        btnFemale = (Button) view.findViewById(R.id.llGender).findViewById(R.id.llFemale).findViewById(R.id.btnFemale);
        btnMale = (Button) view.findViewById(R.id.llGender).findViewById(R.id.llMale).findViewById(R.id.btnMale);
        llMale = (LinearLayout) view.findViewById(R.id.llGender).findViewById(R.id.llMale);
        llFemale = (LinearLayout) view.findViewById(R.id.llGender).findViewById(R.id.llFemale);
        ////////////////////////////


        llFemale.setOnClickListener(clkFemale);
        llMale.setOnClickListener(clkMale);
        btnMale.setOnClickListener(clkMale);
        btnFemale.setOnClickListener(clkFemale);

//        ((RadioGroup) view.findViewById(R.id.llGender).findViewById(R.id.rgReg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                RadioButton rbMale = (RadioButton) group.findViewById(R.id.rbRegMale);
//                if (!rbMale.isChecked()) {
//
//                    rbMale.setChecked(true);
//                } else {
//                    rbMale.setChecked(false);
//                }
//            }
//        });

        ivUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPersonalPhoto();
            }
        });


        view.findViewById(R.id.llDOB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDialog.THEME_DEVICE_DEFAULT_DARK
                //AlertDialog.THEME_DEVICE_DEFAULT_LIGHT
                datePickerDialog = new DatePickerDialog(LoginActivity.this, /*DatePickerDialog.THEME_HOLO_LIGHT,*/ onDateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("Birthday");
                datePickerDialog.setMessage("Input your birthday. it won't be seen by others");
                datePickerDialog.setCanceledOnTouchOutside(false);
                datePickerDialog.setCancelable(false);
                datePickerDialog.show();
            }
        });

        //-----------------------------------------------------------------------------
        View view2 = headerView//co
                .findViewById(R.id.appBarLayout)
                .findViewById(R.id.toolbar)
                .findViewById(R.id.rlReg)
                .findViewById(R.id.closeDrawerLayout);
//        Toast.makeText(getApplicationContext(), view.getTag().toString()
//                , Toast.LENGTH_SHORT).show();
        view2.setOnClickListener(this);

    }

    public void genderCanotBeChanged() {
        new AlertDialog.Builder(this)
                .setTitle("Gender")
                .setMessage("Gender can't be changed once set")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }

    public void uploadPersonalPhoto() {

        View view = LayoutInflater.from(this).inflate(R.layout.layout_upload_photo, null);
        view.findViewById(R.id.llUpload).findViewById(R.id.btnGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fireToast("Gallery");
                fromGallery();
            }
        });
        view.findViewById(R.id.llUpload).findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fireToast("Cam");
                fromCam();
            }
        });

        builder = new AlertDialog.Builder(this);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        if (!alertDialog.isShowing())
            alertDialog.show();


    }

    private void fromCam() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void fromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(photoPickerIntent, "Set avatar"), SELECT_PHOTO);
    }

    public void fireToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        selectedImage = data.getData();
                        imageStream = getContentResolver().openInputStream(selectedImage);
                        mImageBitmap = BitmapFactory.decodeStream(imageStream);
                        ivUploadImage.setImageBitmap(mImageBitmap);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        if (alertDialog.isShowing())
                            alertDialog.dismiss();
                    }
//                    ivPersonalIamge.setVisibility(View.VISIBLE);
//                    ivPersonalIamge.invalidate();
                }
                break;
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    try {
                        mImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),
                                Uri.parse(mCurrentPhotoPath));
                        ivUploadImage.setImageBitmap(mImageBitmap);
                        //ivUploadImage.invalidate();
                        // new FragmentRegister().setmImageBitmap(mImageBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (alertDialog.isShowing())
                            alertDialog.dismiss();
                    }
                }
                break;

        }
    }

    /**
     * Create formatted image file in phone storage for the future use
     *
     * @return Camera Image file
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, // prefix
                ".jpg", // suffix
                storageDir // directory
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(getApplicationContext(), "Close Drawer", Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(Gravity.RIGHT);

        //clear all inputs again
//        etName.setText("");
//        etPassword.setText("");
//        etConfirmPassword.setText("");
//        etEmail.setText("");
//        etShownWords.setText("");
//        rbMale.setChecked(false);
//        rbFemale.setChecked(false);
    }

    public void valiAndVeri() {
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please, Enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        //check if email already has be used by current use during the registartion
        if (TextUtils.equals(etEmail.getText().toString().trim(), mFirebaseAuth.getCurrentUser() == null ? "" : mFirebaseUser.getEmail().toString())) {
            etEmail.setError("Email already exists. Please specify another email. if this email belongs to you, please login directly", getResources().getDrawable(R.drawable.round_error_symbol));
            return;
        }
        if (!isValidEmail(etEmail.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please, Enter valid email !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please, Input login password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etPassword.getText().toString().trim().length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.equals(etPassword.getText(), etConfirmPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (etName.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please, Enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!rbFemale.isChecked() && !rbMale.isChecked()) {
            Toast.makeText(getApplicationContext(), "Please select your gender", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mImageBitmap == null) {
            Toast.makeText(getApplicationContext(), "Please set your avatar first", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!set) {
            Toast.makeText(getApplicationContext(), "Input your birthday. it won't be seen by others", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void registerNewUser(View view) {
        //Toast.makeText(getApplicationContext(), "Register", Toast.LENGTH_SHORT).show();


        //valiAndVeri();
//        if (!isInternetAvailable()) {
//            fireToast("No internet connection !!!!");
//            return;
//        }

        //ok let's go
//        String email = etEmail.getText().toString().trim(), password = etPassword.getText().toString().trim();
//        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            mFirebaseUser = mFirebaseAuth.getCurrentUser();
//                            fireToast("Registered Successfully, U can login now");
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            fireToast("Authentication failed." + task.getException());
//
//                        }
//                    }
//                });


        saveInDatabase();


    }
    FirebaseDatabase firebaseDatabase = null;
    DatabaseReference firebaseDatabaseRef = null;
    private void saveInDatabase() {
        //init
        //firebaseDatabase = FirebaseDatabase.getInstance();
        // It automatically stores the data offline when there is no internet connection. When the device connects to internet,
        // all the data will be pushed to realtime database.
        //to enable Firebase's offline mode before any database references are used
        //firebaseDatabase.setPersistenceEnabled(true);
        //In order to perform any operation on to database whether it can be read or write, you need to get the reference to database first
        //firebaseDatabaseRef = firebaseDatabase.getReference();
        ///////////////////////////////
        //processing

//        String userId = firebaseDatabaseRef.push().getKey();
//        User user = new User();
//        firebaseDatabaseRef.child(userId).setValue(user).addOnCompleteListener(this, new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                fireToast("inserted");
//            }
//        });


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.push().setValue(1);
        //This will append the new value at the end of the list.
        //By combining the push() and child() methods, you can create multiple lists of children in the database:
        mDatabase.child("numbers").push().setValue(1);
        mDatabase.child("numbers").push().setValue(53);
        mDatabase.child("numbers").push().setValue(42);

        mDatabase.child("letters").push().setValue("a");
        mDatabase.child("letters").push().setValue("z");
        mDatabase.child("letters").push().setValue("c");

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public void retry() {
        if (isInternetAvailable()) {
            //Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
            //hiding the no internet conn layout
            //linearLayout.setVisibility(LinearLayout.GONE);

            //downloading the image from internet then set as bitmap for imageview
            //new DownloadImageTask(imageView).execute(urldisplay);


        } else {
            //Toast.makeText(getApplicationContext(), "Not Connected", Toast.LENGTH_SHORT).show();
            //imageView.setVisibility(View.INVISIBLE);
            //Snackbar.make(relativeLayout, "No internet connection", Snackbar.LENGTH_LONG).setAction("Retry", retryListheneer).show();
            return;
        }
    }

    //    This method actually checks if device is connected to internet(There is a possibility it
//            's connected to a network but not to internet).
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();


        editor.putString(KEY_NEW_, "new");
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        editor.putString(KEY_NEW_, "new");
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check if user is signed in (non-null) and update UI accordingly.
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            etUserEmail.setText(mFirebaseUser.getEmail().toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser != null) {
            etUserEmail.setText(mFirebaseUser.getEmail().toString());
        }
    }

    public void btnForgotPassword(View view) {

        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_forgot_pass, null);
        view1.findViewById(R.id.btnEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.forget_email, null);
                etForgEmail = ((EditText) view2.findViewById(R.id.etForgetEmail));
                view2.findViewById(R.id.llForget).findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                view2.findViewById(R.id.llForget).findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetPasswordByEmail();
                    }
                });
                fireAlertDialog(view2);
            }
        });
        view1.findViewById(R.id.btnMobileNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.forget_mobile_number, null);
                View view3 = view2.findViewById(R.id.ll);
                etPhone = (EditText) view3.findViewById(R.id.phone);
                spinnerCountryCodes = (Spinner) view3.findViewById(R.id.spinner);
                initCountryCodes();
                fireAlertDialog(view2);
            }
        });

        fireAlertDialog(view1);


    }

    private void initCountryCodes() {
        spinnerCountryCodes.setOnItemSelectedListener(mOnItemSelectedListener);
        mAdapter = new CountryAdapter(getApplicationContext());
        spinnerCountryCodes.setAdapter(mAdapter);
        if (bo) {
            new AsyncPhoneInitTask(getApplicationContext()).execute();
            bo = !bo;
        }

        ////////////////////
        etPhone.addTextChangedListener(new CustomPhoneNumberFormattingTextWatcher(mOnPhoneChangedListener));
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (dstart > 0 && !Character.isDigit(c)) {
                        return "";
                    }
                }
                return null;
            }
        };
        etPhone.setFilters(new InputFilter[]{filter});
        etPhone.setImeOptions(EditorInfo.IME_ACTION_SEND);
        etPhone.setImeActionLabel(/*getString(R.string.label_send)*/"Send", EditorInfo.IME_ACTION_SEND);
        etPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    fireToast("send");
                    return true;
                }
                return false;
            }
        });
    }

    public void resetPasswordByEmail() {
        String email = etForgEmail.getText().toString().trim();
        if (email.length() == 0) {
            fireToast("Enter email to reset the password");
            return;
        }
        if (!isValidEmail(email)) {
            etForgEmail.setError("Enter valid email"/*, getResources().getDrawable(R.drawable.round_error_symbol)*/);
            return;
        }

        alertDialog.dismiss();

        //otherwise
        //ok
        mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    fireToast("Email sent successfully, Go ! and reset it now ..");
                } else {
                    fireToast("Error, try again later");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }

    public void fireAlertDialog(View view) {
        builder = new AlertDialog.Builder(this);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(false);
        if (!alertDialog.isShowing())
            alertDialog.show();
    }

    public void btnNewUser(View view) {
        //finish();
        //or
//        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
//        startActivity(intent);
      /*  Calling overridePendingTransition after the call to startActivity will ensure that
        the newly started Activity runs the enterAnimationId and
        the current Activity runs the exitAnimationId.*/
        //overridePendingTransition(R.anim.slide_from_right, 0);


        //To Open:
        drawerLayout.openDrawer(Gravity.RIGHT);


    }

    public void startShakeAnim(View view) {
        Animation animWobble = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake1);
        view.startAnimation(animWobble);

    }

    public void btnLogin(View view) {
        //check if fields are empty or invalid then conform user
        if (etUserEmail.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
            startShakeAnim(findViewById(R.id.btnLogin));
            etUserEmail.requestFocus();
            return;
        }
        if (!isValidEmail(etUserEmail.getText().toString())) {
            startShakeAnim(findViewById(R.id.btnLogin));
            Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
            etUserEmail.requestFocus();
            return;
        }
        if (etUserPassword.getText().toString().trim().length() == 0) {
            startShakeAnim(findViewById(R.id.btnLogin));
            Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
            etUserPassword.requestFocus();
            return;
        }

//        if (etUserPassword.getText().toString().trim().length() < 6) {
//            Toast.makeText(getApplicationContext(), "Password must greater than 6 characters", Toast.LENGTH_SHORT).show();
//            return;
//        }

        //next step
        //checking internet connection
        if (isInternetAvailable()) {
            Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            return;
        }

        //next step
        //checking if user registered
//        if (mFirebaseUser == null) {
//            // Not signed in, launch the Sign In activity
//            Toast.makeText(getApplicationContext(), "Invalid email or password, Please Register", Toast.LENGTH_SHORT).show();
//        } else {
////            mUsername = mFirebaseUser.getDisplayName();
////            if (mFirebaseUser.getPhotoUrl() != null) {
////                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
////            }
//            Toast.makeText(getApplicationContext(), mUsername, Toast.LENGTH_SHORT).show();
//        }


        final ProgressDialog dialog = fireProgressDialog(this, "Please wait..", "Please Wait ...");
        dialog.show();

        String email = etUserEmail.getText().toString().trim(), password = etUserPassword.getText().toString().trim();
        //authenticate user
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    // there was an error
                    fireToast("Invalid username or password");
                } else {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });


    }

    public ProgressDialog fireProgressDialog(Context context, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
//        progressDialog.show();
        return progressDialog;

    }

    public void btnLoginViaSocialNetwork(View view) {

    }

    public void closeDrawerLayout(View view) {
//        Toast.makeText(getApplicationContext(), "Close Darwer", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(this, "onConnected()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,
                "onConnectionSuspended: " + i,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,
                "onConnectionFailed():\n" + connectionResult.getErrorMessage(),
                Toast.LENGTH_LONG).show();
    }

    protected class AsyncPhoneInitTask extends AsyncTask<Void, Void, ArrayList<Country>> {

        private int mSpinnerPosition = -1;
        private Context mContext;

        public AsyncPhoneInitTask(Context context) {
            mContext = context;
        }

        @Override
        protected ArrayList<Country> doInBackground(Void... params) {
            ArrayList<Country> data = new ArrayList<Country>(233);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(mContext.getApplicationContext().getAssets().open("countries.dat"), "UTF-8"));

                // do reading, usually loop until end of file reading
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    //process line
                    Country c = new Country(mContext, line, i);
                    data.add(c);
                    ArrayList<Country> list = mCountriesMap.get(c.getCountryCode());
                    if (list == null) {
                        list = new ArrayList<Country>();
                        mCountriesMap.put(c.getCountryCode(), list);
                    }
                    list.add(c);
                    i++;
                }
            } catch (IOException e) {
                //log the exception
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        //log the exception
                    }
                }
            }
            String countryRegion = PhoneUtils.getCountryRegionFromPhone(mContext);
            int code = mPhoneNumberUtil.getCountryCodeForRegion(countryRegion);
            ArrayList<Country> list = mCountriesMap.get(code);
            if (list != null) {
                for (Country c : list) {
                    if (c.getPriority() == 0 || c.getPriority() == 1) {
                        mSpinnerPosition = c.getNum();
                        break;
                    }
                }
            }
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<Country> data) {
            if (data != null) {
                mAdapter.addAll(data);
                if (mSpinnerPosition > 0) {
                    spinnerCountryCodes.setSelection(mSpinnerPosition);
                }
            }
        }
    }

    class MyClickListhener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    //setting image url for imagview if internet connection is enabled
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}

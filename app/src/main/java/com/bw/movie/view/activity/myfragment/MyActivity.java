package com.bw.movie.view.activity.myfragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.bean.HeadPicBean;
import com.bw.movie.modle.utils.DateUtil;
import com.bw.movie.modle.version.DateListener;
import com.bw.movie.persenter.Persenter;
import com.bw.movie.view.activity.MainActivity;
import com.bw.movie.view.base.BaseActivity;
import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.contract.IViewContract;
import com.bw.movie.view.mi.EncryptUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyActivity extends BaseActivity implements IViewContract.doView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.text_my_imag)
    SimpleDraweeView textMyImag;
    @BindView(R.id.text_my_name)
    TextView textMyName;
    @BindView(R.id.text_my_xing)
    TextView textMyXing;
    @BindView(R.id.text_my_data)
    TextView textMyData;
    @BindView(R.id.text_my_email)
    TextView textMyEmail;
    @BindView(R.id.but_shoot)
    Button butShoot;
    @BindView(R.id.but_photo)
    Button butPhoto;
    @BindView(R.id.linear_gone)
    LinearLayout linearGone;
    @BindView(R.id.text_my_phone)
    TextView textMyPhone;
    @BindView(R.id.liner_phone)
    LinearLayout linerPhone;
    @BindView(R.id.edit_text_edit)
    EditText editTextEdit;
    @BindView(R.id.but_versi)
    Button butVersion;
    @BindView(R.id.liner_phone_hl)
    LinearLayout linerPhoneHl;

    private String s;
    private Map<String, Object> map;
    private String email;
    private String phone;

    //需要的权限数组 读/写/相机
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA };
    private Persenter persenter;


    @Override
    protected int initLayout() {
        return R.layout.activity_my;
    }

    @Override
    protected BasePersenter initPersenter() {
        Persenter persenter = (Persenter) basePersenter;
        return persenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        detailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences sp = getSharedPreferences("feil", Context.MODE_PRIVATE);
        email = sp.getString("email", " ");
        String pwd = sp.getString("pwd", "");
        String encrypt = EncryptUtil.encrypt(pwd);
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("pwd", encrypt);
        persenter = new Persenter(this);
        persenter.doGuild(map);
    }

    @Override
    public void onLogCurress(Object obj) {
        final GuideBean guideBean = (GuideBean) obj;
        GuideBean.ResultBean.UserInfoBean userInfo = guideBean.result.userInfo;
        textMyImag.setImageURI(userInfo.headPic);
        textMyName.setText(userInfo.nickName);
        textMyEmail.setText(email);
        textMyPhone.setText(userInfo.phone);

        int sex = userInfo.sex;
        if (sex == 1) {
            textMyXing.setText("男");
        } else if (sex == 2) {
            textMyXing.setText("女");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String tida = format.format(userInfo.birthday);
        textMyData.setText(tida);
        textMyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtil.setYearDate(MyActivity.this, new DateListener() {
                    @Override
                    public void setYear(String year) {

                    }

                    @Override
                    public void setMonth(String month) {

                    }

                    @Override
                    public void setDay(String day) {

                    }

                    @Override
                    public void setMouthDate(String year, String month) {

                    }

                    @Override
                    public void setYearDate(String year, String month, String day) {
                        s = year + "-" + month + "-" + day;
                        Log.i("qq", "setYearDate: " + s);
                        GuideBean.ResultBean result = guideBean.result;
                        map = new HashMap<>();
                        map.put("userId", result.userId);
                        map.put("sessionId", result.sessionId);
                        Map<String, String> smap = new HashMap<>();
                        smap.put("birthday", s);
                        Persenter persenter = new Persenter(MyActivity.this);
                        persenter.doBirthday(map, smap);
                    }
                });
            }
        });
//        显示选择路径
        textMyImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearGone.setVisibility(View.VISIBLE);
            }
        });
//        相机
        butShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查是否已经获得相机的权限
                if(verifyPermissions(MyActivity.this,PERMISSIONS_STORAGE[2]) == 0){

                    ActivityCompat.requestPermissions(MyActivity.this, PERMISSIONS_STORAGE, 3);
                }else{
                    //已经有权限
                    Intent intent = new Intent ( MediaStore.ACTION_IMAGE_CAPTURE );
                    startActivityForResult ( intent,101 );
                }


     }
        });
//        相册
        butPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
        linerPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linerPhoneHl.setVisibility(View.VISIBLE);
                linerPhone.setVisibility(View.GONE);
            }
        });
        butVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> omap = new HashMap<>();
                omap.put("userId", guideBean.result.userId);
                omap.put("sessionId", guideBean.result.sessionId);
                phone = editTextEdit.getText().toString();
                Map<String, String> smap = new HashMap<>();
                smap.put("phone", phone);
                Persenter persenter = new Persenter(MyActivity.this);
                persenter.doUserPhone(omap, smap);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断返回码不等于0
        if (requestCode != RESULT_CANCELED) {    //RESULT_CANCELED = 0(也可以直接写“if (requestCode != 0 )”)
            //读取返回码
            switch (requestCode) {
                case 100:   //相册返回的数据（相册的返回码）
                    Uri uri01 = data.getData();
                    Log.i("aaa", "onActivityResult: "+uri01);
                    String[] str = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver ().query ( uri01, str, null, null, null );
                    int columnIndexOrThrow = cursor.getColumnIndexOrThrow ( MediaStore.Images.Media.DATA );
                    cursor.moveToFirst ();
                    String path = cursor.getString ( columnIndexOrThrow );
                    File file = new File ( path );
                    RequestBody requestBody = RequestBody.create ( MediaType.parse ( "multipart/form-data" ), file );
                    MultipartBody.Part formData = MultipartBody.Part.createFormData ( "image", file.getName (), requestBody );
                    persenter.doloadHeadPic(map,formData);
                    break;
                case 101:  //相机返回的数据（相机的返回码）
                    try {
                        File file1 = new File(Environment.getExternalStorageDirectory(), "fileImg.jpg");//相机取图片数据文件
                        RequestBody requestBody1 = RequestBody.create ( MediaType.parse ( "multipart/form-data" ), file1 );
                        MultipartBody.Part formData1 = MultipartBody.Part.createFormData ( "image", file1.getName (), requestBody1 );
                        persenter.doloadHeadPic(map,formData1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public void onShapeCurress(Object obj) {
        HeadPicBean headPicBean = (HeadPicBean) obj;
        String headPath = headPicBean.headPath;
        textMyImag.setImageURI(headPath);
        linearGone.setVisibility(View.GONE);
    }

    @Override
    public void onMyCurress(Object obj) {
        textMyData.setText(s);
    }

    @Override
    public void onBannerCurress(Object obj) {
        linerPhoneHl.setVisibility(View.GONE);
        linerPhone.setVisibility(View.VISIBLE);
        textMyPhone.setText(phone);
    }

    @Override
    public void onMovieCinema(Object obj) {

    }

    @Override
    public void onLogExurr(String str) {
        Log.i("aaa", "onLogExurr: "+str);
    }
}

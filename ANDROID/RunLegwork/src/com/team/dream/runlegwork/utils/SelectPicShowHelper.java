package com.team.dream.runlegwork.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;





import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.team.dream.runlegwork.R;


/**
 * Created by 秋平 on 2015/10/23.
 */
public class SelectPicShowHelper {

    public static final int REQUESTCODE_PICTURE = 1;// 相册
    public static final int REQUESTCODE_CAMERA = 0;// 相机
    public static final int PHOTO_REQUEST_GALLERY = 10;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 11;// 结果
    public static final int IMAGE_CODE = 3;   //这里的IMAGE_CODE是自己任意定义的

    private final String IMAGE_TYPE = "image/*";
    // 建立图片目录
    public final File PICTURE_DIR = new File(Environment.getExternalStorageDirectory() + "/huochebao/myImage");
    public static File mFileUri;

    private List<String> mSelectPic = new ArrayList<String>();
    private SelectPicPopupWindow mPopupWindow;
    private Activity context;
    private String path;
    private boolean isNeedCrop = true;

    public SelectPicShowHelper(Activity context, boolean isNeedCrop) {
        this.context = context;
        mSelectPic.add("拍照");
        mSelectPic.add("从手机相册选择");
        mSelectPic.add("取消");
        initPopupWindow();
        this.isNeedCrop = isNeedCrop;
    }

    public void initPopupWindow() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_window_listview, null);
        mPopupWindow = new SelectPicPopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, mSelectPic);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 1.0f;
                context.getWindow().setAttributes(lp);
            }
        });
        mPopupWindow.setOnItemSelected(new SelectPicPopupWindow.IOnItemSelected() {
            @Override
            public void onItemSelected(int position) {
                mPopupWindow.dismiss();
                switch (position) {
                    case 0:
                        getImageFromCamera();
                        break;
                    case 1:
                        if (isNeedCrop) {
                            Intent intent = new Intent(Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            context.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                        } else {
                            Intent getAlbum = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            getAlbum.setType(IMAGE_TYPE);
                            context.startActivityForResult(getAlbum, IMAGE_CODE);
                        }
                        break;

                }
            }
        });
    }

    private static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    public void showWindow() {
        mPopupWindow.setAnimationStyle(R.style.anim_popup_dir);
        mPopupWindow.showAtLocation(getRootView(context), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = .3f;
        context.getWindow().setAttributes(lp);
    }

    private void getImageFromCamera() {
        String sdstate = Environment.getExternalStorageState();
        if (sdstate.equals(Environment.MEDIA_MOUNTED)) {
            if (!PICTURE_DIR.exists()) {
                PICTURE_DIR.mkdirs();
            }
            String mPictureName = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            mFileUri = new File(PICTURE_DIR, mPictureName);
            Uri uri = Uri.fromFile(mFileUri);
            Bundle bundle = new Bundle();
            bundle.putString("path", path);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFileUri));
            context.startActivityForResult(intent, REQUESTCODE_CAMERA);
        } else {
        	ToastUtils.show(context, "无sd卡");
        }

    }

    public void getImgRequsetAndCorp(int size) {
        try {
            Uri uri = null;
            if (mFileUri == null) {
                return;
            }
            uri = Uri.fromFile(mFileUri);
            Intent picCorp = new Intent("com.android.camera.action.CROP");
            picCorp.setDataAndType(uri, "image/*");
            picCorp.putExtra("crop", "true");
            picCorp.putExtra("aspectX", 1);
            picCorp.putExtra("aspectY", 1);
            picCorp.putExtra("outputX", size);
            picCorp.putExtra("outputY", size);
            picCorp.putExtra("return-data", true);
            context.startActivityForResult(picCorp, REQUESTCODE_PICTURE);
        } catch (Exception e) {
            ToastUtils.show(context, "获取相册图片失败");
            e.printStackTrace();
        }

    }

    public void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
  
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_OK) {
//            return;
//        }
//        Bundle mBundle = null;
//        switch (requestCode) {
//            case SelectPicShowHelper.REQUESTCODE_CAMERA:
//                picUtils.getImgRequsetAndCorp(300);
//                break;
//            case SelectPicShowHelper.REQUESTCODE_PICTURE:
//                mBundle = data.getExtras();
//                if (mBundle == null)
//                    break;
//                initBitmap(mBundle);
//                break;
//            case SelectPicShowHelper.PHOTO_REQUEST_GALLERY:
//                if (data != null)
//                    picUtils.startPhotoZoom(data.getData(), 300);
//                break;
//            case SelectPicShowHelper.PHOTO_REQUEST_CUT:
//                mBundle = data.getExtras();
//                if (mBundle == null)
//                    break;
//                initBitmap(mBundle);
//                break;
//
//        }
//    }
    

}

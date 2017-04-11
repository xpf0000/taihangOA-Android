package x.taihangOA.com.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import x.taihangOA.com.R;

/**
 * Created by admins on 2015/10/17.
 */
public class GetPhotos {
    private Button Photo,tu,quxiao;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
        public void showDialog( final Activity activity, final File tempFile)
        {

            View view=activity.getLayoutInflater().inflate(R.layout.photo_choose_dialog,null);
            final Dialog dialog=new Dialog(activity,R.style.transparentFrameWindowStyle);
            dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            Window window = dialog.getWindow();
            // 设置显示动画
            window.setWindowAnimations(R.style.main_menu_animstyle);
            WindowManager.LayoutParams wl = window.getAttributes();
            wl.x = 0;
            wl.y =activity.getWindowManager().getDefaultDisplay().getHeight();
            // 以下这两句是为了保证按钮可以水平满屏
            wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            // 设置显示位置
            dialog.onWindowAttributesChanged(wl);
            // 设置点击外围解散
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
            Photo=(Button)view.findViewById(R.id.photo);
            Photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    dialog.dismiss();
                    // 调用系统的拍照功能
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 指定调用相机拍照后照片的储存路径
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                    activity.startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                }
            });
            tu=(Button)view.findViewById(R.id.tu);
            tu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    activity.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                }
            });
            quxiao=(Button)view.findViewById(R.id.quxiao);
            quxiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    public String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
    public void startPhotoZoom(Uri uri, int size,  Activity activity) {
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

        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public Drawable setPicToView(Intent picdata) {
        Bundle bundle = picdata.getExtras();
        Drawable drawable = null;
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            drawable = new BitmapDrawable(photo);
            return drawable;

        }
        return drawable;
    }
  public File  Getalbum(Intent data,Activity activity){
      String[] proj = { MediaStore.Images.Media.DATA };
      Uri originalUri = data.getData();
      Cursor cursor =activity.managedQuery(originalUri, proj, null, null,
              null);
      int column_index = cursor
              .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      cursor.moveToFirst();
      String path=cursor.getString(column_index);
      Bitmap bit = BitmapFactory.decodeFile(path);
      float pc = (float) 100 / (float) bit.getWidth();
      Bitmap bitmap = resize_img(bit, pc);
      String filename = path.substring(path.lastIndexOf("/") + 1);
      File file = saveMyBitmap(filename, bitmap);
      return file;
    }
    public Bitmap resize_img(Bitmap bitmap, float pc) {
        Matrix matrix = new Matrix();
        matrix.postScale(pc, pc); // 长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);

//	    bitmap.recycle();
//	    bitmap = null;
//	    System.gc();

        return resizeBmp;
    }
    @SuppressLint("SdCardPath")
    public File saveMyBitmap(String filename, Bitmap bit) {
        File sd = Environment.getExternalStorageDirectory();
        String path=sd.getPath()+"/citycircle/Cache/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(path + filename);
        try {
            f.createNewFile();
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(f);
            bit.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            f = null;
            e1.printStackTrace();
        }

        return f;
    }

}


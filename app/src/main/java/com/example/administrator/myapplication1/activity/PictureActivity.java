package com.example.administrator.myapplication1.activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.myapplication1.R;
import com.example.administrator.myapplication1.utils.FileUtils;
import com.example.administrator.myapplication1.utils.FinishProjectPopupWindows;
import com.example.administrator.myapplication1.utils.ImageTools;
import com.example.administrator.myapplication1.utils.ImgGridViewAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 仿微信朋友圈发布动态
 * 拍照或图库选择 
 * 压缩图片并保存
 * @author 张明杨
 *
 */
public class PictureActivity extends BasicActivity implements OnItemClickListener {
	private FinishProjectPopupWindows mFinishProjectPopupWindow;
	// 图片 九宫格
	private GridView gv;
	// 图片 九宫格适配器
	private ImgGridViewAdapter gvAdapter;

	// 用于保存图片资源文件
	private List<Bitmap> lists = new ArrayList<Bitmap>();
	// 用于保存图片路径
	private List<String> list_path = new ArrayList<String>();

	// 拍照
	public static final int IMAGE_CAPTURE = 1;
	// 从相册选择
	public static final int IMAGE_SELECT = 2;
	// 照片缩小比例
	private static final int SCALE = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture);
		init();
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		gv = (GridView) findViewById(R.id.noScrollgridview);
		gvAdapter = new ImgGridViewAdapter(this, lists);
		gv.setOnItemClickListener(this);
		gv.setAdapter(gvAdapter);
		gvAdapter.setList(lists);
	}
	
	@Override
	protected void onDestroy() {
		//删除文件夹及文件
		FileUtils.deleteDir();
		super.onDestroy();
	}

	/**
	 * 拍照
	 * 
	 * @param path
	 *            照片存放的路径
	 */
	public void captureImage(String path) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
		Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, IMAGE_CAPTURE);
	}

	/**
	 * 从图库中选取图片
	 */
	public void selectImage() {
		 Intent intent = new Intent();
		 intent.setType("image/*");
		 intent.setAction(Intent.ACTION_PICK);
		 startActivityForResult(intent, IMAGE_SELECT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && resultCode != RESULT_CANCELED) {
			String fileName;
			switch (requestCode) {
			case IMAGE_CAPTURE:// 拍照返回
				// 将保存在本地的图片取出并缩小后显示在界面上
				Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/image.jpg");
				Bitmap newBitmap = ImageTools.zoomBitmap(bitmap,bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
				// 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
				bitmap.recycle();
				// 生成一个图片文件名
				fileName = String.valueOf(System.currentTimeMillis());
				// 将处理过的图片添加到缩略图列表并保存到本地
				ImageTools.savePhotoToSDCard(newBitmap, FileUtils.SDPATH,fileName);
				lists.add(newBitmap);
				list_path.add(fileName+".jpg");
				
				for (int i = 0; i < list_path.size(); i++) {
					logI("第"+i+"张照片的地址："+list_path.get(i));
				}
				
				// 更新GrideView
				gvAdapter.setList(lists);
				break;
			case IMAGE_SELECT:// 选择照片返回
				ContentResolver resolver = getContentResolver();
				// 照片的原始资源地址
				Uri originalUri = data.getData();
				try {
					// 使用ContentProvider通过URI获取原始图片
					Bitmap photo = MediaStore.Images.Media.getBitmap(resolver,originalUri);
					if (photo != null) {
						// 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
						Bitmap smallBitmap = ImageTools.zoomBitmap(photo,photo.getWidth() / SCALE, photo.getHeight()/ SCALE);
						// 释放原始图片占用的内存，防止out of memory异常发生
						photo.recycle();
						// 生成一个图片文件名
						fileName = String.valueOf(System.currentTimeMillis());
						// 将处理过的图片添加到缩略图列表并保存到本地
						ImageTools.savePhotoToSDCard(smallBitmap, FileUtils.SDPATH,fileName);
						lists.add(smallBitmap);
						list_path.add(fileName+".jpg");
						
						for (int i = 0; i < list_path.size(); i++) {
							logI("第"+i+"照片的地址："+list_path.get(i));
						}
						
						// 更新GrideView
						gvAdapter.setList(lists);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, final int position,
                            long id) {
		Toast.makeText(getApplicationContext(), "" + position + getDataSize(), Toast.LENGTH_SHORT).show();

		if (position == getDataSize()) {// 点击“+”号位置添加图片
			//showAlertDialog(false, "提示", new String[] { "拍照", "从图库选择", "取消" },
//					new DialogInterface.OnClickListener() {
//
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							switch (which + 1) {
//							case 1:// 拍照
//								captureImage(FileUtils.SDPATH);
//								dialog.dismiss();
//								break;
//							case 2:// 从图库选择
//								selectImage();
//								dialog.dismiss();
//								break;
//							case 3:// 取消
//								dialog.dismiss();
//								break;
//
//							default:
//								break;
//							}
//						}
//					});
			// 显示PopupWindow
			mFinishProjectPopupWindow = new FinishProjectPopupWindows(PictureActivity.this, itemsOnClick);
			mFinishProjectPopupWindow.showAtLocation(PictureActivity.this.findViewById(R.id.noScrollgridview),
					Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
		} else {// 点击图片删除
			showAlertDialog("提示", "是否删除此图片？", "确定", "取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					lists.remove(position);
					FileUtils.delFile(list_path.get(position));
					list_path.remove(position);
					gvAdapter.setList(lists);
					dialog.dismiss();
				}
			}, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		}
	}

	private int getDataSize() {
		return lists == null ? 0 : lists.size();
	}


	private View.OnClickListener itemsOnClick = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			mFinishProjectPopupWindow.dismiss();
			switch(v.getId()){
				case R.id.popupwindow_Button_saveProject:
					captureImage(FileUtils.SDPATH);
					mFinishProjectPopupWindow.dismiss();
					break;
				case R.id.popupwindow_Button_abandonProject:

					selectImage();
					mFinishProjectPopupWindow.dismiss();
					break;

				case R.id.popupwindow_cancelButton:
					mFinishProjectPopupWindow.dismiss();

					break;
			}

		}

	};
}

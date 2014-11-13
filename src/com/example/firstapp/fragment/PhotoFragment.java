package com.example.firstapp.fragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.firstapp.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PhotoFragment extends Fragment {
	
	private static final int REQUEST_CODE_TAKE_PHOTO = 0;
	private static final int REQUEST_CODE_GALLERY = 1;
	private ImageView imageview1;
	private LinearLayout linearLayout1;
	private Uri extraOutput;

	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_photo, container, false);
		
		imageview1 = (ImageView) rootView.findViewById(R.id.imageView1);
		linearLayout1 = (LinearLayout) rootView.findViewById(R.id.linearLayout1);
		
		loadPhotoFromParse();
		
		
		return rootView;
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getActivity().getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_take_photo) {

			extraOutput = getUri();
			Intent intent = new Intent();
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

			intent.putExtra(MediaStore.EXTRA_OUTPUT, extraOutput);
			startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
			return true;
		} else if (id == R.id.action_gallery) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(intent, REQUEST_CODE_GALLERY);

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if ((requestCode == REQUEST_CODE_TAKE_PHOTO)) {
			// Bitmap bitmap = data.getParcelableExtra("data");
			// imageview1.setImageBitmap(bitmap);

			imageview1.setImageURI(extraOutput);
			saveToParse(extraOutput);
		} else if ((requestCode == REQUEST_CODE_GALLERY)) {
			Uri selectImageUri = data.getData();
			imageview1.setImageURI(selectImageUri);
			saveToParse(selectImageUri);
		}
	}

	private Uri getUri() {
		File file = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
		if (file.exists() == false) {
			file.mkdirs();
		}
		File file2 = new File(file, "image.png");
		return Uri.fromFile(file2);
	}

	private byte[] uriToBytes(Uri uri) {
		try {
			InputStream is = getActivity().getContentResolver().openInputStream(uri);
			ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				byteBuffer.write(buffer, 0, len);
			}
			return byteBuffer.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void saveToParse(Uri uri) {
		byte[] bytes = uriToBytes(uri);
		ParseObject object = new ParseObject("Photo");
		final ParseFile file = new ParseFile("photo.png", bytes);

		object.put("file", file);
		object.saveInBackground(new SaveCallback() {

			@Override
			public void done(ParseException e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void loadPhotoFromParse() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Photo");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				for (ParseObject object : objects) {
					ParseFile file = object.getParseFile("file");
					Log.d("debug", file.getName());

				}
			}
		});
	}
}

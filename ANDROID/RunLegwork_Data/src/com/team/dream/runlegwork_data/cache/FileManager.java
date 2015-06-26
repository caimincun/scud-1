package com.team.dream.runlegwork_data.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

@SuppressLint("NewApi")
public class FileManager {

	private FileManager() {
	}

	private static final class LazyHoler {
		private static final FileManager INSTANCE = new FileManager();
	}

	public static FileManager getFileManager() {
		return LazyHoler.INSTANCE;
	}

	public void writeToFile(File file, String fileConent) {
		if (file.exists())
			return;
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(fileConent);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	public String readFileContent(File file) {
		StringBuffer sb = new StringBuffer();
		if (file.exists()) {
			String stringLine;
			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileReader);
				while ((stringLine = reader.readLine()) != null) {
					sb.append(stringLine + "\n");
				}
				reader.close();
				fileReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public void clearDiretory(File diretory) {
		if (!diretory.exists())
			return;
		if (diretory.isDirectory()) {
			deleteFileDirectory(diretory);
		} else {
			diretory.delete();
		}
	}

	public void writeToPreferences(Context context, String preferenceName, String key, Long value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putLong(preferenceName, value);
		editor.apply();
	}
	public long getFromPreferences(Context context, String preferenceFileName, String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
		return sharedPreferences.getLong(key, 0);
	}

	private void deleteFileDirectory(File file) {
		File[] filesList = file.listFiles();
		for (int i = 0; i < filesList.length; i++) {
			filesList[i].delete();
		}
		if (filesList.length == 0) {
			file.delete();
		}
	}



}

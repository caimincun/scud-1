package com.team.dream.runlegwork.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

/*
 * 数据流处理类
 * @yuejunyan
 */
public final class StreamUtil {

	private static final int	BUFFER_SIZE	= 2048;

	/**
	 * 获取输入流数据
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public final static byte[] convertToBytes(InputStream in) throws IOException {
		ByteArrayOutputStream reqOut = new ByteArrayOutputStream();
		byte[] req = null;
		try {
			if (!MDMUtils.isSDCardEnable()) {
				return null;
			}
			
			byte[] buffer = new byte[BUFFER_SIZE];
			int count = 0;
			while ((count = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
				reqOut.write(buffer, 0, count);
			}
			req = reqOut.toByteArray();
		} catch (Exception e) {
			throw new IOException();
		} finally {
			reqOut.close();
			in.close();
		}
		return req;
	}

	/**
	 * 获取输入流数据
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public final static byte[] convertToBytes(File file) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		byte[] req = convertToBytes(in);
		return req;
	}

	/**
	 * 获取输入流数据
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public final static byte[] convertToBytes(String fileName) throws IOException {
		File file = new File(fileName);
		byte[] req = convertToBytes(file);
		return req;
	}
	
	public final static boolean saveToFile(String filePath, byte[] data) {
		BufferedOutputStream fos = null;
		try {
			if (!MDMUtils.isSDCardEnable()) {
				return false;
			}
			if(filePath.lastIndexOf("/") > 0) {
				File folder = new File(filePath.substring(0, filePath.lastIndexOf("/")));
				if(!folder.exists()) {
					folder.mkdirs();
				}
			}
			
			File file = new File(filePath);
			fos = new BufferedOutputStream(new FileOutputStream(file));
			fos.write(data);
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	public final static boolean saveBitmap(String filePath, Bitmap bm, int quality) {
		return saveBitmap(filePath, bm, Bitmap.CompressFormat.JPEG, quality);
	}
	
	public final static boolean saveBitmap(String filePath, Bitmap bm, CompressFormat format, int quality) {
		try {
			if (!MDMUtils.isSDCardEnable()) {
				return false;
			}		
			
			if(filePath.lastIndexOf("/") > 0) {
				File folder = new File(filePath.substring(0, filePath.lastIndexOf("/")));
				if(!folder.exists()) {
					folder.mkdirs();
				}
			}
			
			BufferedOutputStream  out = new BufferedOutputStream(new FileOutputStream(filePath));
			if(bm.compress(format, quality, out)){
				out.flush();
				out.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public final static boolean removeFile(String filePath) {
		try {
			if (!MDMUtils.isSDCardEnable()) {
				return false;
			}		
			
			File mFile = new File(filePath);
			if(mFile.exists() && mFile.isFile()) {
				mFile.delete();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private static class CompratorByLastModified implements Comparator<File> {
		public int compare(File f1, File f2) {
			long diff = f1.lastModified() - f2.lastModified();
			if (diff > 0)
				return 1;
			else if (diff == 0)
				return 0;
			else
				return -1;
		};

		public boolean equals(Object obj) {
			return true;
		};

	}
	
	/**
	 * 删除文件夹下的文件，最多保留maxNum
	 * @param filePath
	 * @param maxNum
	 * @return
	 */
	public final static boolean clearFilesByNum(String filePath, int maxNum) {
		try {
			if (!MDMUtils.isSDCardEnable()) {
				return false;
			}		
			
			File mFile = new File(filePath);
			if(mFile.exists() && mFile.isDirectory()) {
				File[] files = mFile.listFiles();
				if (files.length > maxNum) {
					//升序
					Arrays.sort(files, new CompratorByLastModified());
					if(maxNum < 0) {
						maxNum = 0;
					}
					//删除老文件
					for (int i = 0, len = files.length - maxNum; i < len; i++) {						
						files[i].delete();
					}
					return true;
				}
			}
			
		} catch (Exception e) {
			
		}
		return false;
	}
	
	/**
	 * 删除文件夹
	 * @param filePath
	 * @return
	 */
	public final static boolean clearFiles(String filePath) {
		return clearFilesByNum(filePath, 0);
	}
	
	/**
	 * 删除文件夹，保留最近time创建的文件
	 * @param filePath
	 * @param time
	 * @return
	 */
	public final static boolean clearFilesByTime(String filePath, long time) {
		try {
			if (!MDMUtils.isSDCardEnable()) {
				return false;
			}		
			
			File mFile = new File(filePath);
			if(mFile.exists() && mFile.isDirectory()) {
				File[] files = mFile.listFiles();
				
				long current = System.currentTimeMillis();
				for (int i = 0, len = files.length; i < len; i++) {		
					long last = files[i].lastModified();
					if (current - last > time) {
					files[i].delete();
					}
				}
				return true;
			}
			
		} catch (Exception e) {
			
		}
		return false;
	}
	
	
	public final static boolean copyFile(String fromFile, String toFile) {
		try {
			InputStream fosfrom = new FileInputStream(fromFile);
			OutputStream fosto = new FileOutputStream(toFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			fosfrom.close();
			fosto.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public final static boolean moveFile(String fromFile, String toFile) {
		try {
			File mFrom = new File(fromFile);			
			return mFrom.renameTo(new File(toFile));
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isSameKey(byte[] key, byte[] buffer) {
        int n = key.length;
        if (buffer.length < n) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            if (key[i] != buffer[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static byte[] copyOfRange(byte[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        byte[] copy = new byte[newLength];
        System.arraycopy(original, from, copy, 0,Math.min(original.length - from, newLength));
        return copy;
    } 
}

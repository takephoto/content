/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 

package com.yang.test;

import java.util.regex.Pattern;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.RingtonePreference;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;



*//**
 * The RingtonePreference does not have a way to get/set the current ringtone so
 * we override onSaveRingtone and onRestoreRingtone to get the same behavior.
 *//*
public class AlarmPreference extends RingtonePreference {
	private Uri mAlert;

	private Context mCon;
	public static UpteMOdes mUpteMOdes;

	public static void getInstent(UpteMOdes mUpteMOde) {
		mUpteMOdes = mUpteMOde;
	}

	public AlarmPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		mCon = context;
	}

	@Override
	protected void onSaveRingtone(Uri ringtoneUri) {
		Log.i("lwwonSaveRingtone111", "--- " + ringtoneUri);
		setAlert(ringtoneUri);
	}

	@Override
	protected Uri onRestoreRingtone() {
		if (RingtoneManager.isDefault(mAlert)) {
			Log.i("lwwonSaveRingtone2222", "--- " + mAlert);
			return RingtoneManager.getActualDefaultRingtoneUri(getContext(),
					RingtoneManager.TYPE_ALARM);
		}
		return mAlert;
	}

	public void setAlert(Uri alert) {
		try {
			setTitle("");
			mAlert = alert;
			if (alert != null) {
				final Ringtone r = RingtoneManager.getRingtone(getContext(),
						alert);
				if (r != null) {
					String strs = mCon.getString(R.string.alarm_clocks);
					String text = r.getTitle(getContext());
					if (isNumeric(text.trim())) {
						int index = alert.toString().lastIndexOf("/");
						String newText = alert.toString().substring(index + 1, alert
								.toString().length());
						if (newText.trim().equals(text.trim())) {
							text = mCon.getString(R.string.alarms_moren);
						}
					}
					if (text.contains("默认") ||text.contains("default") )
						text = mCon.getString(R.string.alarms_moren);
					// text=text.replace(" ","");
					if (!checkNameChese(text)) {
						if (text.length() > 10)
							text = text.substring(0, 10) + "...";
					} else {
						if (text.length() > 8)
							text = text.substring(0, 8) + "...";
					}
					// if (text != null && text.contains("Beep")
					// && text.contains("Alarm"))
					// text = text.substring(0, text.length() - 6);
					String sss = "<big><font color='black'>" + strs + "\t\t\t"
							+ text + "</font></big>";
					Spanned s = Html.fromHtml(sss.trim());
					setSummary(s);
				}
			} else {
				String strs = mCon.getString(R.string.alarm_clocks);
				String strs2 = mCon.getString(R.string.silent_alarm_summary);
				String sss = "<big><font color='black'>" + strs + "\t\t\t"
						+ strs2 + "</font></big>";
				Spanned s = Html.fromHtml(sss.trim());
				setSummary(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Uri getAlert() {
		return mAlert;
	}

	*//**
	 * 检测String是否全是中文
	 * 
	 * @param name
	 * @return
	 *//*
	public boolean checkNameChese(String name) {
		boolean res = true;
		char[] cTemp = name.toCharArray();
		for (int i = 0; i < name.length(); i++) {
			if (!isChinese(cTemp[i])) {
				res = false;
				break;
			}
		}
		return res;
	}

	*//**
	 * 判定输入汉字
	 * 
	 * @param c
	 * @return
	 *//*
	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	// 判断数字
	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}

*/
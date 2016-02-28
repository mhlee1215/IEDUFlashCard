package com.flashcard.iedu.flashcard.samples.setting;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.flashcard.iedu.flashcard.R;

public class AppPreferences extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}

}
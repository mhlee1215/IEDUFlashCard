package com.flashcard.iedu.flashcard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flashcard.iedu.flashcard.R;

import edu.iedu.flashcard.dao.domain.Word;

public class CardFragment extends Fragment {

	public static final String _OF_ = " of ";
	public static final String BACK = " Back";
	public static final String FRONT = " Front";

	public final static String CARD_QUESTION = "q";
	public final static String CARD_ANSWER = "a";
	public final static String MAX_KEY = "m";
	public final static String CARD_POSITION_KEY = "p";
	
	private boolean mWordToggle = false;
	private StringBuilder mCounterStringBuilder;
	private TextView mTextViewQuestion;
	private TextView mTextViewAnswer;
	private TextView mCounterTextView;
	private EditText mEditTextWord;
	private LinearLayout mLinearLayoutEditButtons;
	private ImageButton mImageButtonSave;
	private ImageButton mImageButtonCancel;
	private int mCardPosition;
	private String mCardQuestion;
	private String mCardAnswer;
	private View mCardView;
	private int mFontSize;

	private Word card;

	public static CardFragment newInstance(int wordIndex, int totalWords, int fontSize, Word card) {

		CardFragment pageFragment = new CardFragment();
		pageFragment.setFontSize(fontSize);
		Bundle bundle = new Bundle();

		bundle.putString(CARD_QUESTION, "questions!");
		bundle.putString(CARD_ANSWER, "answer!");
		bundle.putInt(CARD_POSITION_KEY, wordIndex);
		bundle.putInt(MAX_KEY, totalWords);
		pageFragment.setArguments(bundle);

		pageFragment.setCard(card);

		return pageFragment;
	}


	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mCardView = inflater.inflate(R.layout.card_fragment, container, false);

		mCardQuestion = card.getName();//getArguments().getString(CARD_QUESTION);
		mCardAnswer = card.getMeaning();//getArguments().getString(CARD_ANSWER);
		mCardPosition = card.getPosition();//getArguments().getInt(CARD_POSITION_KEY);

		mTextViewQuestion = (TextView)mCardView.findViewById(R.id.textViewQuestion);
		mTextViewQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSize);

		if(null != mCardQuestion) {

			mTextViewQuestion.setText(mCardQuestion);
		}
		else {

			mTextViewQuestion.setText("??");
		}

		mTextViewAnswer = (TextView)mCardView.findViewById(R.id.textViewAnswer);
		mTextViewAnswer.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSize);

		if(null != mCardAnswer) {

			mTextViewAnswer.setText(mCardAnswer);
		}
		else {

			mTextViewAnswer.setText("!!");
		}

		mEditTextWord = (EditText)mCardView.findViewById(R.id.editTextWord);
		mEditTextWord.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSize);

		mLinearLayoutEditButtons = (LinearLayout)mCardView.findViewById(R.id.linearLayoutEditButtons);

		mImageButtonSave = (ImageButton)mCardView.findViewById(R.id.imageButtonSave);
		mImageButtonSave.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Toast.makeText(getActivity().getApplicationContext(), "save clicked", Toast.LENGTH_SHORT).show();

			}
		});

		mImageButtonCancel = (ImageButton)mCardView.findViewById(R.id.imageButtonCancel);
		mImageButtonCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Toast.makeText(getActivity().getApplicationContext(), "cancel clicked", Toast.LENGTH_SHORT).show();
			}
		});

		// Set the bottom word counter
		mCounterTextView = (TextView) mCardView.findViewById(R.id.textViewWordNumber);
		mCounterStringBuilder = new StringBuilder();
		mCounterStringBuilder.append(mCardPosition + 1);
		mCounterStringBuilder.append(_OF_);
		mCounterStringBuilder.append(getArguments().getInt(MAX_KEY));
		mCounterTextView.setText(mCounterStringBuilder.toString());
		mCounterTextView.append(FRONT);

		mCardView.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				turnPage(mCardView);
			}
		});

		return mCardView;
	}

	public void setFontSize(int size) {

		mFontSize = size;

		if(null != mTextViewQuestion && null != mTextViewAnswer) {

			mTextViewQuestion.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSize);
			mTextViewAnswer.setTextSize(TypedValue.COMPLEX_UNIT_SP, mFontSize);
		}
	}

	public void onEdit() {

		mTextViewQuestion.setVisibility(View.INVISIBLE);
		mTextViewAnswer.setVisibility(View.INVISIBLE);

		if(mWordToggle) {

			mEditTextWord.setText(mTextViewAnswer.getText());
		}
		else {

			mEditTextWord.setText(mTextViewQuestion.getText());
		}

		mEditTextWord.setVisibility(View.VISIBLE);
		mEditTextWord.setSelection(mEditTextWord.getText().length());
		mLinearLayoutEditButtons.setVisibility(View.VISIBLE);
	}

	private void turnPage(final View view) {

		/*
		 * If in edit mode, we don't allow the user to switch between the front and back page.
		 */
		if(mEditTextWord.isShown()) {

			return;
		}

		//R.animator.flip1
		final Animation flip1 = AnimationUtils.loadAnimation(view.getContext(), R.anim.flip1);
		final Animation flip2 = AnimationUtils.loadAnimation(view.getContext(), R.anim.flip2);

		flip1.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) { /* Nothing to do here */ }

			public void onAnimationRepeat(Animation animation) { /* Nothing to do here */ }

			public void onAnimationEnd(Animation animation) {

				mWordToggle ^= true;

				if (mWordToggle) {

					mTextViewQuestion.setVisibility(View.INVISIBLE);
					mTextViewAnswer.setVisibility(View.VISIBLE);
					mCounterTextView.setText(mCounterStringBuilder.toString());
					mCounterTextView.append(BACK);
				} else {

					mTextViewQuestion.setVisibility(View.VISIBLE);
					mTextViewAnswer.setVisibility(View.INVISIBLE);
					mCounterTextView.setText(mCounterStringBuilder.toString());
					mCounterTextView.append(FRONT);
				}

				view.startAnimation(flip2);
			}
		});

		view.startAnimation(flip1);
	}

	public void setCard(Word card){
		this.card = card;
	}
}

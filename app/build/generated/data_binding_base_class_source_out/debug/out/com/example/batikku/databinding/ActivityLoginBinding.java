// Generated by view binder compiler. Do not edit!
package com.example.batikku.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.batikku.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView Signuptv;

  @NonNull
  public final TextInputEditText emailEditText;

  @NonNull
  public final TextInputLayout emailEditTextLayout;

  @NonNull
  public final Guideline guidelineHorizontal;

  @NonNull
  public final Guideline guidelineHorizontal2;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final TextInputEditText passwordEditText;

  @NonNull
  public final TextInputLayout passwordEditTextLayout;

  @NonNull
  public final Button signin;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull TextView Signuptv,
      @NonNull TextInputEditText emailEditText, @NonNull TextInputLayout emailEditTextLayout,
      @NonNull Guideline guidelineHorizontal, @NonNull Guideline guidelineHorizontal2,
      @NonNull ImageView imageView, @NonNull TextInputEditText passwordEditText,
      @NonNull TextInputLayout passwordEditTextLayout, @NonNull Button signin) {
    this.rootView = rootView;
    this.Signuptv = Signuptv;
    this.emailEditText = emailEditText;
    this.emailEditTextLayout = emailEditTextLayout;
    this.guidelineHorizontal = guidelineHorizontal;
    this.guidelineHorizontal2 = guidelineHorizontal2;
    this.imageView = imageView;
    this.passwordEditText = passwordEditText;
    this.passwordEditTextLayout = passwordEditTextLayout;
    this.signin = signin;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Signuptv;
      TextView Signuptv = ViewBindings.findChildViewById(rootView, id);
      if (Signuptv == null) {
        break missingId;
      }

      id = R.id.emailEditText;
      TextInputEditText emailEditText = ViewBindings.findChildViewById(rootView, id);
      if (emailEditText == null) {
        break missingId;
      }

      id = R.id.emailEditTextLayout;
      TextInputLayout emailEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (emailEditTextLayout == null) {
        break missingId;
      }

      id = R.id.guidelineHorizontal;
      Guideline guidelineHorizontal = ViewBindings.findChildViewById(rootView, id);
      if (guidelineHorizontal == null) {
        break missingId;
      }

      id = R.id.guidelineHorizontal2;
      Guideline guidelineHorizontal2 = ViewBindings.findChildViewById(rootView, id);
      if (guidelineHorizontal2 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.passwordEditText;
      TextInputEditText passwordEditText = ViewBindings.findChildViewById(rootView, id);
      if (passwordEditText == null) {
        break missingId;
      }

      id = R.id.passwordEditTextLayout;
      TextInputLayout passwordEditTextLayout = ViewBindings.findChildViewById(rootView, id);
      if (passwordEditTextLayout == null) {
        break missingId;
      }

      id = R.id.signin;
      Button signin = ViewBindings.findChildViewById(rootView, id);
      if (signin == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, Signuptv, emailEditText,
          emailEditTextLayout, guidelineHorizontal, guidelineHorizontal2, imageView,
          passwordEditText, passwordEditTextLayout, signin);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

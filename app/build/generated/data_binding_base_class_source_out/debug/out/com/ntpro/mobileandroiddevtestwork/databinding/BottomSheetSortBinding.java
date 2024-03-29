// Generated by view binder compiler. Do not edit!
package com.ntpro.mobileandroiddevtestwork.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ntpro.mobileandroiddevtestwork.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class BottomSheetSortBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView rowDateOfChange;

  @NonNull
  public final TextView rowName;

  @NonNull
  public final TextView rowParty;

  @NonNull
  public final TextView rowPrice;

  @NonNull
  public final TextView rowVolume;

  private BottomSheetSortBinding(@NonNull LinearLayout rootView, @NonNull TextView rowDateOfChange,
      @NonNull TextView rowName, @NonNull TextView rowParty, @NonNull TextView rowPrice,
      @NonNull TextView rowVolume) {
    this.rootView = rootView;
    this.rowDateOfChange = rowDateOfChange;
    this.rowName = rowName;
    this.rowParty = rowParty;
    this.rowPrice = rowPrice;
    this.rowVolume = rowVolume;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static BottomSheetSortBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static BottomSheetSortBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.bottom_sheet_sort, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static BottomSheetSortBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.row_date_of_change;
      TextView rowDateOfChange = ViewBindings.findChildViewById(rootView, id);
      if (rowDateOfChange == null) {
        break missingId;
      }

      id = R.id.row_name;
      TextView rowName = ViewBindings.findChildViewById(rootView, id);
      if (rowName == null) {
        break missingId;
      }

      id = R.id.row_party;
      TextView rowParty = ViewBindings.findChildViewById(rootView, id);
      if (rowParty == null) {
        break missingId;
      }

      id = R.id.row_price;
      TextView rowPrice = ViewBindings.findChildViewById(rootView, id);
      if (rowPrice == null) {
        break missingId;
      }

      id = R.id.row_volume;
      TextView rowVolume = ViewBindings.findChildViewById(rootView, id);
      if (rowVolume == null) {
        break missingId;
      }

      return new BottomSheetSortBinding((LinearLayout) rootView, rowDateOfChange, rowName, rowParty,
          rowPrice, rowVolume);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

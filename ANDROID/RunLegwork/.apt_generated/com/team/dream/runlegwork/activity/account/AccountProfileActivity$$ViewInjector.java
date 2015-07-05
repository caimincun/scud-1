// Generated code from Butter Knife. Do not modify!
package com.team.dream.runlegwork.activity.account;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class AccountProfileActivity$$ViewInjector<T extends com.team.dream.runlegwork.activity.account.AccountProfileActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099689, "field 'miLabel' and method 'modifyAddress'");
    target.miLabel = finder.castView(view, 2131099689, "field 'miLabel'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.modifyAddress();
        }
      });
    view = finder.findRequiredView(source, 2131099683, "field 'miname' and method 'modifyName'");
    target.miname = finder.castView(view, 2131099683, "field 'miname'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.modifyName();
        }
      });
    view = finder.findRequiredView(source, 2131099691, "field 'btnsave' and method 'save'");
    target.btnsave = finder.castView(view, 2131099691, "field 'btnsave'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.save();
        }
      });
    view = finder.findRequiredView(source, 2131099686, "field 'miIdcard' and method 'modifyIdcard'");
    target.miIdcard = finder.castView(view, 2131099686, "field 'miIdcard'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.modifyIdcard();
        }
      });
    view = finder.findRequiredView(source, 2131099687, "field 'misex' and method 'choiceSex'");
    target.misex = finder.castView(view, 2131099687, "field 'misex'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.choiceSex();
        }
      });
    view = finder.findRequiredView(source, 2131099685, "field 'mtb'");
    target.mtb = finder.castView(view, 2131099685, "field 'mtb'");
    view = finder.findRequiredView(source, 2131099690, "field 'misigner' and method 'modifySigner'");
    target.misigner = finder.castView(view, 2131099690, "field 'misigner'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.modifySigner();
        }
      });
    view = finder.findRequiredView(source, 2131099688, "field 'miemail' and method 'modifyEmail'");
    target.miemail = finder.castView(view, 2131099688, "field 'miemail'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.modifyEmail();
        }
      });
  }

  @Override public void reset(T target) {
    target.miLabel = null;
    target.miname = null;
    target.btnsave = null;
    target.miIdcard = null;
    target.misex = null;
    target.mtb = null;
    target.misigner = null;
    target.miemail = null;
  }
}

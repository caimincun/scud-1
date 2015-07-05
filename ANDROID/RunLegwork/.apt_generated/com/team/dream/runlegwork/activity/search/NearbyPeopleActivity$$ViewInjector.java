// Generated code from Butter Knife. Do not modify!
package com.team.dream.runlegwork.activity.search;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class NearbyPeopleActivity$$ViewInjector<T extends com.team.dream.runlegwork.activity.search.NearbyPeopleActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099696, "field 'mtb'");
    target.mtb = finder.castView(view, 2131099696, "field 'mtb'");
    view = finder.findRequiredView(source, 2131099697, "field 'plListv'");
    target.plListv = finder.castView(view, 2131099697, "field 'plListv'");
  }

  @Override public void reset(T target) {
    target.mtb = null;
    target.plListv = null;
  }
}

package com.github.flickr.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.flickr.BaseAppComponent;
import com.github.flickr.R;
import com.github.flickr.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment implements HomeFragmentContract.View {

    @NonNull Injector INJECTOR = new Injector();

    @BindView(R.id.photos)
    RecyclerView photos;

    @Inject
    HomeFragmentContract.Presenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        INJECTOR.createHomeComponent(getBaseComponent(), this)
                .inject(this);

        presenter.init();

        return view;

    }

    private static class Injector {
        private HomeFragmentComponent createHomeComponent(
                @NonNull BaseAppComponent baseComponent,
                @NonNull HomeFragmentContract.View view) {
            return DaggerHomeFragmentComponent
                    .builder()
                    .baseAppComponent(baseComponent)
                    .homeFragmentModule(new HomeFragmentModule(view))
                    .build();
        }
    }
}

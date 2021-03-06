package com.github.flickr.dependency.scheduler;

import android.support.annotation.NonNull;

public interface Scheduler {

    @NonNull rx.Scheduler background();

    @NonNull rx.Scheduler main();

    @NonNull rx.Scheduler immediate();
}
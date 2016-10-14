package home.skwmium.skilltest.model;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public interface SchedulerProvider {
    <T> Observable.Transformer<T, T> applySchedulers();

    SchedulerProvider DEFAULT = new SchedulerProvider() {
        @Override
        public <T> Observable.Transformer<T, T> applySchedulers() {
            return observable -> observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    SchedulerProvider THREAD_POOL_EXECUTOR = new SchedulerProvider() {
        private final ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(5);

        @Override
        public <T> Observable.Transformer<T, T> applySchedulers() {
            return observable -> observable.subscribeOn(Schedulers.from(threadPoolExecutor))
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };
}

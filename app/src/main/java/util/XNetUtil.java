package util;

import android.widget.Toast;
import x.taihangOA.com.MyAppService.LocationApplication;
import x.taihangOA.com.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by X on 2016/10/1.
 */

public class XNetUtil {

    static public boolean debug = false;

    public interface OnHttpResult<T>
    {
        void onError(Throwable e);
        void onSuccess(T t);
    }

    final static public <T> void APPPrintln(T t)
    {
        if(debug)
        {
            System.out.println(t);
        }

    }

    public static <T> void Handle(Observable<HttpResult<T>> obj,Subscriber<T> res) {

        obj
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFunc<T>())
                .subscribe(res);

    }

    public static <T> void HandleReturnAll(Observable<HttpResult<T>> obj,final OnHttpResult<HttpResult<T>> res) {
        obj
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<T>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        if(debug)
                        {
                            e.printStackTrace();
                            Toast.makeText(LocationApplication.context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            XActivityindicator.showToast(LocationApplication.context.getResources().getString(R.string.intent_error));
                        }
                        res.onError(e);

                    }

                    @Override
                    public void onNext(HttpResult<T> tHttpResult) {
                        res.onSuccess(tHttpResult);
                    }
                });

    }


    public static <T> void Handle(Observable<HttpResult<T>> obj,final OnHttpResult<T> res) {
        obj
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFunc<T>())
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(debug)
                        {
                            e.printStackTrace();
                            Toast.makeText(LocationApplication.context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            XActivityindicator.showToast(LocationApplication.context.getResources().getString(R.string.intent_error));
                        }
                        res.onError(e);

                    }

                    @Override
                    public void onNext(T t) {
                        res.onSuccess(t);
                    }
                });

    }


    public static <T> void Handle(Observable<HttpResult<T>> obj,String success,String fail,final OnHttpResult<Boolean> res) {

        obj
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFuncBool<T>(success,fail))
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        XActivityindicator.hide();
                        if(debug)
                        {
                            e.printStackTrace();
                            Toast.makeText(LocationApplication.context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            XActivityindicator.showToast(LocationApplication.context.getResources().getString(R.string.intent_error));
                        }
                        res.onError(e);

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        res.onSuccess(aBoolean);
                    }
                });

    }


    public static <T> void HandleBool(Observable<HttpResult<T>> obj,String success,String fail,final OnHttpResult<Result<T>> res) {

        obj
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFuncBoolAndResult<T>(success,fail))
                .subscribe(new Subscriber<Result<T>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        XActivityindicator.hide();
                        if(debug)
                        {
                            e.printStackTrace();
                            Toast.makeText(LocationApplication.context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            XActivityindicator.showToast(LocationApplication.context.getResources().getString(R.string.intent_error));
                        }
                        res.onError(e);
                    }

                    @Override
                    public void onNext(Result<T> r) {
                        res.onSuccess(r);
                    }
                });

    }



    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private static class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getRet() != 200) {

                XActivityindicator.create(LocationApplication.context).showErrorWithStatus("数据加载失败!");
            }
            else
            {
                if(httpResult.getData().getCode() != 0)
                {
                    String msg = httpResult.getData().getMsg();
                    msg = msg.length() == 0 ? "数据加载失败!" : msg;
                    XActivityindicator.create(LocationApplication.context).showErrorWithStatus(msg);
                }
            }

            return httpResult.getData().getInfo();
        }
    }

    private static class HttpResultFuncBool<T> implements Func1<HttpResult<T>, Boolean> {

        private String success = "";
        private String fail = "";

        public HttpResultFuncBool(String s,String f) {

            this.success = s;
            this.fail = f;
        }

        @Override
        public Boolean call(HttpResult<T> httpResult) {

            XActivityindicator.hide();

            if (httpResult.getRet() != 200 && fail != null) {

                XActivityindicator.create(LocationApplication.context).showErrorWithStatus(fail);

                return false;
            }
            else
            {
                if(httpResult.getData().getCode() != 0 && fail != null)
                {
                    String msg = httpResult.getData().getMsg();
                    msg = msg.length() == 0 ? fail : msg;
                    XActivityindicator.create(LocationApplication.context).showErrorWithStatus(msg);
                    return false;
                }
            }

            if(success != null)
            {
                XActivityindicator.create(LocationApplication.context).showSuccessWithStatus(success);
            }

            return true;
        }
    }



    private static class HttpResultFuncBoolAndResult<T> implements Func1<HttpResult<T>, Result<T>> {

        private String success = "";
        private String fail = "";

        public HttpResultFuncBoolAndResult(String s,String f) {

            this.success = s;
            this.fail = f;
        }

        @Override
        public Result<T> call(HttpResult<T> httpResult) {

            XActivityindicator.hide();
            Result<T> r = new Result<T>();
            r.setHttpResult(httpResult);

            if (httpResult.getRet() != 200) {

                XActivityindicator.create(LocationApplication.context).showErrorWithStatus(fail);
                r.setSuccess(false);
                return r;
            }
            else
            {
                if(httpResult.getData().getCode() != 0)
                {
                    String msg = httpResult.getData().getMsg();
                    msg = msg.length() == 0 ? fail : msg;
                    XActivityindicator.create(LocationApplication.context).showErrorWithStatus(msg);
                    r.setSuccess(false);
                    return r;
                }
            }

            if(success != null)
            {
                XActivityindicator.create(LocationApplication.context).showSuccessWithStatus(success);
            }
            r.setSuccess(true);
            return r;
        }
    }

    public static class Result<T>
    {
        private boolean success = false;
        private HttpResult<T> httpResult;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public HttpResult<T> getHttpResult() {
            return httpResult;
        }

        public void setHttpResult(HttpResult<T> httpResult) {
            this.httpResult = httpResult;
        }
    }

}

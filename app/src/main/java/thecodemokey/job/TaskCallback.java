package thecodemokey.job;

/**
 * Created by asd on 2016-03-30.
 */
public interface TaskCallback<T> {

    void onStart();

    void onSuccess(T obj);

    void onFailure(Exception exception);
}
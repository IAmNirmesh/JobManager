package thecodemokey.job;

/**
 * Created by asd on 2016-03-30.
 */
public interface Task<T> {
    public T executeTask() throws Exception;
}
package thriftserver.backend;

public interface StorageHandler<T> {

  public void put(T user);
  public T get(int uid);
  public boolean contains(int uid);
}

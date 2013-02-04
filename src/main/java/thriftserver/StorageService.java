package thriftserver;

import org.apache.thrift.TException;
import thriftserver.UserStorage;
import thriftserver.UserProfile;
import thriftserver.backend.Redis;
import thriftserver.backend.HashTable;
import thriftserver.backend.StorageHandler;

import java.util.HashMap;

public class StorageService implements UserStorage.Iface {
  //private static StorageHandler<UserProfile> storage = new Redis("localhost");
  private static StorageHandler<UserProfile> storage = new HashTable();

  @Override
  public void store(UserProfile user) throws TException {
    System.out.println("Storing " + user.getName() + "with id" + user.getUid());
    storage.put(user);
  }

  @Override
  public UserProfile retrieve(int uid) throws TException {
    return storage.contains(uid) ? storage.get(uid) : null;
  }
}

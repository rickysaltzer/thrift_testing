package thriftserver.backend;

import java.util.HashMap;
import thriftserver.UserProfile;

public class HashTable implements StorageHandler<UserProfile>{
  private static HashMap<Integer, UserProfile> map = new HashMap<Integer, UserProfile>();

  @Override
  public void put(UserProfile user) {
    map.put(user.getUid(), user);
  }

  @Override
  public UserProfile get(int uid) {
    return map.containsKey(uid) ? map.get(uid) : null;
  }

  @Override
  public boolean contains(int uid) {
    return map.containsKey(uid);
  }
}

package thriftserver.backend;

import thriftserver.UserProfile;
import redis.clients.jedis.Jedis;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.TDeserializer;

public class Redis implements StorageHandler<UserProfile> {
  private static Jedis redis;
  private static TSerializer serializer = new TSerializer();
  private static TDeserializer deserializer = new TDeserializer();

  public Redis(String hostname) {
    redis = new Jedis(hostname);
    System.out.println("Connected to Redis");
  }

  @Override
  public void put(UserProfile user) {
    try {
      redis.set(Integer.toString(user.getUid()), serializer.toString(user, "UTF-8"));
    } catch (TException e) {
      e.printStackTrace();
    }
  }

  @Override
  public UserProfile get(int uid) {
    UserProfile user = new UserProfile();
    String result = redis.get(Integer.toString(uid));
    if (!result.isEmpty()) {
      try {
        deserializer.deserialize(user, result, "UTF-8");
      } catch (TException e) {
        e.printStackTrace();
      }
    } else {
      return null;
    }
    return user;
  }

  @Override
  public boolean contains(int uid) {
    return redis.exists(Integer.toString(uid));
  }
}

package thriftserver;

import thriftserver.UserProfile;
import thriftserver.UserStorage;
import thriftserver.Server.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      Server.StartServer(new UserStorage.Processor<StorageService>(new StorageService()));
    }
}

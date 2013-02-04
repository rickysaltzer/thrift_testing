package thriftserver;

import thriftserver.UserProfile;
import thriftserver.UserStorage;

import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
//import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;

public class Server {
  public static void StartServer(UserStorage.Processor<StorageService> processor) {
    try {
      TServerTransport serverTransport = new TServerSocket(9090);
      //TServer server = new TSimpleServer(
      //        new Args(serverTransport).processor(processor));

      TServer server = new TThreadPoolServer(new
        TThreadPoolServer.Args(serverTransport).processor(processor));

      System.out.println("Starting the simple server...");
      server.serve();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

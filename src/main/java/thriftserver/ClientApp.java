package thriftserver;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import thriftserver.UserStorage;
import thriftserver.UserProfile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClientApp {

  public static void main(String[] args) {
    try {
      long startTime = System.currentTimeMillis();
      TTransport transport;

      transport = new TSocket("172.16.11.129", 9090);
      transport.open();

      TProtocol protocol = new TBinaryProtocol(transport);
      UserStorage.Client client = new UserStorage.Client(protocol);
      int counter = 0;
      BufferedReader reader = new BufferedReader(new FileReader("/home/ricky/sandbox/thriftserver/names.out"));
      String currLine;
      while ((currLine = reader.readLine()) != null) {
        UserProfile user = new UserProfile(counter++, currLine, "hi");
        client.store(user);
        System.out.println("Stored " + user.name);
      }

      long endTime = System.currentTimeMillis();
      System.out.println("Elapsed: " + ((endTime - startTime) / 1000.0) + " seconds");
      System.out.println("Inserted " + counter + " users");

      transport.close();
    } catch (TTransportException e) {
      e.printStackTrace();
    } catch (TException x) {
      x.printStackTrace();
    } catch (FileNotFoundException f) {
      f.printStackTrace();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

}
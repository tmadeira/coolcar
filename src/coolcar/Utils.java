package coolcar;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class Utils {
  // Copiado de
  // http://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java
  public static String md5File(String file) throws Exception {
    byte[] b = createChecksum(file);
    String result = "";
    for (int i = 0; i < b.length; i++) {
      result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
    }
    return result;
  }

  public static byte[] createChecksum(String filename) throws Exception {
    InputStream fis = new FileInputStream(filename);

    byte[] buffer = new byte[1024];
    MessageDigest complete = MessageDigest.getInstance("MD5");
    int numRead;

    do {
      numRead = fis.read(buffer);
      if (numRead > 0) {
        complete.update(buffer, 0, numRead);
      }
    } while (numRead != -1);

    fis.close();
    return complete.digest();
  }
}

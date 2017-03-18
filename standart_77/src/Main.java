/**
 * Created by alina on 16.03.2017.
 */
import com.sun.javafx.scene.layout.region.Margins;
import javafx.scene.shape.Path;

import javax.xml.bind.DatatypeConverter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.BitSet;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(CryptographySBT.getHash());
        long stop = System.currentTimeMillis();
        System.out.println((stop - start)/1000);
    }

}

/*
 *  Copyright 2010 BigData.mx
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package mx.bigdata.sat.cfd.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.swing.JOptionPane;

import mx.bigdata.sat.cfd.CFD2;
import mx.bigdata.sat.cfd.CFD2Factory;
import mx.bigdata.sat.common.ValidationErrorHandler;
import mx.bigdata.sat.security.KeyLoader;

import org.xml.sax.SAXParseException;

public final class CLI {

  public static void main(String[] args) throws Exception {
    String cmd = args[0];
    if (cmd.equals("validar")) {
      String file = args[1];
      CFD2 cfd = CFD2Factory.load(new File(file));
      ValidationErrorHandler handler = new ValidationErrorHandler();
      cfd.validar(handler);
      List<SAXParseException> errors = handler.getErrors();
      if (errors.size() > 0) {
        for (SAXParseException e : errors) {
          System.err.printf("%s %s\n", file, e.getMessage());
        }
        System.exit(1);
      }
    } else if (cmd.equals("verificar")) {
      String file = args[1];
      CFD2 cfd = CFD2Factory.load(new File(file));
      if (args.length == 3) {
        Certificate cert = KeyLoader
        .loadX509Certificate(new FileInputStream(args[2]));
        cfd.verificar(cert);
      } else {
        cfd.verificar();
      }
    } else if (cmd.equals("sellar")) { 
      //JOptionPane.showMessageDialog(null, "sellar");
      String file = args[1];
      CFD2 cfd = CFD2Factory.load(new File(file));
      PrivateKey key = KeyLoader
        .loadPKCS8PrivateKey(new FileInputStream(args[2]), args[3]);
      X509Certificate cert = KeyLoader
        .loadX509Certificate(new FileInputStream(args[4]));
      cfd.sellar(key, cert);
      OutputStream out = new FileOutputStream(args[5]);
      cfd.guardar(out);
      System.out.println("Proceso terminado archivo guardado en " + args[5]);
    } 
  }
}
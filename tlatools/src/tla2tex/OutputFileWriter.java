// Copyright (c) 2003 Compaq Corporation.  All rights reserved.

/***************************************************************************
* CLASS OutputFileWriter                                                   *
*                                                                          *
* An OutputFileWriter returns an object for writing an ouput file in the   *
* user's directory.  The object's public methods are putLine(str), which   *
* writes the string str as a line of the output file, and close().         *
***************************************************************************/
package tla2tex;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import util.FileUtil;

public class OutputFileWriter
 { private Writer fileWriter = null ;
   private String name = "" ;

   public OutputFileWriter(String fileName)
   /**********************************************************************
   * Construct an OutputFileWriter from a file name.                     *
   **********************************************************************/
  { name = fileName ;
    try {fileWriter = new OutputStreamWriter(new FileOutputStream(fileName), FileUtil.UTF8) ;}
    catch (java.io.IOException e)
     { Debug.ReportError( 
         "TLATeX cannot open output file " + name + ".\n"
       + "    Perhaps the file is write-protected");
     };
  }

   public OutputFileWriter(OutputStream out, String fileName)
  { name = fileName ;
    fileWriter = new OutputStreamWriter(out, FileUtil.UTF8) ;
  }

   public OutputFileWriter(Writer out, String fileName)
  { name = fileName ;
    fileWriter = out ;
  }

   public void putLine(String out)
     /**********************************************************************
     * Writes the string out followed by '\n'.                             *
     **********************************************************************/
    { try {fileWriter.write(out + "\n");
           fileWriter.flush();
          }
      catch (java.io.IOException e)
       { Debug.ReportError( 
           "Error trying to write to output file " + name + ".\n"
         + "    Perhaps there is a file-system problem.");
       };
    }
     
   public void close()
    {try {fileWriter.close();}
      catch (java.io.IOException e)
       { Debug.ReportError( 
           "Error trying to close output file " + name + ".\n"
         + "    Perhaps there is a file-system problem.");
       };
    }
 }  // END class
adnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:20 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:30 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:30 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:33 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:33 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:34 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:34 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:34 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:35 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:35 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:35 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:48 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:48 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:48 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:48 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00000057
CatalogDB: 4:44:49 PM 7/19/2014: catadnew.cpp at line #1029 encountered error 0x00th; i++)
         {
            IIOImage iioImage = new IIOImage(images[i], null, null);
            if (writer.canInsertImage(i)) writer.writeInsert(i, iioImage, null);
         }
      }
      catch (IOException e)
      {
         JOptionPane.showMessageDialog(this, e);
      }
   }

   /**
    * Gets a set of "preferred" format names of all image writers. The preferred format name is the
    * first format name that a writer specifies.
    * @return the format name set
    */
   public static Set<String> getWriterFormats()
   {
      Set<String> writerFormats = new TreeSet<>();
      Set<String> formatNames = new TreeSet<>(Arrays.asList(ImageIO
            .getWriterFormatNames()));
      while (formatNames.size() > 0)
      {
         String name = formatNames.iterator().next();
         Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(name);
         ImageWriter writer = iter.next();
         String[] names = writer.getOriginatingProvider().getFormatNames();
         String format = names[0];
         if (format.equals(format.toLowerCase())) format = format.toUpperCase();
         writerFormats.add(format);
         formatNames.removeAll(Arrays.asList(names));
      }
      return writerFormats;
   }
}

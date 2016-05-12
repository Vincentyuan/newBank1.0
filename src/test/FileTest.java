package test;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
public class FileTest {
 public static void main(String[] args) {
  // �����ļ�ѡ����
  JFileChooser fileChooser = new JFileChooser();
  // ���õ�ǰĿ¼
  fileChooser.setCurrentDirectory(new File("."));
  fileChooser.setAcceptAllFileFilterUsed(false);
  final String[][] fileENames = {    { ".xls", "MS-Excel 2003 �ļ�(*.xls)" }
           };
  
  // ��ʾ�����ļ�
  fileChooser.addChoosableFileFilter(new FileFilter() {
   public boolean accept(File file) {
    return true;
   }
   public String getDescription() {
    return "�����ļ�(*.*)";
   }
  });
  
  // ѭ�������Ҫ��ʾ���ļ�
  for (final String[] fileEName : fileENames) {
   
   fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
 
    public boolean accept(File file) { 
 
     if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
 
      return true;
     }
 
     return false;
    }
 
    public String getDescription() {
 
     return fileEName[1];
    }
 
   });
  }
  
  fileChooser.showDialog(null, null);
 }
}
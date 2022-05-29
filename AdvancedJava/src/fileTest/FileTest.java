package fileTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {
    
    public static void main(String[] args) {
        //원본 파일경로
        String oriFilePath = "D:\\Others\\origin.txt";
        //복사될 파일경로
        String copyFilePath = "D:\\Others\\copy.txt";
        
        //파일객체생성
        File oriFile = new File(oriFilePath);
        //복사파일객체생성
        File copyFile = new File(copyFilePath);
        
        try {
            
            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
            
            int fileByte = 0; 
            // fis.read()가 -1 이면 파일을 다 읽은것
            while((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }
            //자원사용종료
            fis.close();
            fos.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
package bitmatrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BarCodeFactory {


	public static void main(String[] args)  throws Exception{
//		String content="维总，你好啊";
//		MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
//		String pathString="D:/MS/4.jpg";
//		Path path = Paths.get(pathString);
//		Map<EncodeHintType, Object> map=new HashMap<EncodeHintType, Object>();
//		map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//		map.put(EncodeHintType.MARGIN, 0);
//		BitMatrix bitMatrix=multiFormatWriter.encode(content,BarcodeFormat.QR_CODE, 400, 400,map);
//		MatrixToImageWriter.writeToPath(bitMatrix, "jpg", path);
//		MatrixToImageConfig matrixToImageConfig=new MatrixToImageConfig();
		createBarCodeImg();
		}
	
	public static void createBarCodeImg() throws Exception{
		
		String message="http://www.baidu.com";
		
		String fileParentPath="src/main/webapp/image/barcode_img/";
		File file=new File(fileParentPath);
		if(!file.exists()){
			file.mkdirs();
		}
		Random random=new Random(new Date().getTime());
		String temp=String.valueOf((random.nextInt(9999999))+1000000);
		String seed=temp.substring(temp.length()-6,temp.length());
		
		MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
		BitMatrix bitMatrix=multiFormatWriter.encode(message, BarcodeFormat.QR_CODE, 300, 300, getEncodeHintTypeMap());
		MatrixToImageWriter.writeToPath(bitMatrix,"jpg", Paths.get(fileParentPath+seed+".jpg"));
		
		System.out.println(seed);
	}
	
	
	static private Map<EncodeHintType, Object> getEncodeHintTypeMap() throws FileNotFoundException,IOException {
		Map<EncodeHintType, Object> map=new HashMap<EncodeHintType, Object>();
		Properties properties=new Properties();
		properties.load(new FileInputStream(new File("src/main/resources/BarCode.properties")));
	
		map.put(EncodeHintType.CHARACTER_SET, properties.getProperty("CHARACTER_SET"));
//		map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		map.put(EncodeHintType.MARGIN, Integer.valueOf(properties.getProperty("MARGIN")));
//		map.put(EncodeHintType.MARGIN, 0);
		
		return map;
	}
}

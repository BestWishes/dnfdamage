package barcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BarcodeGenerator {

	public static String barcodeGenerator(String content,Object object) {
		String parentFilePath="src/main/webapp/image/barcode_img/";
		File parentFile=new File(parentFilePath);
		if(!parentFile.exists()){
			parentFile.mkdirs();
		}
		String fileName="";
		Map<EncodeHintType, Object> encodeHintTypeMap=new HashMap<EncodeHintType, Object>();
		encodeHintTypeMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		encodeHintTypeMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		encodeHintTypeMap.put(EncodeHintType.MARGIN, 20);
		
		MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
		BitMatrix bitMatrix=new BitMatrix(400, 400);
		try {
			bitMatrix=multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400);
			
			fileName=String.valueOf(new Random(new Date().getTime()).nextInt(9999999)+1000000);
					
			try {
				
				MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(parentFilePath+"/"+fileName+".jpg"));
				
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		} catch (WriterException e) {
			System.err.println(e.getMessage());
		}
		
		
		
		
		return fileName;
	}
	
	
	
}

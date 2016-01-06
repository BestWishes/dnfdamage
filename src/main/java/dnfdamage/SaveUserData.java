package dnfdamage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class SaveUserData
 */
@WebServlet("/saveAction")
public class SaveUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUserData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath=this.getServletContext().getRealPath("WEB-INF/saveFile");
		String tempPath=this.getServletContext().getRealPath("WEB-INF/tempFile");
		File tempFile=new File(tempPath);
		File saveFile=new File(savePath);

		Map<String, String> fileMap=new HashMap<String, String>();
		
		if(!tempFile.exists()){
			tempFile.mkdir();
		}
		if(!saveFile.exists()){
			saveFile.mkdir();
		}
		try {
			
			DiskFileItemFactory diskFileItemFactor=new DiskFileItemFactory();
			diskFileItemFactor.setSizeThreshold(1024*1000);
			diskFileItemFactor.setRepository(tempFile);
			ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactor);
			
			servletFileUpload.setHeaderEncoding("UTF-8");
			servletFileUpload.setFileSizeMax(1024*1024*10);
			servletFileUpload.setSizeMax(1024*1024*100);
			List<FileItem> fileItems=servletFileUpload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				
				if(!fileItem.isFormField()){
					String fileName=fileItem.getName();
					System.out.println(fileName);
					InputStream inputStream=fileItem.getInputStream();
					OutputStream outputStream=new FileOutputStream(savePath+"\\"+fileName.substring(fileName.lastIndexOf("\\")+1));
					int len=0;
					byte[] bufferBytes=new byte[1024];
					while((len=inputStream.read(bufferBytes))>0){
						outputStream.write(bufferBytes, 0, len);
					}
					inputStream.close();
					outputStream.close();
				}
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			
			
		} catch (Exception e) {
			System.out.println();
		}
		
		response.sendRedirect("index.jsp");
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		File file=new File(this.getServletContext().getRealPath("/")+"save.txt");
//		Enumeration<String> enumeration=request.getParameterNames();
//		StringBuffer sb=new StringBuffer();
//		sb.append(request.getRemoteAddr()+":");
//		sb.append("\r\n");
//		while (enumeration.hasMoreElements()) {
//			String string = (String) enumeration.nextElement();
//			sb.append(string+":"+request.getParameter(string)+"\t");
//		}
//		sb.append("\r\n");
//		if(!file.exists()){
//			file.createNewFile();
//		}
//		FileWriter fileWriter=new FileWriter(file);
//		fileWriter.write(sb.toString());
//		fileWriter.flush();
//		fileWriter.close();
//		response.getWriter().write("success");
		doGet(request, response);
	}

}

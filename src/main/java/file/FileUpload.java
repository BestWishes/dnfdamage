package file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bitmatrix.BarCodeFactory;
import entity.FilesForDownLoad;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/file_upload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userName", "LJJ");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("WEB-INF/views/file_upload.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//文件夹
		File files=new File(this.getServletContext().getRealPath("/WEB-INF/uploadfiles/files"));
		//用于存放临时文件的文件夹
		File tempFile=new File(this.getServletContext().getRealPath("/WEB-INF/uploadfiles/tempfiles"));
		//判断文件夹是否存在
		if(!files.exists()){
			files.mkdir();
		}
		//判断文件夹是否存在
		if(!tempFile.exists()){
			tempFile.mkdir();
		}
		//文件解析器工厂
		DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
		//设置工厂的临时存储路径
		diskFileItemFactory.setRepository(tempFile);
		//设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
		diskFileItemFactory.setSizeThreshold(1024*1024);
		//创建servlet文件上传解析器
		ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
		//设置每个文件的最大值
		servletFileUpload.setFileSizeMax(1024*1024*10);
		//设置文件的总大小
		servletFileUpload.setSizeMax(1024*1024*100);
		//设置文件的编码格式
		servletFileUpload.setHeaderEncoding("UTF-8");
		//设置上传监听程序
		servletFileUpload.setProgressListener(new ProgressListener() {
			
			@Override
			public void update(long pBytesRead, long pContentLength, int pItems) {
				System.out.println("文件大小为:"+pContentLength+" 当前已经处理:"+pBytesRead+" 第几个文件:"+pItems);
			}
		});
		
		try {
			List<FileItem> fileItems=servletFileUpload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				//如果是普通的属性值
				if(fileItem.isFormField()){
					System.out.println("参数:"+fileItem.getFieldName()+" 值:"+fileItem.getString());
				}
				//如果是文件
				else {
					String filePath=this.getServletContext().getRealPath("/WEB-INF/uploadfiles/files")+"/"+fileItem.getName().substring(fileItem.getName().lastIndexOf("\\")+1);
					File file=new File(filePath);
					InputStream inputStream=fileItem.getInputStream();
					OutputStream outputStream=new FileOutputStream(file);
					byte[] byteBuffer=new byte[1024];
					int len=0;
					while((len=inputStream.read(byteBuffer))>0){
						outputStream.write(byteBuffer, 0, len);
					}
					inputStream.close();
					outputStream.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		String[] downloadFiles=files.list(); 
		List<FilesForDownLoad> filesForDownLoads=new ArrayList<FilesForDownLoad>();

		for (String string : downloadFiles) {
			FilesForDownLoad filesForDownLoad=new FilesForDownLoad();
			filesForDownLoad.setFileName(string);
			try {
//				BufferedImage bufferedImage=BarCodeFactory.createBarCode();
//				filesForDownLoad.setImage(bufferedImage);
//				filesForDownLoad.setGraphics2d(bufferedImage.createGraphics());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			filesForDownLoads.add(filesForDownLoad);
			
		}
		
		request.setAttribute("files", filesForDownLoads);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/views/file_download.jsp");
		dispatcher.forward(request, response);
	}

}

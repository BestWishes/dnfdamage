package dnfdamage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DownFile
 */
@WebServlet("/downFile")
public class DownFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filenameString=this.getServletContext().getRealPath("WEB-INF/saveFile")+"/"+request.getParameter("filename");
		File file=new File(filenameString);
		
		if(!file.exists()){
			response.getWriter().write("文件不存在，请刷新页面");
		}
		else {
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(request.getParameter("filename"), "UTF-8"));
			InputStream inputStream=new FileInputStream(filenameString);
			OutputStream outputStream=response.getOutputStream();
			byte[] buffer=new byte[1024];
			int len=0;
			while((len=inputStream.read(buffer))>0){
				outputStream.write(buffer, 0, len);
			}
			inputStream.close();
			outputStream.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

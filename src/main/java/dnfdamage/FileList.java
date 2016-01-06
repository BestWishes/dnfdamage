package dnfdamage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONWriter;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class FileList
 */
@WebServlet("/FileList")
public class FileList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list=new ArrayList<String>();
		File file=new File(this.getServletContext().getRealPath("WEB-INF/saveFile"));
		response.setContentType("application/json;charset=UTF-8");
		if(!file.exists()){
			response.getWriter().write(JSON.toJSONString("文件为空，请先上传文件"));
		}else {
			File[] files=file.listFiles();
			for (File file2 : files) {
				list.add(file2.getName().substring(file2.getName().lastIndexOf("\\")+1));
			}
			response.getWriter().write(JSON.toJSONString(list));
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}

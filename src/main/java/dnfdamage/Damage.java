package dnfdamage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.NumberFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Damage
 */
@WebServlet("/Damage")
public class Damage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static BigInteger acount=BigInteger.ZERO;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext servletContext=this.getServletContext();
		System.out.println(servletContext.getContextPath());
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
		Damage.acount=Damage.acount.add(BigInteger.ONE);
		Double result=1.0d;
		Double oldresult=1.0d;
		//职业（百分比、固伤）
		String zhiye=request.getParameter("zhiye");
		
		//属�?强化
		Double oldshuxin=Double.valueOf(request.getParameter("oldshuxin"));
		//附加伤害
		Double oldfujia=Double.valueOf(request.getParameter("oldfujia"))/100.0;
		//增加伤害
		Double oldzengjia=Double.valueOf(request.getParameter("oldzengjia"))/100.0;
		//增加暴击伤害
		Double oldbaoji=Double.valueOf(request.getParameter("oldbaoji"))/100.0;
		//暴击�?
		Double oldbaojilv=Double.valueOf(request.getParameter("oldbaojilv"))/100.0;
		//怪物属�?抗�?
		Double oldkangxing=Double.valueOf(request.getParameter("oldkangxing"));
		//怪物减伤
		Double oldjianshang=Double.valueOf(request.getParameter("oldjianshang"))/100.0;
		
		
		Double shuxin=Double.valueOf(request.getParameter("shuxin"));
		Double fujia=Double.valueOf(request.getParameter("fujia"))/100.0;
		Double zengjia=Double.valueOf(request.getParameter("zengjia"))/100.0;
		Double baoji=Double.valueOf(request.getParameter("baoji"))/100.0;
		Double baojilv=Double.valueOf(request.getParameter("baojilv"))/100.0;
		Double kangxing=Double.valueOf(request.getParameter("kangxing"));
		Double jianshang=Double.valueOf(request.getParameter("jianshang"))/100.0;
		
		if(zhiye.equals("dulizhiye")){
			//力量
			Double oldpower=Double.valueOf(request.getParameter("oldpower"));
			Double power=Double.valueOf(request.getParameter("power"));
			//独立
			Double oldduli=Double.valueOf(request.getParameter("oldduli"));
			Double duli=Double.valueOf(request.getParameter("duli"));
			
			oldresult= (1+oldduli/1000)*(1+oldpower/2500)*(1+(oldshuxin-oldkangxing)/220)*(oldbaojilv*(1.5+oldbaoji)+(1-oldbaojilv))*(1-oldjianshang)*(1+oldzengjia)*(1+oldfujia*(oldbaojilv*(1.5+oldbaoji)+(1-oldbaojilv)));
			result=(1+duli/1000)*(1+power/2500)*(1+(shuxin-kangxing)/220)*(baojilv*(1.5+baoji)+(1-baojilv))*(1-jianshang)*(1+zengjia)*(1+fujia*(baojilv*(1.5+baoji)+(1-baojilv)));

			
		}else{
			//面板基础攻击
			Double oldgongji=Double.valueOf(request.getParameter("oldgongji"));
			Double gongji=Double.valueOf(request.getParameter("gongji"));
			//无视攻击
			Double oldwushi=Double.valueOf(request.getParameter("oldwushi"));
			Double wushi=Double.valueOf(request.getParameter("wushi"));
			
			oldresult= ((oldgongji*(1-oldjianshang))+oldwushi)*(1+(oldshuxin-oldkangxing)/220.0)*(oldbaojilv*(1.5+oldbaoji)+(1-oldbaojilv))*(1+oldzengjia)*(1+oldfujia*(oldbaojilv*(1.5+oldbaoji)+(1-oldbaojilv)));
			result=((gongji*(1-jianshang))+wushi)*(1+(shuxin-kangxing)/220.0)*(baojilv*(1.5+baoji)+(1-baojilv))*(1+zengjia)*(1+fujia*(baojilv*(1.5+baoji)+(1-baojilv)));
		}
		NumberFormat numberFormat=NumberFormat.getPercentInstance();
		numberFormat.setMinimumFractionDigits(2);
		System.out.println(getServletContext().getRealPath("/"));
		File file=new File(getServletContext().getRealPath("/")+"submitacount.txt");
		if(!file.exists()){
			file.createNewFile();
			FileWriter fileWriter=new FileWriter(file);
			fileWriter.write(BigInteger.ONE.toString());
			fileWriter.flush();
			fileWriter.close();
		}else {
			FileReader fileReader=new FileReader(file);
			
			String fileAcount="";
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			fileAcount=bufferedReader.readLine();
			bufferedReader.close();
			fileReader.close();
			BigInteger submitacount=BigInteger.valueOf(Long.valueOf(fileAcount));
			submitacount=submitacount.add(BigInteger.ONE);
			FileWriter fileWriter=new FileWriter(file);
			fileWriter.write(submitacount.toString());
			fileWriter.flush();
			fileWriter.close();
		}
		response.getWriter().write(String.valueOf(numberFormat.format(result/oldresult-1)));
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
		Damage.acount=Damage.acount.add(BigInteger.ONE);
		Double result=1.0d;
		Double oldresult=1.0d;
		//职业（百分比、固伤）
		String zhiye=request.getParameter("zhiye");
		
		//属�?强化
		Double oldshuxin=Double.valueOf(request.getParameter("oldshuxin"));
		//附加伤害
		Double oldfujia=Double.valueOf(request.getParameter("oldfujia"))/100.0;
		//增加伤害
		Double oldzengjia=Double.valueOf(request.getParameter("oldzengjia"))/100.0;
		//增加暴击伤害
		Double oldbaoji=Double.valueOf(request.getParameter("oldbaoji"))/100.0;
		//暴击�?
		Double oldbaojilv=Double.valueOf(request.getParameter("oldbaojilv"))/100.0;
		//怪物属�?抗�?
		Double oldkangxing=Double.valueOf(request.getParameter("oldkangxing"));
		//怪物减伤
		Double oldjianshang=Double.valueOf(request.getParameter("oldjianshang"))/100.0;
		
		
		Double shuxin=Double.valueOf(request.getParameter("shuxin"));
		Double fujia=Double.valueOf(request.getParameter("fujia"))/100.0;
		Double zengjia=Double.valueOf(request.getParameter("zengjia"))/100.0;
		Double baoji=Double.valueOf(request.getParameter("baoji"))/100.0;
		Double baojilv=Double.valueOf(request.getParameter("baojilv"))/100.0;
		Double kangxing=Double.valueOf(request.getParameter("kangxing"));
		Double jianshang=Double.valueOf(request.getParameter("jianshang"))/100.0;
		
		if(zhiye.equals("dulizhiye")){
			//力量
			Double oldpower=Double.valueOf(request.getParameter("oldpower"));
			Double power=Double.valueOf(request.getParameter("power"));
			//独立
			Double oldduli=Double.valueOf(request.getParameter("oldduli"));
			Double duli=Double.valueOf(request.getParameter("duli"));
			
			oldresult= (1+oldduli/1000)*(1+oldpower/2500)*(1+(oldshuxin-oldkangxing)/220)*(oldbaojilv*(1.5+oldbaoji)+(1-oldbaojilv))*(1-oldjianshang)*(1+oldzengjia)*(1+oldfujia*(oldbaojilv*(1.5+oldbaoji)+(1-oldbaojilv)));
			result=(1+duli/1000)*(1+power/2500)*(1+(shuxin-kangxing)/220)*(baojilv*(1.5+baoji)+(1-baojilv))*(1-jianshang)*(1+zengjia)*(1+fujia*(baojilv*(1.5+baoji)+(1-baojilv)));

			
		}else{
			//面板基础攻击
			Double oldgongji=Double.valueOf(request.getParameter("oldgongji"));
			Double gongji=Double.valueOf(request.getParameter("gongji"));
			//无视攻击
			Double oldwushi=Double.valueOf(request.getParameter("oldwushi"));
			Double wushi=Double.valueOf(request.getParameter("wushi"));
			
			oldresult= ((oldgongji*(1-oldjianshang))+oldwushi)*(1+(oldshuxin-oldkangxing)/220.0)*(oldbaojilv*(1.5+oldbaoji)+(1-oldbaojilv))*(1+oldzengjia)*(1+oldfujia*(oldbaojilv*(1.5+oldbaoji)+(1-oldbaojilv)));
			result=((gongji*(1-jianshang))+wushi)*(1+(shuxin-kangxing)/220.0)*(baojilv*(1.5+baoji)+(1-baojilv))*(1+zengjia)*(1+fujia*(baojilv*(1.5+baoji)+(1-baojilv)));
		}
		NumberFormat numberFormat=NumberFormat.getPercentInstance();
		numberFormat.setMinimumFractionDigits(2);
		System.out.println(getServletContext().getRealPath("/"));
		File file=new File(getServletContext().getRealPath("/")+"submitacount.txt");
		if(!file.exists()){
			file.createNewFile();
			FileWriter fileWriter=new FileWriter(file);
			fileWriter.write(BigInteger.ONE.toString());
			fileWriter.flush();
			fileWriter.close();
		}else {
			FileReader fileReader=new FileReader(file);
			
			String fileAcount="";
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			fileAcount=bufferedReader.readLine();
			bufferedReader.close();
			fileReader.close();
			BigInteger submitacount=BigInteger.valueOf(Long.valueOf(fileAcount));
			submitacount=submitacount.add(BigInteger.ONE);
			FileWriter fileWriter=new FileWriter(file);
			fileWriter.write(submitacount.toString());
			fileWriter.flush();
			fileWriter.close();
		}
		response.getWriter().write(String.valueOf(numberFormat.format(result/oldresult-1)));
	}

}

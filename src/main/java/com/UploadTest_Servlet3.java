package com;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

//@WebServlet("/uploadServlet3.do")
@WebServlet("/back-end/uploadServlet3_simple.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
// ��ƾڶq�j��fileSizeThreshold�ȮɡA���e�N�Q�g�J�Ϻ�
// �W�ǹL�{���L�׬O��Ӥ��W�LmaxFileSize�ȡA�Ϊ̤W�Ǫ��`�q�j��maxRequestSize �ȳ��|�ߥXIllegalStateException ���`
public class UploadTest_Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded"; // �W���ɮת��ت��a�ؿ�;
											   // �N�ѩ��U����26~30��� java.io.File �� ContextPath ���U, �۰ʫإߥئa�ؿ�

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8"); // �B�z�����ɦW
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		System.out.println("ContentType="+req.getContentType()); // ���ե�

		String realPath = getServletContext().getRealPath(saveDirectory);
		System.out.println("realPath="+realPath); // ���ե�
		File fsaveDirectory = new File(realPath);
		if (!fsaveDirectory.exists())
			 fsaveDirectory.mkdirs(); // �� ContextPath ���U,�۰ʫإߥئa�ؿ�

		Collection<Part> parts = req.getParts(); // Servlet3.0�s�W�FPart�����A���ڭ̤�K���i���ɮפW�ǳB�z
		out.write("<h2> Total parts : " + parts.size() + "</h2>");

		for (Part part : parts) {
			String filename = part.getSubmittedFileName();
			if (filename!= null && filename.length()!=0 && part.getContentType()!=null) {
				out.println("<PRE>");
				out.println("name: " + part.getName());
				out.println("filename: " + filename);
				out.println("ContentType: " + part.getContentType());
				out.println("size: " + part.getSize());
				
				File f = new File(fsaveDirectory, filename);
				out.println("File: " + f);

				// �Q��File����,�g�J�ئa�ؿ�,�W�Ǧ��\
				part.write(f.toString());

				// �B�~���� InputStream �P byte[] (���N��model��VO�w�@�ǳ�)
				InputStream in = part.getInputStream();
				byte[] buf = new byte[in.available()];   // byte[] buf = in.readAllBytes();  // Java 9 ���s��k
				in.read(buf);
				in.close();
				out.println("buffer length: " + buf.length);
				
				// �B�~���ըq��
				out.println("<br><img src=\""+req.getContextPath()+saveDirectory+"/"+filename+"\">");

				out.println();
				out.println("</PRE>");
			}
		}
	}
}
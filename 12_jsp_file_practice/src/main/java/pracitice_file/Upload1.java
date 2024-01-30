package pracitice_file;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;




@WebServlet("/upload1")
public class Upload1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	//String saveDirectory = "C:\\Users\\82108\\git\\12_jsp_basic\\12_jsp_basic\\src\\main\\webapp\\chapter09_file\\fileRepository\\";
		
		MultipartRequest multipartRequest = new MultipartRequest(request,FileConfig.FILE_REPOSITORY_PATH,1024*1024 *100, "utf-8");
	
		// 질문! <String> -> <?>상관은 없다.
		Enumeration<?> files = multipartRequest.getFileNames();
		
		if(files.hasMoreElements()) {
			String element =(String) files.nextElement();
			
			multipartRequest.getOriginalFileName(element);
			multipartRequest.getFilesystemName(element);
			multipartRequest.getContentType(element);
			multipartRequest.getFile(element).length();
			

			String jsScript = """
				<script>
					alert('파일을 업로드 하였습니다.');
					location.href = 'fileMain';
				</script>""";
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsScript);
		}
	}

}

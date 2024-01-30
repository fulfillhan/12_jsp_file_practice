package pracitice_file;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;


@WebServlet("/upload2")
public class Upload2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String saveDirectory = "C:\\Users\\82108\\git\\12_jsp_basic\\12_jsp_basic\\src\\main\\webapp\\chapter09_file\\fileRepository\\";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, FileConfig.FILE_REPOSITORY_PATH,1024 * 1024 * 100 , "utf-8" );
		
		Enumeration<String> files = multipartRequest.getFileNames();
		
		while(files.hasMoreElements()) {
			String element = files.nextElement();
			if(multipartRequest.getOriginalFileName(element) != null) {
				 String originalFileName= multipartRequest.getOriginalFileName(element);
				UUID uuid = UUID.randomUUID();
				String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //"." 뒤의 인덱스 끝까지 반환
				String fileUUID = uuid + extension;
			}
		}
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

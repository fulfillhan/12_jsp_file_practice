package pracitice_file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//String saveDirectory = "C:\\Users\\82108\\git\\12_jsp_basic\\12_jsp_basic\\src\\main\\webapp\\chapter09_file\\fileRepository\\";
		
		String deleteFileName = request.getParameter("deleteFileName");
		File file = new File(FileConfig.FILE_REPOSITORY_PATH + deleteFileName);

		String jsScript = "";
		if (file.exists()) {// 파일이 존재하면
			file.delete();
			jsScript = """
					<script>
						alert('파일을 삭제 하였습니다.');
						location.href = 'fileMain';
					</script>""";
		} else {
			jsScript = """
					<script>
						alert('존재하지 않는 파일입니다.');
						history.go(-1);
					</script>""";

		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsScript);

	}

}

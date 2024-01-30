package pracitice_file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
  private final String FILE_REPOSITORY_PATH = FileConfig.FILE_REPOSITORY_PATH;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//파일명이 한국어 일수도 있으니깐.
		
		//String saveDirectory = "C:\\Users\\82108\\git\\12_jsp_basic\\12_jsp_basic\\src\\main\\webapp\\chapter09_file\\fileRepository\\";
		String fileName = request.getParameter("fileName");//download?fileName=testImg1.PNG 여기에서 가지고오기
		 
		 String downloadFilePath = FILE_REPOSITORY_PATH + fileName;
		 File file = new File(fileName);
		 
		// 파일을 읽어 스트림에 담기
			File file0w = new File(downloadFilePath);
			FileInputStream in = new FileInputStream(downloadFilePath);

			// 한글명 파일 처리 명령어
			fileName = new String(fileName.getBytes("utf-8"), "8859_1");

			// 파일다운로드 헤더지정
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

			// 다운로드 명령어
			OutputStream os = response.getOutputStream();

			int length;
			byte[] b = new byte[(int) file.length()];

			while ((length = in.read(b)) > 0) {
				os.write(b, 0, length);
			}

			os.flush();

			os.close();
			in.close();

		}

	
	
	}




package article.uploadFile;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;

import auth.service.User;

public class RenameFile implements FileRenamePolicy{

	User user;
	String newFileName;
	
	public RenameFile(User user) {
		this.user = user;
		System.out.println(user.getId());
	}

	public File rename(File file) {
//		String uploadPath = "C:/project/spring/spring00/src/main/webapp/WEB-INF/views/sample/img";
		String uploadPath = "C:/project/work/board/src/main/webapp/imgs";
        String fileName = file.getName(); 
        String extension = ""; 
        
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            extension = fileName.substring(dotIndex); 
            fileName = fileName.substring(0, dotIndex); 
        }
        
        newFileName = user.getId() + extension;
        
        File newFile = new File(uploadPath, newFileName);
        
        return newFile;
    }
	
	public String name() {
		return newFileName;
	}
}

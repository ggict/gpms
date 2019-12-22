package egovframework.cmmn.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.springframework.web.multipart.MultipartFile;

import kr.go.gg.gpms.attachfile.service.model.AttachFileVO;

public class FileUploadUtils {

	// 공통 파일 업로드
	public static List<AttachFileVO> saveFileList(String uploadPath, String kind
			, List<MultipartFile> files) throws Exception {

		// 파일 리스트
		List<AttachFileVO> fileList = new ArrayList<AttachFileVO>();

		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(currentDate);
		uploadPath += kind + File.separator + date;

		// 폴더 경로
		File uploadFolder =  new File(checkFilePath(uploadPath,"path"));

		if(!uploadFolder.exists() || !uploadFolder.isFile()){
			uploadFolder.mkdirs();
		}

		if(uploadFolder.exists()){

			// 파일명을 변경
			String transFileNm = "";
			String orginlFileNm = "";

			for(MultipartFile file : files) {
				transFileNm = UUID.randomUUID().toString();
				orginlFileNm = file.getOriginalFilename();
				if (!"".equals(orginlFileNm)) {

					if(orginlFileNm.contains(".")){
						transFileNm += orginlFileNm.substring(orginlFileNm.lastIndexOf("."));
					}

					String filePath =  checkFilePath(uploadPath,"path") + File.separator
							+ checkFilePath(transFileNm, "name");
					file.transferTo(new File(filePath));

					AttachFileVO attachFileVO = new AttachFileVO();

					attachFileVO.setFILE_NM(transFileNm);
					attachFileVO.setORGINL_FILE_NM(orginlFileNm);
					attachFileVO.setFILE_COURS(uploadPath);
					attachFileVO.setFILE_SIZE(Integer.toString((int)file.getSize()));
					fileList.add(attachFileVO);
				}
			}
		}

		return fileList;
	}

	public static AttachFileVO saveFile(String uploadPath, String kind, MultipartFile file) throws Exception {

		// 파일 리스트
		AttachFileVO fileInfo = new AttachFileVO();

		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(currentDate);
		uploadPath += kind + File.separator + date;

		// 폴더 경로
		File uploadFolder =  new File(checkFilePath(uploadPath, "path"));

		if(!uploadFolder.exists() || !uploadFolder.isFile()){
			uploadFolder.mkdirs();
		}

		if(uploadFolder.exists()){

			// 파일명을 변경
			String transFileNm = "";
			String orginlFileNm = "";

			transFileNm = UUID.randomUUID().toString();
			orginlFileNm = file.getOriginalFilename();
			if (!"".equals(orginlFileNm)) {

				if(orginlFileNm.contains(".")){
					transFileNm += orginlFileNm.substring(orginlFileNm.lastIndexOf("."));
				}

				String filePath =  checkFilePath(uploadPath, "path") + File.separator
						+ checkFilePath(transFileNm, "name");
				file.transferTo(new File(filePath));

				AttachFileVO attachFileVO = new AttachFileVO();

				attachFileVO.setFILE_NM(transFileNm);
				attachFileVO.setORGINL_FILE_NM(orginlFileNm);
				attachFileVO.setFILE_COURS(uploadPath);
				attachFileVO.setFILE_SIZE(Integer.toString((int)file.getSize()));
				fileInfo = attachFileVO;
			}

		}

		return fileInfo;
	}

	//dwg Zip File
	public static File createDwgZipFile(String[] FileNmList, String uploadPath,
			String kind, String roadNo, String dwgPath) throws Exception {
		File zipFile = null;

		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(currentDate);
		uploadPath += File.separator + kind + File.separator + date;

		String zipFileNm =UUID.randomUUID().toString() + ".zip";

		// Set the content type based to zip
		try{
			File uploadFolder =  new File(checkFilePath(uploadPath, "path"));

			if(!uploadFolder.exists() || !uploadFolder.isFile()){
				uploadFolder.mkdirs();
			}

			zipFile = new File(checkFilePath(uploadPath, "path") + "\\" + checkFilePath(zipFileNm,"name"));
			//zip 파일로 다운로드
			ZipArchiveOutputStream zos = new ZipArchiveOutputStream(new FileOutputStream(zipFile));
	        byte bytes[] = new byte[2048];

	        for(String dwgNm : FileNmList) {
	        	String fullPath = dwgPath + "\\" + roadNo + "\\" + dwgNm;
	        	//dwg 파일
				File dwgfile = new File(checkFilePath(fullPath,"path") + ".dwg");

				if(!dwgfile.exists()){
					System.out.println(fullPath+".dwg");
					System.out.println("파일 없음!");
				}else {
					zos.putArchiveEntry(new ZipArchiveEntry(dwgfile.getName()));
					// Get the file
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(dwgfile);

					} catch (FileNotFoundException fnfe) {
						zos.write(("ERRORld not find file " + dwgfile.getName())
								.getBytes());
						zos.closeArchiveEntry();
						fis.close();
						continue;
					}

					BufferedInputStream bis = new BufferedInputStream(fis);

					// Write the contents of the file
					int bytesRead;
		            while ((bytesRead = bis.read(bytes)) != -1) {
		                zos.write(bytes, 0, bytesRead);
		            }
		            zos.closeArchiveEntry();
		            bis.close();
		            fis.close();
				}

				//dwf 파일
				File dwffile = new File(checkFilePath(fullPath,"path") + ".dwf");

				if(!dwffile.exists()){
					System.out.println(fullPath+".dwf");
					System.out.println("파일 없음!");
				}else {
					zos.putArchiveEntry(new ZipArchiveEntry(dwffile.getName()));
					// Get the file
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(dwgfile);

					} catch (FileNotFoundException fnfe) {
						zos.write(("ERRORld not find file " + dwffile.getName())
								.getBytes());
						zos.closeArchiveEntry();
						fis.close();
						continue;
					}

					BufferedInputStream bis = new BufferedInputStream(fis);

					// Write the contents of the file
					int bytesRead;
		            while ((bytesRead = bis.read(bytes)) != -1) {
		                zos.write(bytes, 0, bytesRead);
		            }
		            zos.closeArchiveEntry();
		            bis.close();
		            fis.close();
				}

			}
			zos.close();

		}catch(IOException ex){
		}

		return zipFile;
	}

	/**
	 * 경로순회(directory traversal) 문자열을 제거
	 * author : skc@muhanit.kr
	 * @param path
	 * @return
	 */
	public static String checkFilePath(String filePath, String type) throws Exception {
		if(type == "name"){
			filePath = filePath.replaceAll("\\.\\.", "").replaceAll("/", "").replaceAll("\\\\", "");
		}else if(type == "path"){
			filePath = filePath.replaceAll("\\.\\.", "").replaceAll("\\\\", "/").replaceAll("//", "/");
		}else{
			filePath = "";
		}

		return filePath;
	}

}

package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import org.zerock.domain.AttachFileDTO;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {

		log.info("upload form");
	}

	// @PostMapping("/uploadFormAction")
	// public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
	//
	// for (MultipartFile multipartFile : uploadFile) {
	//
	// log.info("-------------------------------------");
	// log.info("Upload File Name: " +multipartFile.getOriginalFilename());
	// log.info("Upload File Size: " +multipartFile.getSize());
	// }
	// }

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

		String uploadFolder = "/Users/cjoungi/upload";

		for (MultipartFile multipartFile : uploadFile) {

			log.info("-------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			} // end catch
		} // end for

	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {

		log.info("upload ajax");
	}

	 /* @PostMapping("/uploadAjaxAction")
	 public void uploadAjaxPost(MultipartFile[] uploadFile) {

	 log.info("update ajax post.........");

		 String uploadFolder = "/Users/cjoungi/upload";

	 for (MultipartFile multipartFile : uploadFile) {

	 log.info("-------------------------------------");
	 log.info("Upload File Name: " + multipartFile.getOriginalFilename());
	 log.info("Upload File Size: " + multipartFile.getSize());

	 String uploadFileName = multipartFile.getOriginalFilename();

	 // IE has file path
	 uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
	 log.info("only file name: " + uploadFileName);

	 File saveFile = new File(uploadFolder, uploadFileName);

	 try {

	 multipartFile.transferTo(saveFile);
	 } catch (Exception e) {
	 log.error(e.getMessage());
	 } // end catch

	 } // end for

	 }*/

	// 오늘 날짜의 경로를 문자열로 반환
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

	 /* @PostMapping("/uploadAjaxAction")
	 public void uploadAjaxPost(MultipartFile[] uploadFile) {

	 String uploadFolder = "/Users/cjoungi/upload";

	 // make folder --------
	 File uploadPath = new File(uploadFolder, getFolder());
	 log.info("upload path: " + uploadPath);

	 if (uploadPath.exists() == false) {
	 uploadPath.mkdirs();
	 }
	 // make yyyy/MM/dd folder

	 for (MultipartFile multipartFile : uploadFile) {

	 log.info("-------------------------------------");
	 log.info("Upload File Name: " + multipartFile.getOriginalFilename());
	 log.info("Upload File Size: " + multipartFile.getSize());

	 String uploadFileName = multipartFile.getOriginalFilename();

	 // IE has file path
	 uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") +
	 1);
	 log.info("only file name: " + uploadFileName);

	 // File saveFile = new File(uploadFolder, uploadFileName);
	 File saveFile = new File(uploadPath, uploadFileName);

	 try {

	 multipartFile.transferTo(saveFile);
	 } catch (Exception e) {
	 log.error(e.getMessage());
	 } // end catch

	 } // end for

	 }*/

	 /* @PostMapping("/uploadAjaxAction")
	 public void uploadAjaxPost(MultipartFile[] uploadFile) {

	 String uploadFolder = "/Users/cjoungi/upload";

	 // make folder --------
	 File uploadPath = new File(uploadFolder, getFolder());
	 log.info("upload path: " + uploadPath);

	 if (uploadPath.exists() == false) {
	 uploadPath.mkdirs();
	 }
	 // make yyyy/MM/dd folder

	 for (MultipartFile multipartFile : uploadFile) {

	 log.info("-------------------------------------");
	 log.info("Upload File Name: " + multipartFile.getOriginalFilename());
	 log.info("Upload File Size: " + multipartFile.getSize());

	 String uploadFileName = multipartFile.getOriginalFilename();

	 // IE has file path
	 uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") +
	 1);
	 log.info("only file name: " + uploadFileName);

	 UUID uuid = UUID.randomUUID();

	 uploadFileName = uuid.toString() + "_" + uploadFileName;

	 File saveFile = new File(uploadPath, uploadFileName);

	 try {

	 multipartFile.transferTo(saveFile);
	 } catch (Exception e) {
	 log.error(e.getMessage());
	 } // end catch

	 } // end for

	 }*/

	// 이미지 썸네일 생성전, 이미지 파일인지 체크
	private boolean checkImageType(File file) {

		try {
			log.info("file : "+file);
			String contentType = Files.probeContentType(file.toPath());
			log.info(contentType);
			return contentType.startsWith("image");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	 /* @PostMapping("/uploadAjaxAction")
	 public void uploadAjaxPost(MultipartFile[] uploadFile) {

	 tring uploadFolder = "/Users/cjoungi/upload";

	 // make folder --------
	 File uploadPath = new File(uploadFolder, getFolder());
	 log.info("upload path: " + uploadPath);

	 if (uploadPath.exists() == false) {
	 uploadPath.mkdirs();
	 }
	 // make yyyy/MM/dd folder

	 for (MultipartFile multipartFile : uploadFile) {

	 log.info("-------------------------------------");
	 log.info("Upload File Name: " + multipartFile.getOriginalFilename());
	 log.info("Upload File Size: " + multipartFile.getSize());

	 String uploadFileName = multipartFile.getOriginalFilename();

	 // IE has file path
	 uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") +
	 1);
	 log.info("only file name: " + uploadFileName);

	 UUID uuid = UUID.randomUUID();

	 uploadFileName = uuid.toString() + "_" + uploadFileName;

	 try {
	 File saveFile = new File(uploadPath, uploadFileName);
	 multipartFile.transferTo(saveFile);
	 // check image type file
	 if (checkImageType(saveFile)) {

	 FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" +
	 uploadFileName));

	 Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100,
	 100);

	 thumbnail.close();
	 }

	 } catch (Exception e) {
	 e.printStackTrace();
	 } //end catch
	 } // end for

	 }*/

	// AttachFileDTO 리스트 반환
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

		// 브라우저로 보내기 위한 파일 정보들은 담은 객체
		List<AttachFileDTO> list = new ArrayList<>();

		String uploadFolder = "/Users/cjoungi/upload";
		String uploadFolderPath = getFolder();

		// 연/월/일 폴더 생성
		File uploadPath = new File(uploadFolder, uploadFolderPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();    // 필요한 상위 폴더까지 한번에 생성
		}

		// uploadFile 배열 중 각 MultipartFile 객체에 대한 처리
		for (MultipartFile multipartFile : uploadFile) {

			// 브라우저로 전송할 파일 정보를 담고 있는 객체
			AttachFileDTO attachDTO = new AttachFileDTO();

			String uploadFileName = multipartFile.getOriginalFilename();

			// 브라우저 전송 객체에 파일 이름 저장
			attachDTO.setFileName(uploadFileName);

			// 중복 방지를 위한 UUID 적용 (예: bbabb4ee-7e2e-47c2-b35b-bbc1ebc89467_연락망_230901.pdf)
			UUID uuid = UUID.
					randomUUID
							();
			uploadFileName = uuid.toString() + "_" + uploadFileName;

			try {

				// 연/월/일 폴더에 저장
				File saveFile = new File(uploadPath, uploadFileName);

				// 업로드 된 파일(multipartFile)이 특정 위치(saveFile)로 복사됨
				multipartFile.transferTo(saveFile);

				// 브라우저 전송 객체에 uuid, 파일 경로 저장
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);

				// 이미지 파일이라면 섬네일 생성
				/*if (checkImageType(saveFile)) {

					// 브라우저 전송 객체에 이미지 파일 여부 저장
					attachDTO.setImage(true);

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.
							createThumbnail
									(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}*/

				// 브라우저로 보낼 AttachDTO 리스트에 AttachDTO 객체 추가
				list.add(attachDTO);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ResponseEntity 반환
		return new ResponseEntity<>(list, HttpStatus.
				OK
		);
	}

	@GetMapping("/display")	
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {

		log.info("fileName: " + fileName);

		File file = new File("/Users/cjoungi/upload/" + fileName);

		log.info("file: " + file);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	 // 첨부파일 다운로드
	 /* @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 @ResponseBody
	 public ResponseEntity<Resource> downloadFile(String fileName) {

	 log.info("download file: " + fileName);

	 Resource resource = new FileSystemResource("/Users/cjoungi/upload/" + fileName);

	 log.info("resource: " + resource);

	 //추가
	 String resourceName = resource.getFilename();

	 HttpHeaders headers = new HttpHeaders();
	 try {
		 headers.add("Content-Disposition",
				 "attachment; filename=" + new String(resourceName.getBytes("UTF-8"),
						 "ISO-8859-1"));
	 } catch (UnsupportedEncodingException e) {
		 e.printStackTrace();
	 }
	 return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	 }*/


	 // IE/EDGE에서 파일의 한글이름 다운로드 처리
	 /* @GetMapping(value="/download" , produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 @ResponseBody
	 public ResponseEntity<Resource>
	 // userAgent 추가
	 downloadFile(@RequestHeader("User-Agent")String userAgent, String fileName){

	 Resource resource = new FileSystemResource("/Users/cjoungi/upload/" + fileName);
	 // 추가
	 if(resource.exists() == false) {
	 	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }
	 String resourceName = resource.getFilename();
	 HttpHeaders headers = new HttpHeaders();
	 try {

	 boolean checkIE = (userAgent.indexOf("MSIE") > -1 ||
	 userAgent.indexOf("Trident") > -1);

	 String downloadName = null;

	 if (checkIE) {
	 downloadName = URLEncoder.encode(resourceName, "UTF8").replaceAll("\\+", " ");
	 } else {
	 downloadName = new String(resourceName.getBytes("UTF-8"), "ISO-8859-1");
	 }

	 headers.add("Content-Disposition", "attachment; filename=" + downloadName);

	 } catch (UnsupportedEncodingException e) {
	 e.printStackTrace();
	 }

	 return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	 }*/

	 /* @GetMapping(value="/download" ,
	 produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 @ResponseBody
	 public ResponseEntity<Resource>
	 downloadFile(@RequestHeader("User-Agent")String userAgent, String fileName){

	 Resource resource = new FileSystemResource("/Users/cjoungi/upload/" + fileName);

	 if(resource.exists() == false) {
	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

	 String resourceName = resource.getFilename();

	 //remove UUID
	 String resourceOriginalName =
	 resourceName.substring(resourceName.indexOf("_")+1);

	 HttpHeaders headers = new HttpHeaders();
	 try {

	 boolean checkIE = (userAgent.indexOf("MSIE") > -1 ||
	 userAgent.indexOf("Trident") > -1);

	 String downloadName = null;

	 if(checkIE) {
	 downloadName = URLEncoder.encode(resourceOriginalName,
	 "UTF8").replaceAll("\\+", " ");
	 }else {
	 downloadName = new
	 String(resourceOriginalName.getBytes("UTF-8"),"ISO-8859-1");
	 }

	 headers.add("Content-Disposition", "attachment; filename="+downloadName);

	 } catch (UnsupportedEncodingException e) {
	 e.printStackTrace();
	 }

	 return new ResponseEntity<Resource>(resource, headers,HttpStatus.OK);
	 }*/

	// 파일 이름에 UUID가 붙은 부분 제거 -> 순수한 파일 이름만으로 다운로드
	 @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {

		Resource resource = new FileSystemResource("/Users/cjoungi/upload/" + fileName);

		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		String resourceName = resource.getFilename();

		// remove UUID
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();
		try {

			String downloadName = null;

			if ( userAgent.contains("Trident")) {
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
			}else if(userAgent.contains("Edge")) {
				log.info("Edge browser");
				downloadName =  URLEncoder.encode(resourceOriginalName,"UTF-8");
			}else {
				log.info("Chrome browser");
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			log.info("downloadName: " + downloadName);

			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	

	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {

		log.info("deleteFile: " + fileName);

		File file;

		try {
			file = new File("/Users/cjoungi/upload/" + URLDecoder.decode(fileName, "UTF-8"));

			file.delete();

			if (type.equals("image")) {

				String largeFileName = file.getAbsolutePath().replace("s_", "");

				log.info("largeFileName: " + largeFileName);

				file = new File(largeFileName);

				file.delete();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>("deleted", HttpStatus.OK);

	}
	

}

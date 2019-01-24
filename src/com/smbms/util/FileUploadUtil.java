package com.smbms.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * �ļ��ϴ�������
 * 
 * @author Answer
 *
 */
public class FileUploadUtil {
	private String uploadPath;

	public FileUploadUtil(HttpServletRequest request, String uploadPath) {
		this.uploadPath = request.getSession().getServletContext().getRealPath(uploadPath);
	}

	// �ж��ļ���С
	public boolean isThanSize(MultipartFile mf, long size) {
		if (mf.getSize() > size) {
			return false;
		}
		return true;
	}

	// �ж��ļ�����
	public boolean isEquestType(MultipartFile mf, String... types) {
		if (types != null) {
			String mfType = FilenameUtils.getExtension(mf.getOriginalFilename());
			for (String type : types) {
				if (type.equalsIgnoreCase(mfType)) {
					return true;
				}
			}
		}
		return false;
	}

	// �ļ��ϴ�
	public String uploadFile(MultipartFile mf, String oid) {
		String newName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + oid + "."
				+ FilenameUtils.getExtension(mf.getOriginalFilename());
		try {
			mf.transferTo(new File(this.uploadPath, newName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return newName;
	}

	public String all(MultipartFile mf, long size, String oid, String... types) {
			if(isThanSize(mf, size)) {
				if(isEquestType(mf, types)) {
					return uploadFile(mf, oid);
				}
			}
			return "";
	}
}

package com.example.YouTube.service;

import com.example.YouTube.dto.AttachDTO;
import com.example.YouTube.entity.AttachEntity;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.AttachRepository;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class AttachService {

    @Value("${attach.upload.folder}")
    private String attachUploadFolder;

    @Value("${attach.download.url}")
    private String attachDownloadUrl;

    @Autowired
    private AttachRepository attachRepository;
    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day; // 2022/04/23
    }


    public String getExtension(String fileName) {
        // mp3/jpg/npg/mp4.....
        if (fileName == null) {
            throw new AppBadException("file name null");
        }
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }
    public AttachDTO save(MultipartFile file) {
        try {

            String pathFolder = getYmDString(); // 2022/04/23
            File folder = new File(attachUploadFolder + pathFolder); // attaches/2022/04/23

            if (!folder.exists()) folder.mkdirs();
            String fileName = UUID.randomUUID().toString();
            String extension = getExtension(file.getOriginalFilename()); //test.jpg

            byte[] bytes = file.getBytes();
            Path path = Paths.get(attachUploadFolder + pathFolder + "/" + fileName + "." + extension);
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setId(fileName);
            entity.setOriginName(file.getOriginalFilename());
            entity.setType(extension);
            entity.setPath(pathFolder);
            entity.setSize(file.getSize());
            entity.setUrl(attachDownloadUrl+fileName+"."+extension);
            attachRepository.save(entity);


            AttachDTO dto = new AttachDTO();
            dto.setId(entity.getId());
            dto.setUrl(attachDownloadUrl + fileName + "." + extension);
            return dto;
        } catch (IOException e) {
            throw new AppBadException("file upload");
        }
    }
    private AttachEntity getAttach(String fileName) {
        String id = fileName.split("\\.")[0];
        Optional<AttachEntity> optional = attachRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("File Not Found");
        }
        return optional.get();
    }

    public byte[] open(String fileName) {
        try {
            AttachEntity entity = getAttach(fileName);

            Path file = Paths.get(attachUploadFolder + entity.getPath() + "/" + fileName);
            return Files.readAllBytes(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    // not active
    public Resource download(String fileName) {
        try {
            AttachEntity entity = getAttach(fileName);


            File file = new File(attachUploadFolder + entity.getPath() + "/" + fileName);

            File read = file.getParentFile();
            File rFile = new File(read, entity.getOriginName());

            Resource resource = new UrlResource(rFile.toURI());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new AppBadException("could not read");
            }
        } catch (MalformedURLException e) {
            throw new AppBadException("smth wrong");
        }
    }
    public Page<AttachDTO> getWithPage(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<AttachEntity> pageObj = attachRepository.findAll(pageable);

        List<AttachEntity> entityList = pageObj.getContent();
        List<AttachDTO> dtoList = new ArrayList<>();


        for (AttachEntity entity : entityList) {
            AttachDTO dto = new AttachDTO();
            dto.setId(entity.getId());
            dto.setPath(entity.getPath());
            dto.setType(entity.getType());
            dto.setUrl(attachDownloadUrl + "/" + entity.getId() + "." + entity.getType());
            dto.setOriginalName(entity.getOriginName());
            dto.setSize(entity.getSize());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return new PageImpl<>(dtoList, pageable, pageObj.getTotalElements());
    }
    public String deleteById(String fileName) {
        try {
            AttachEntity entity = getAttach(fileName);
            Path file = Paths.get(attachUploadFolder + entity.getPath() + "/" + fileName);

            Files.delete(file);
            attachRepository.deleteById(entity.getId());

            return "deleted";
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AttachDTO getURL(String previewAttachId) {
        return getURL(previewAttachId);
    }
}

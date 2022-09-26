package pe.com.mauricio.javac.controller;

import pe.com.mauricio.javac.ResponseData;
import pe.com.mauricio.javac.entity.Attachment;
import pe.com.mauricio.javac.service.AttachmentService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downloadURl = "";
        attachment = attachmentService.saveAttachment(file);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseData(attachment.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        log.info("getFileType: {}",attachment.getFileType());
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
    @GetMapping
    public ResponseEntity<Object>data(){
        Attachment attachment=new Attachment();
        attachment.setId("00000000");
       return ResponseEntity.ok()
               //.contentType(MediaType.parseMediaType("1.0.0"))
               .header("version","1.0.0")
               .body(attachment);
    }
    @GetMapping("/http-servlet-response")
    public String usingHttpServletResponse(HttpServletResponse response) {
        response.addHeader("version", "1.0.0");
        return "Response with header using HttpServletResponse";
    }
}

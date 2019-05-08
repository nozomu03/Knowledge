package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import org.springframework.web.multipart.MultipartFile;
import kr.hs.dgsw.web01blog.Service.AttachmentService;
import kr.hs.dgsw.web01blog.domain.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    private AttachmentService as;
    private List<String> destFileName;
    List<Attachment> attachments;

    @PostMapping("/attachment/{postId}")
    private List<Attachment> Upload(@RequestParam List<MultipartFile> srcFile, @PathVariable Long postId){
        destFileName = new ArrayList<String>();
        attachments = new ArrayList<Attachment>();
        int count = 0;
        try {
            for (MultipartFile temp : srcFile) {
                destFileName.add("D://site/upload/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + UUID.randomUUID().toString() + "_" + temp.getOriginalFilename());
                File destFile = new File(destFileName.get(count));
                destFile.getParentFile().mkdirs();
                temp.transferTo(destFile);
                attachments.add(new Attachment(destFileName.get(count), postId));
            }
            return attachments;
        }catch (Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }
}

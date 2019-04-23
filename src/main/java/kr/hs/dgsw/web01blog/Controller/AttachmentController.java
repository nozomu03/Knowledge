package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import org.springframework.web.multipart.MultipartFile;
import kr.hs.dgsw.web01blog.Service.AttachmentService;
import kr.hs.dgsw.web01blog.domain.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.*;

import java.util.List;

@RestController
public class AttachmentController {
    @Autowired
    private AttachmentService as;


    @PostMapping("/attachment")
    private ResponseFormat Upload(@RequestBody Attachment comment){
        File destFile = new File(comment.getFilePath());
        Attachment temp =this.as.upload(comment.getId(), (MultipartFile) destFile);
        if(temp!=null)
            return new ResponseFormat(ResponseType.ATTACHMENT_STORED, "Stored");
        else
            return new ResponseFormat(ResponseType.FAIL, "Fail to store");
    }
}

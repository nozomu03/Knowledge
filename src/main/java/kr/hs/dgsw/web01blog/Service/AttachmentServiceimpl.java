package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Repository.AttachmentRepository;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.domain.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentServiceimpl implements AttachmentService{
    @Autowired
    private AttachmentRepository ar;

    @Autowired
    private PostRepository pr;

    @Override
    public Attachment upload(Long id, MultipartFile srcFile){
        String destFilename = "D:/tete/"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY/MM/DD")) +"/"+ UUID.randomUUID().toString()+"_"+srcFile.getOriginalFilename();
        try{
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            Attachment att = new Attachment(destFilename, id);
            this.ar.save(att);
            return att;
        }catch (IOException e) {
            return null;
        }
    }

    @Override
    public void Download(List<Attachment> picture, HttpServletRequest request, HttpServletResponse response) {

    }
}

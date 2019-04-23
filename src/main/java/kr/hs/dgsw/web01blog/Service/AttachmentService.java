package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.domain.Attachment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AttachmentService{
    Attachment upload(Long id, MultipartFile srcFile);
    void Download(List<Attachment> picture, HttpServletRequest request, HttpServletResponse response);
}

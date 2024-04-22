package com.ruoyi.work.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class Impor {
    private MultipartFile file;
    private String orgId;
}

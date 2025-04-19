package cn.liaozh.front_api.controller;

import cn.liaozh.common.aop.NotLogin;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.enums.AlbumEnum;
import cn.liaozh.common.enums.FileEnum;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.plugin.storage.StorageDriver;
import cn.liaozh.common.plugin.storage.UploadFilesVo;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.front_api.LikeFrontThreadLocal;
import cn.liaozh.front_api.service.IFileService;
import cn.liaozh.front_api.vo.upload.UploadImagesVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@Schema(name = "上传管理")
public class UploadController {

    @Resource
    IFileService iFileService;

    @NotLogin
    @PostMapping("/image")
    @Operation(summary = "上传图片")
    public AjaxResult<UploadImagesVo> image(HttpServletRequest request) {
        MultipartFile multipartFile;
        try {
            multipartFile = ((MultipartRequest) request).getFile("file");
        } catch (Exception e) {
            throw new OperateException("请正确选择上传图片!");
        }

        if (multipartFile == null) {
            throw new OperateException("请选择上传图片!");
        }

        String folder = "image";
        if (StringUtils.isNotEmpty(request.getParameter("dir"))) {
            folder += "/" + request.getParameter("dir");
        }

        StorageDriver storageDriver = new StorageDriver();
        UploadFilesVo vo = storageDriver.upload(multipartFile, folder, AlbumEnum.IMAGE.getCode());
        System.out.println(vo);

        Map<String, String> params = new HashMap<>();
        params.put("cid", "0");
        params.put("uid", String.valueOf(LikeFrontThreadLocal.getUserId()));
        params.put("source", String.valueOf(FileEnum.SOURCE_USER.getCode()));
        params.put("type", String.valueOf(FileEnum.IMAGE_TYPE.getCode()));
        params.put("name", vo.getName());
        params.put("url", vo.getUrl());
        Integer id = iFileService.fileAdd(params);

        UploadImagesVo upVo = new UploadImagesVo();
        upVo.setUrl(vo.getUrl());
        upVo.setUri(vo.getPath());
        upVo.setId(id);
        upVo.setType(FileEnum.IMAGE_TYPE.getCode());
        upVo.setName(vo.getName());
        upVo.setCid(0);
        return AjaxResult.success(upVo);
    }

}

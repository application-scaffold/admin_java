package cn.liaozh.admin_api.controller;

import cn.liaozh.admin_api.LikeAdminThreadLocal;
import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.aop.aspect.RequestType;
import cn.liaozh.admin_api.service.IFileService;
import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.enums.AlbumEnum;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.plugin.storage.StorageDriver;
import cn.liaozh.common.plugin.storage.UploadFilesVo;
import cn.liaozh.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminapi/upload")
@Tag(name = "上传文件管理")
public class UploadController {

    @Resource
    IFileService fileService;

    @Log(title = "上传图片", requestType = RequestType.File)
    @PostMapping("/image")
    @NotPower
    @Operation(summary = "上传图片")
    public AjaxResult image(@RequestParam("file") MultipartFile[] files, @RequestParam("cid") String requestCid) {

        String cid = StringUtils.isNotEmpty(requestCid) ? requestCid : "0";

        if (files == null || files.length == 0) {
            throw new OperateException("请选择上传图片!");
        }


        for (int i = 0; i < files.length; i++) {
            StorageDriver storageDriver = new StorageDriver();
            UploadFilesVo vo = storageDriver.upload(files[i], "image", AlbumEnum.IMAGE.getCode());
            Map<String, String> album = new LinkedHashMap<>();
            album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
            album.put("cid", cid);
            album.put("type", String.valueOf(AlbumEnum.IMAGE.getCode()));
//        album.put("size", vo.getSize().toString());
//        album.put("ext", vo.getExt());
            album.put("url", vo.getUrl());
            album.put("name", vo.getName());
            Integer id = fileService.fileAdd(album);
            vo.setId(id);
        }

        return AjaxResult.success();
    }

    @Log(title = "上传图片", requestType = RequestType.File)
    @PostMapping("/file")
    @NotPower
    @Operation(summary = "上传文件")
    public AjaxResult file(@RequestParam("file") MultipartFile[] files, @RequestParam("cid") String requestCid) {

        String cid = StringUtils.isNotEmpty(requestCid) ? requestCid : "0";

        if (files == null || files.length == 0) {
            throw new OperateException("请选择上传文件!");
        }


        for (int i = 0; i < files.length; i++) {
            StorageDriver storageDriver = new StorageDriver();
            UploadFilesVo vo = storageDriver.upload(files[i], "image", AlbumEnum.Doc.getCode());
            Map<String, String> album = new LinkedHashMap<>();
            album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
            album.put("cid", cid);
            album.put("type", String.valueOf(AlbumEnum.Doc.getCode()));
//        album.put("size", vo.getSize().toString());
//        album.put("ext", vo.getExt());
            album.put("url", vo.getUrl());
            album.put("name", vo.getName());
            Integer id = fileService.fileAdd(album);
            vo.setId(id);
        }

        return AjaxResult.success();
    }

    @Log(title = "上传视频", requestType = RequestType.File)
    @PostMapping("/video")
    @NotPower
    @Operation(summary = "上传视频")
    public AjaxResult video(@RequestParam("file") MultipartFile[] files, @RequestParam("cid") String requestCid) {

        String cid = StringUtils.isNotEmpty(requestCid) ? requestCid : "0";


        if (files == null || files.length == 0) {
            throw new OperateException("请选择上传视频!");
        }
        for (int i = 0; i < files.length; i++) {

            StorageDriver storageDriver = new StorageDriver();
            UploadFilesVo vo = storageDriver.upload(files[i], "video", AlbumEnum.Video.getCode());

            Map<String, String> album = new LinkedHashMap<>();
            album.put("cid", cid);
            album.put("aid", String.valueOf(LikeAdminThreadLocal.getAdminId()));
            album.put("type", String.valueOf(AlbumEnum.Video.getCode()));
//        album.put("ext", vo.getExt());
//        album.put("size", vo.getSize().toString());
            album.put("url", vo.getUrl());
            album.put("name", vo.getName());
            Integer id = fileService.fileAdd(album);

            vo.setId(id);
        }
        return AjaxResult.success();
    }

}

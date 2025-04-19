package cn.liaozh.front_api.service.impl;

import cn.liaozh.common.entity.file.File;
import cn.liaozh.common.mapper.album.FileCateMapper;
import cn.liaozh.common.mapper.album.FileMapper;
import cn.liaozh.common.util.*;
import cn.liaozh.front_api.service.IFileService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Map;

/**
 * 文件服务实现类
 */
@Service
public class FileServiceImpl implements IFileService {

    @Resource
    FileMapper fileMapper;

    @Resource
    FileCateMapper fileCateMapper;

    /**
     * 文件新增
     *
     * @author fzr
     * @param params 文件信息参数
     */
    @Override
    public Integer fileAdd(Map<String, String> params) {
        String name = params.get("name");
        if (name.length() >= 100) {
            name = name.substring(0, 99);
        }

        File album = new File();
        album.setCid(Integer.parseInt(params.get("cid") == null ? "0" : params.get("cid")));
        album.setSourceId(Integer.parseInt(params.get("uid") == null ? "0" : params.get("uid")));
        album.setType(Integer.parseInt(params.get("type")));
        album.setSource(Integer.parseInt(params.get("source") == null ? "0" : params.get("source")));
        album.setName(name);
        album.setUri(params.get("url"));
        album.setCreateTime(System.currentTimeMillis() / 1000);
        album.setUpdateTime(System.currentTimeMillis() / 1000);
        fileMapper.insert(album);
        return album.getId();
    }

}

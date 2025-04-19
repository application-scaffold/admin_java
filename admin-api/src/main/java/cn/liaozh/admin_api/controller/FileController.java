package cn.liaozh.admin_api.controller;

import com.alibaba.fastjson2.JSONObject;
import cn.liaozh.admin_api.aop.Log;
import cn.liaozh.admin_api.service.IFileService;
import cn.liaozh.admin_api.validate.commons.IdValidate;
import cn.liaozh.admin_api.validate.commons.IdsValidate;
import cn.liaozh.admin_api.validate.commons.PageValidate;
import cn.liaozh.admin_api.validate.file.FileCateValidate;
import cn.liaozh.admin_api.validate.file.FileMoveValidate;
import cn.liaozh.admin_api.validate.file.FileRenameValidate;
import cn.liaozh.admin_api.validate.file.FileSearchValidate;
import cn.liaozh.admin_api.vo.album.FileVo;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/adminapi/file")
@Tag(name = "文件数据管理")
public class FileController {

    @Resource
    IFileService fileService;

    @GetMapping("/lists")
    @Operation(summary = "文件列表")
    public AjaxResult<PageResult<FileVo>> fileList(@Validated PageValidate pageValidate,
                                                   @Validated FileSearchValidate searchValidate) {
        PageResult<FileVo> voPageResult = fileService.fileList(pageValidate, searchValidate);
        return AjaxResult.success(voPageResult);
    }

    @Log(title = "文件重命名")
    @PostMapping("/rename")
    @Operation(summary = "文件重命名")
    public AjaxResult<Object> rename(@Validated @RequestBody FileRenameValidate renameValidate) {
        fileService.fileRename(renameValidate.getId(), renameValidate.getName());
        return AjaxResult.success();
    }

    @Log(title = "文件移动")
    @PostMapping("/move")
    @Operation(summary = "相册文件移动")
    public AjaxResult<Object> move(@Validated @RequestBody FileMoveValidate moveValidate) {
        fileService.fileMove(moveValidate.getIds(), moveValidate.getCid());
        return AjaxResult.success();
    }

    @Log(title = "文件删除")
    @PostMapping("/delete")
    @Operation(summary = "相册文件删除")
    public AjaxResult<Object> del(@Validated @RequestBody IdsValidate idsValidate) {
        fileService.fileDel(idsValidate.getIds());
        return AjaxResult.success();
    }

    @GetMapping("/listCate")
    @Operation(summary = "文件分类列表")
    public AjaxResult<JSONObject> cateList(@Validated FileSearchValidate searchValidate) {
        JSONObject result = fileService.cateList(searchValidate);
        return AjaxResult.success(result);
    }

    @Log(title = "分类新增")
    @PostMapping("/addCate")
    @Operation(summary = "分类新增")
    public AjaxResult<Object> cateAdd(@Validated @RequestBody FileCateValidate cateValidate) {
        fileService.cateAdd(cateValidate);
        return AjaxResult.success();
    }

    @Log(title = "相册分类重命名")
    @PostMapping("/editCate")
    @Operation(summary = "相册分类重命名")
    public AjaxResult<Object> cateRename(@Validated @RequestBody FileRenameValidate renameValidate) {
        fileService.cateRename(renameValidate.getId(), renameValidate.getName());
        return AjaxResult.success();
    }

    @Log(title = "相册分类删除")
    @PostMapping("/delCate")
    @Operation(summary = "相册分类删除")
    public AjaxResult<Object> cateDel(@Validated @RequestBody IdValidate idValidate) {
        fileService.cateDel(idValidate.getId());
        return AjaxResult.success();
    }

}

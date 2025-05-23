package cn.liaozh.generator.controller;

import cn.liaozh.common.aop.NotPower;
import cn.liaozh.common.core.AjaxResult;
import cn.liaozh.common.core.PageResult;
import cn.liaozh.common.exception.OperateException;
import cn.liaozh.common.util.StringUtils;
import cn.liaozh.common.util.YmlUtils;
import cn.liaozh.common.validator.annotation.IDMust;
import cn.liaozh.generator.service.IGenerateService;
import cn.liaozh.generator.validate.GenParam;
import cn.liaozh.generator.validate.PageParam;
import cn.liaozh.generator.vo.DbColumnVo;
import cn.liaozh.generator.vo.DbTableVo;
import cn.liaozh.generator.vo.GenTableVo;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminapi/gen")
public class GenController {

    @Resource
    IGenerateService iGenerateService;

    /**
     * 库列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/db")
    public AjaxResult<PageResult<DbTableVo>> db(@Validated PageParam pageParam,
                                                @RequestParam Map<String, String> params) {
        PageResult<DbTableVo> list = iGenerateService.db(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 所有库表
     *
     * @author fzr
     * @param params 搜索参数
     * @return Object
     */
    @NotPower
    @GetMapping("/dbAll")
    public AjaxResult<List<DbTableVo>> dbAll(@RequestParam Map<String, String> params) {
        List<DbTableVo> list = iGenerateService.dbAll(params);
        return AjaxResult.success(list);
    }

    /**
     * 根据表名查字段
     *
     * @param tableName 表名
     * @return List<DbColumnVo>
     */
    @NotPower
    @GetMapping("/dbColumn")
    public AjaxResult<List<DbColumnVo>> dbColumn(@RequestParam String tableName) {
        List<DbColumnVo> list = iGenerateService.dbColumn(tableName);
        return AjaxResult.success(list);
    }

    /**
     * 生成列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<GenTableVo>> list(@Validated PageParam pageParam,
                                                   @RequestParam Map<String, String> params) {
        PageResult<GenTableVo> list = iGenerateService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 生成详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/detail")
    public AjaxResult<Map<String, Object>> detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, Object> maps = iGenerateService.detail(id);
        return AjaxResult.success(200, "成功", maps);
    }

    /**
     * 导入表结构
     *
     * @param tables 参数
     * @return Object
     */
    @PostMapping("/importTable")
    public AjaxResult<Object> importTable(String tables) {
        Assert.notNull(tables, "请选择要导入的表");
        String[] tableNames = tables.split(",");
        iGenerateService.importTable(tableNames);
        return AjaxResult.success("成功", 0);
    }

    /**
     * 编辑表结构
     *
     * @author fzr
     * @param genParam 参数
     * @return Object
     */
    @PostMapping("/editTable")
    public AjaxResult<Object> editTable(@Validated() @RequestBody GenParam genParam) {
        iGenerateService.editTable(genParam);
        return AjaxResult.success("成功", 0);
    }

    /**
     * 删除表结构
     *
     * @author fzr
     * @param genParam 参数
     * @return Object
     */
    @PostMapping("/delTable")
    public AjaxResult<Object> deleteTable(@Validated(value = GenParam.delete.class) @RequestBody GenParam genParam) {
        iGenerateService.deleteTable(genParam.getIds());
        return AjaxResult.success("成功", 0);
    }

    /**
     * 同步表结构
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @PostMapping("/syncTable")
    public AjaxResult<Object> syncTable(@Validated @IDMust() @RequestParam("id") Integer id) {
        iGenerateService.syncTable(id);
        return AjaxResult.success("成功", 0);
    }

    /**
     * 预览代码
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/previewCode")
    public AjaxResult<Map<String, String>> previewCode(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, String> map = iGenerateService.previewCode(id);
        return AjaxResult.success(map);
    }

    /**
     * 生成代码
     *
     * @author fzr
     * @param tables 表名
     */
    @GetMapping("/genCode")
    public AjaxResult<Object> genCode(String tables) {
        String production = YmlUtils.get("like.production");
        if (StringUtils.isNotEmpty(production) && production.equals("true")) {
            throw new OperateException("抱歉,演示环境不允许操作！");
        }

        Assert.notNull(tables, "请选择要生成的表");
        String[] tableNames = tables.split(",");
        for (String tableName : tableNames) {
            iGenerateService.genCode(tableName);
        }
        return AjaxResult.success();
    }

    /**
     * 下载代码
     *
     * @param response 响应对象
     * @param tables 表名
     * @throws IOException 异常
     */
    @GetMapping("/downloadCode")
    public void downloadCode(HttpServletResponse response, String tables) throws IOException {
        Assert.notNull(tables, "请选择要生成的表");
        String[] tableNames = tables.split(",");
        byte[] data = iGenerateService.downloadCode(tableNames);

        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"likeadmin-curd.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

}

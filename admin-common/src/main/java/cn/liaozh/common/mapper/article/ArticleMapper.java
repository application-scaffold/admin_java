package cn.liaozh.common.mapper.article;

import cn.liaozh.common.core.basics.IBaseMapper;
import cn.liaozh.common.entity.article.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章
 */
@Mapper
public interface ArticleMapper extends IBaseMapper<Article> {
}

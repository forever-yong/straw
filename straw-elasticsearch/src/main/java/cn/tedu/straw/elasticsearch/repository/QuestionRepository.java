package cn.tedu.straw.elasticsearch.repository;

import cn.tedu.straw.elasticsearch.vo.QuestionSearchVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository
        extends ElasticsearchRepository<QuestionSearchVO, Long> {
    // 关于声明ES API中的查询方法
    // - 方法名称必须是JPA风格的，可通过IntelliJ IDEA的提示生成方法名称；
    // - 尽管搜索的关键字可能只有1个，但涉及多个条件中使用时，也需要声明为多个参数；
    // - 使用Spring Data实现分页，方法的返回值类型必须是Page<?>类型的，其中的泛型就是查询结果的元素类型
    // - 使用Spring Data实现分页，方法的最后一个参数必须是Pageable
    Page<QuestionSearchVO> queryByTitleMatchesOrContentMatches(String titleKeyword,String contentKeyword, Pageable pageable);
}
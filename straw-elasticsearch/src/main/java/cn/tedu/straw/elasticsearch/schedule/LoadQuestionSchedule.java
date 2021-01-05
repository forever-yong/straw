package cn.tedu.straw.elasticsearch.schedule;

import cn.tedu.straw.elasticsearch.repository.QuestionRepository;
import cn.tedu.straw.elasticsearch.service.IQuestionService;
import cn.tedu.straw.elasticsearch.vo.QuestionSearchVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoadQuestionSchedule {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ElasticsearchOperations elasticsearchOperations;
    @Autowired
    IQuestionService questionService;

    @Scheduled(fixedRate = 1*60*1000)
    public void loadQuestions(){
        // 先将Elasticsearch服务上已有的数据全部删除：直接删除整个索引
        IndexOperations indexOps = elasticsearchOperations.indexOps(QuestionSearchVO.class);
        indexOps.delete();
        // 循环查询MySQL中的数据，并写入Elasticsearch中
        Integer pageNum = 1;
        PageInfo<QuestionSearchVO> pageInfo=null;
        do {
            pageInfo=questionService.getQuestionListFromDatabase(pageNum);
            pageNum++;
            questionRepository.saveAll(pageInfo.getList());
        }while(pageInfo.isHasNextPage());
    }

}

package com.like.weblog.weblog.service;

import com.like.weblog.weblog.dto.PageQuestionDTO;
import com.like.weblog.weblog.dto.QuestionDTO;
import com.like.weblog.weblog.map.QuestionMap;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.Question;
import com.like.weblog.weblog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMap questionMap;
    @Autowired
    UserMapper userMapper;

    public PageQuestionDTO findQuestionDTO(Integer page, Integer size) {
        Integer offset = size*(page-1);
        //封装页面的数据信息
        List<Question> questions = questionMap.findAllQuestion(offset,size);
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for(Question question:questions){
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            User user = userMapper.getUserById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        //封装分页信息
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setQuestionDTOs(questionDTOs);
        //查询数据数量
        Integer totalCount = questionMap.count();
        pageQuestionDTO.setQuestionPage(page,size,totalCount);

        return pageQuestionDTO;
    }

    public PageQuestionDTO findQuestionDTOByCreater(Integer page, Integer size, Long creater) {
        Integer offset = size*(page-1);
        //封装页面的数据信息
        List<Question> questions = questionMap.findQuestionByCreater(offset,size,creater);
        List<QuestionDTO> questionDTOs = new ArrayList<>();
        for(Question question:questions){
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestion(question);
            User user = userMapper.getUserById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOs.add(questionDTO);
        }
        //封装分页信息
        PageQuestionDTO pageQuestionDTO = new PageQuestionDTO();
        pageQuestionDTO.setQuestionDTOs(questionDTOs);
        //查询数据数量
        Integer totalCount = questionMap.countByCreater(creater);
        pageQuestionDTO.setQuestionPage(page,size,totalCount);

        return pageQuestionDTO;
    }

    public QuestionDTO findQuestionById(String id){
        Question question = questionMap.findQUestionById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestion(question);
        User user = userMapper.getUserById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void updateQuestion(String tag,String title,String description,String id,long modifideTime) {
        questionMap.updateQuestion(tag,title,description,id,modifideTime);
    }
}





